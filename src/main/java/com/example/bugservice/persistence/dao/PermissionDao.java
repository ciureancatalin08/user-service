package com.example.bugservice.persistence.dao;

import com.example.bugservice.persistence.entity.Permission;


public interface PermissionDao {


    public Permission createPermission(Permission p);

    public String removePermission(long id);
}
