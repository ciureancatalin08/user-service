// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package com.example.userservice.core.service.impl;

import com.example.userservice.controller.datamodel.PermissionDataModel;
import com.example.userservice.controller.datamodel.RolePermissionDataModel;
import com.example.userservice.core.service.RoleService;
import com.example.userservice.persistence.dao.RoleDao;
import com.example.userservice.persistence.entity.Permission;
import com.example.userservice.persistence.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Role> getRolesByTypeList(List<String> typeList) {

        return roleDao.getRolesByTypeList(typeList);
    }


    @Override
    public List<String> getAllRoles() {

        return roleDao.getAllRoles();
    }

    @Override
    public List<RolePermissionDataModel> getAllRolesAndLinkedPermissions() {

        List<Role> roles = roleDao.getAllRolesAndLinkedPermissions();

        return roles.stream().map(this::mapRolesAndpermissions).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void deletePermissionFromRole(long roleId, long permissionId) {
        Role role = entityManager.find(Role.class, roleId);
        Permission permission = entityManager.find(Permission.class, permissionId);
        role.getPermissions().remove(permission);
        entityManager.flush();
    }

    private RolePermissionDataModel mapRolesAndpermissions(Role role) {
        RolePermissionDataModel rolePermissionDataModel = new RolePermissionDataModel();
        rolePermissionDataModel.setName(role.getType());
        rolePermissionDataModel.setRoleId(role.getId());
        rolePermissionDataModel.setPermissions(role.getPermissions().stream().map(permission -> {
            PermissionDataModel permissionDataModel = new PermissionDataModel();
            permissionDataModel.setId(permission.getId());
            permissionDataModel.setName(permission.getType());
            return permissionDataModel;
        }).collect(Collectors.toList()));

        return rolePermissionDataModel;
    }

    @Override
    public List<PermissionDataModel> getPermissionsNotFromRole(long roleId) {
        return roleDao.getPermissionsNotFromRole(roleId);
    }

    @Override
    @Transactional
    public void addPermissionToRole(long roleId, long permissionId) {
        roleDao.addPermissionToRole(roleId, permissionId);
    }
}
