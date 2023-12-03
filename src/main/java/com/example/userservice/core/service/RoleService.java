package com.example.userservice.core.service;

import com.example.userservice.controller.datamodel.PermissionDataModel;
import com.example.userservice.controller.datamodel.RolePermissionDataModel;
import com.example.userservice.persistence.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRolesByTypeList(List<String> typeList);

    List<String> getAllRoles();

    List<RolePermissionDataModel> getAllRolesAndLinkedPermissions();

    void deletePermissionFromRole(long roleId, long permissionId);

    List<PermissionDataModel> getPermissionsNotFromRole(long roleId);

    void addPermissionToRole(long roleId, long permissionId);
}

