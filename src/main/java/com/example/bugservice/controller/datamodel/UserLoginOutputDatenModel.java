package com.example.bugservice.controller.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
public class UserLoginOutputDatenModel {

    private String email;
    private String userName;
    private ArrayList<String> permissions;
    private String token;

}
