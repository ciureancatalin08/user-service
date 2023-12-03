// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package com.example.userservice.core.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionEnum {

    PERMISSION_MANAGEMENT("PERMISSION_MANAGEMENT"),
    USER_MANAGEMENT(" USER_MANAGEMENT"),
    BUG_MANAGEMENT(" BUG_MANAGEMENT");

    private String name;

}

