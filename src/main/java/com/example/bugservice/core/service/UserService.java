package com.example.bugservice.core.service;

import com.example.bugservice.controller.datamodel.UserDataModel;
import com.example.bugservice.controller.datamodel.UserLoginDataModel;
import com.example.bugservice.controller.datamodel.UserLoginOutputDatenModel;

import java.util.List;

public interface UserService {

    String createUser(UserDataModel userDataModel);

    UserDataModel getUserById(long id);

    UserLoginOutputDatenModel authenticateUser(UserLoginDataModel user);

    List<UserDataModel> getAllUsers();
}
