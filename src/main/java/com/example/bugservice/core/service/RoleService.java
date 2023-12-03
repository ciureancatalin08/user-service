package com.example.bugservice.core.service;

import com.example.bugservice.controller.datamodel.PermissionDataModel;
import com.example.bugservice.controller.datamodel.RolePermissionDataModel;
import com.example.bugservice.persistence.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRolesByTypeList(List<String> typeList);

    List<String> getAllRoles();

    List<RolePermissionDataModel> getAllRolesAndLinkedPermissions();

    void deletePermissionFromRole(long roleId, long permissionId);

    List<PermissionDataModel> getPermissionsNotFromRole(long roleId);

    void addPermissionToRole(long roleId, long permissionId);
}

