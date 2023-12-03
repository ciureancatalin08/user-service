package com.example.userservice.controller.datamodel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RolePermissionDataModel {

    private long roleId;
    private String name;

    private List<PermissionDataModel> permissions;


}
