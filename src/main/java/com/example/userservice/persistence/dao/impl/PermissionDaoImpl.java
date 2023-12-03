// =================================================================================================
// Copyright (c) 2017-2020 BMW Group. All rights reserved.
// =================================================================================================
package com.example.userservice.persistence.dao.impl;

import com.example.userservice.persistence.dao.PermissionDao;
import com.example.userservice.persistence.entity.Permission;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class PermissionDaoImpl implements PermissionDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Permission createPermission(Permission p) {
        em.persist(p);
        return p;
    }

    @Override
    public String removePermission(long id) {
        return null;
    }
}
