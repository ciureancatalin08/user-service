package com.example.userservice.controller.datamodel;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDataModel extends BaseDataModel<Long> {

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String userName;
    private List<String> roles = new ArrayList<>();
    private int status;
}
