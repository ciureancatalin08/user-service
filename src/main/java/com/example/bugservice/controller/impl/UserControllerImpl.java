package com.example.bugservice.controller.impl;

import com.example.bugservice.controller.datamodel.UserDataModel;
import com.example.bugservice.controller.datamodel.UserLoginDataModel;
import com.example.bugservice.controller.datamodel.UserLoginOutputDatenModel;
import com.example.bugservice.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/jbugs")
public class UserControllerImpl {

    @Autowired
    private UserService userService;


    @PostMapping(path = "/login/auth",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public ResponseEntity<UserLoginOutputDatenModel> loginUser(@RequestBody UserLoginDataModel userLoginDataModel) {

        UserLoginOutputDatenModel response = userService.authenticateUser(userLoginDataModel);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping(path = "/jbugs-api/users/insert",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> createUser(@RequestBody UserDataModel user) {

        String response = userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/jbugs-api/users")
    public ResponseEntity<List<UserDataModel>> getAllUsers() {

        List<UserDataModel> response = userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @GetMapping("/users")
//    public ResponseEntity<UserDataModel> getUserById(@RequestParam("id") long id) {
//
//        UserDataModel user = userService.getUserById(id);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

//    @Consumes(MediaType.APPLICATION_JSON)
//    @PutMapping
//    public Response updateUser(@Context SecurityContext securityContext, UserDataModel userDataModel) {
//        if (securityContext.isUserInRole(String.valueOf(PermissionType.USER_MANAGEMENT))) {
//            userUpdateDTO.setWhoUpdatedHim(securityContext.getUserPrincipal().getName());
//            userService.updateUser(userUpdateDTO);
//            return Response.ok().build();
//        }
//        return Response.status(Response.Status.FORBIDDEN).entity(MessageCatalog.PERMISSION_NOT_FOUND).build();
//    }
//
//    @Path("/password")
//    @PutMapping
//    public Response changePassword(@Context SecurityContext securityContext, UserChangePasswordDTO userChangePasswordDTO) {
//        if (securityContext.isUserInRole(String.valueOf(PermissionType.USER_MANAGEMENT))) {
//            userFacade.changePassword(userChangePasswordDTO);
//            return Response.ok().build();
//        }
//        return Response.status(Response.Status.FORBIDDEN).entity(MessageCatalog.PERMISSION_NOT_FOUND).build();
//    }
//
//    @Path("/{id}")
//    @PutMapping
//    public Response deactivateUser(@Context SecurityContext securityContext, @PathParam("id") long id) {
//        if (securityContext.isUserInRole(String.valueOf(PermissionType.USER_MANAGEMENT))) {
//            userFacade.deactivateUser(id);
//            return Response.ok().build();
//        }
//        return Response.status(Response.Status.FORBIDDEN).build();
//    }
//


}
