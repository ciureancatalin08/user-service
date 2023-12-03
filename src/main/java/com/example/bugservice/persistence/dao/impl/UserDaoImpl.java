package com.example.bugservice.persistence.dao.impl;


import com.example.bugservice.persistence.dao.UserDao;
import com.example.bugservice.persistence.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public User createUser(User user) {

        em.persist(user);
        return user;
    }

    @Override
    public User getUserById(long userId) {

        return em.createNamedQuery(User.USER_FIND_BY_ID, User.class)
                .setParameter("id", userId)
                .getSingleResult();
    }

    public User getUserByUsername(String username) {
        return em.createNamedQuery(User.USER_FIND_BY_USERNAME, User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public List<User> getAll() {
        return em.createNamedQuery(User.USER_FIND_ALL, User.class)
                .getResultList();
    }

    public boolean emailExists(String email) {
        long count = em.createNamedQuery(User.USER_FIND_BY_EMAIL, Long.class)
                .setParameter(User.EMAIL, email)
                .getSingleResult();
        return (count > 0);
    }

}
