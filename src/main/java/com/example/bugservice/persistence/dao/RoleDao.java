// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package com.example.bugservice.persistence.dao;

import com.example.bugservice.persistence.entity.Role;
import com.example.bugservice.controller.datamodel.PermissionDataModel;

import java.util.List;


public interface RoleDao {

    Role getRolesById(final long roleId);

    List<String> getAllRoles();

    List<Role> getAllRolesAndLinkedPermissions();

    List<Role> getRolesByTypeList(final List<String> typeList);

    List<PermissionDataModel> getPermissionsNotFromRole(long roleId);
    void addPermissionToRole(long roleId, long permissionId);
}
