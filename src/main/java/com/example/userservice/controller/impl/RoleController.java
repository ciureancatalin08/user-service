package com.example.userservice.controller.impl;

import com.example.userservice.controller.datamodel.PermissionDataModel;
import com.example.userservice.controller.datamodel.RolePermissionDataModel;
import com.example.userservice.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jbugs/jbugs-api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping()
    public ResponseEntity<List<RolePermissionDataModel>> getAllRollesAndLinkedPermissions() {

        List<RolePermissionDataModel> roles = roleService.getAllRolesAndLinkedPermissions();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}/{permissionId}")
    public void deletePermissionFromRole(@PathVariable long roleId, @PathVariable long permissionId) {

        roleService.deletePermissionFromRole(roleId, permissionId);
    }

    @GetMapping("/permissionsByRole/{roleId}")
    public List<PermissionDataModel> getPermissionNotFromRole(@PathVariable long roleId) {

        return roleService.getPermissionsNotFromRole(roleId);
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<String>> getAllUsers() {

        List<String> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PutMapping("/{roleId}/{permissionId}")
    public void addPermissionToRole(@PathVariable long roleId, @PathVariable long permissionId) {

        roleService.addPermissionToRole(roleId, permissionId);
    }
}
