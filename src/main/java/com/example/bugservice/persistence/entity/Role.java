package com.example.bugservice.persistence.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "roles")
@NamedQueries({
        @NamedQuery(name = Role.QUERY_GET_ROLES_BY_TYPE_LIST,
                query = "select r from Role r " + "where r.type in :" + Role.INPUT_TYPE_LIST),

        @NamedQuery(name = Role.QUERY_GET_ROLES_BY_ID,
                query = "select r from Role r " + "where r.id in :" + "roleId"),
        @NamedQuery(name = Role.GET_ALL_ROLES_TYPE,
                query = "SELECT r.type from Role r "),

        @NamedQuery(name = Role.GET_ALLROLESANDLINKEDPERMISSIONS,
                query = "SELECT r from Role r "),
   @NamedQuery(name = Role.QUERY_GET_ALLPERMISSIONS,
                query = "SELECT p from Permission p "),


})
public class Role extends BaseEntity<Long> {

    public static final String QUERY_GET_ROLES_BY_TYPE_LIST = "getRolesByTypeList";
    public static final String QUERY_GET_PERMISSIONS_NOT_FROM_ROLE = "getPermissionsNotFromRole";

    public static final String QUERY_GET_ROLES_BY_ID = "getRolesById";
    public static final String QUERY_GET_ALLPERMISSIONS = "getAllPermissions";

    public static final String GET_ALL_ROLES_TYPE = "getAllRolesTypes";

    public static final String GET_ALLROLESANDLINKEDPERMISSIONS = "getAllRolesAndLinkedPermissions";

    public static final String DELETE_PERMISSIONFROMROLE = "deletePermissionFromRole";

    public static final String INPUT_TYPE_LIST = "type";

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id", nullable = false)
    )
    private List<Permission> permissions = new ArrayList<>();
}
