package com.example.bugservice.persistence.dao;

import com.example.bugservice.persistence.entity.User;

public interface UserDao {


    User createUser(User user);

    User getUserById(long userId);

    User getUserByUsername(String username);
}
