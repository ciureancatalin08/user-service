package com.example.userservice.core.service;

import com.example.userservice.controller.datamodel.UserDataModel;
import com.example.userservice.controller.datamodel.UserLoginDataModel;
import com.example.userservice.controller.datamodel.UserLoginOutputDatenModel;

import java.util.List;

public interface UserService {

    String createUser(UserDataModel userDataModel);

    UserDataModel getUserById(long id);

    UserLoginOutputDatenModel authenticateUser(UserLoginDataModel user);

    List<UserDataModel> getAllUsers();
}
