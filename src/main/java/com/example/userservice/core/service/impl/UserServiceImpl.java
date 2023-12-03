package com.example.userservice.core.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.userservice.controller.datamodel.UserDataModel;
import com.example.userservice.controller.datamodel.UserLoginDataModel;
import com.example.userservice.controller.datamodel.UserLoginOutputDatenModel;
import com.example.userservice.persistence.dao.impl.UserDaoImpl;
import com.example.userservice.persistence.entity.Permission;
import com.example.userservice.persistence.entity.Role;
import com.example.userservice.persistence.entity.User;
import com.example.userservice.core.service.UserService;
import com.example.userservice.core.utils.MessageCatalog;
import com.example.userservice.core.utils.exceptions.BusinessWebAppException;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private RoleServiceImpl roleService;

    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @Override
    public String createUser(UserDataModel userDataModel) {

        if (userDataModel.getFirstName() == null || userDataModel.getEmail() == null || userDataModel.getRoles().isEmpty())
            throw new BusinessWebAppException(MessageCatalog.USER_FIELDS_MISSING, 400);

        if (userDao.emailExists(userDataModel.getEmail()))
            throw new BusinessWebAppException(MessageCatalog.USER_WITH_SAME_MAIL_EXISTS, 400);


        final User newUser = createNewUser(userDataModel);

        newUser.setUsername(userDataModel.getFirstName());
        newUser.setStatus(1);
        newUser.setPassword("DEFAULT_PASSWORD");

        try {
            userDao.createUser(newUser);
        } catch (Exception e) {
            throw new BusinessWebAppException(MessageCatalog.USER_INVALID_PATTERN, 400);
        }


        return newUser.getUsername();
    }

    @Override
    public UserDataModel getUserById(long id) {

        mapperFactory.classMap(User.class, UserDataModel.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(userDao.getUserById(id), UserDataModel.class);
    }

    @Override
    public UserLoginOutputDatenModel authenticateUser(UserLoginDataModel user) {

        User result = userDao.getUserByUsername(user.getUsername());

        if (result != null) {

            List<Permission> permissions = new ArrayList<>();
            for (Role roleEntity : result.getRoles()) {
                permissions.addAll(roleEntity.getPermissions());
            }

            permissions.stream().map(Permission::getType).toArray(String[]::new);
            Algorithm algorithm = Algorithm.HMAC256("harambe");
            String jwt = JWT.create().withIssuer("auth0")
                    .withClaim("id", result.getId())
                    .withClaim("username", user.getUsername())
                    .withArrayClaim("permissions", permissions.stream().map(Permission::getType).toArray(String[]::new))
                    .sign(algorithm);

            ArrayList<String> permissionsAsList = permissions.stream().map(Permission::getType).collect(Collectors.toCollection(ArrayList::new));


            return new UserLoginOutputDatenModel(result.getEmail(), result.getUsername(), permissionsAsList, jwt);

        }

        return null;
    }


    @Override
    public List<UserDataModel> getAllUsers() {

        mapperFactory.classMap(User.class, UserDataModel.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();

        List<UserDataModel> usersDataModel = new ArrayList<>();
        List<User> users = userDao.getAll();

        for (User user : users) {

            UserDataModel userDataModel = mapper.map(user, UserDataModel.class);
            userDataModel.setRoles(user.getRoles().stream().map(Role::getType).collect(Collectors.toList()));
            usersDataModel.add(userDataModel);
        }
        return usersDataModel;
    }

    public User createNewUser(UserDataModel userDataModel) {

        final User user = new User();
        user.setFirstName(userDataModel.getFirstName());
        user.setLastName(userDataModel.getLastName());
        user.setEmail(userDataModel.getEmail());
        user.setMobileNumber(userDataModel.getMobileNumber());

        if (userDataModel.getRoles() != null && !userDataModel.getRoles().isEmpty()) {
            user.getRoles().addAll(
                    roleService.getRolesByTypeList(userDataModel.getRoles()));
        }
        return user;
    }
}
