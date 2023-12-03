package com.example.userservice.persistence.dao;

import com.example.userservice.persistence.entity.Permission;


public interface PermissionDao {


    public Permission createPermission(Permission p);

    public String removePermission(long id);
}
