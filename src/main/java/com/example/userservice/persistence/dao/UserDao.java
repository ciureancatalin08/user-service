package com.example.userservice.persistence.dao;

import com.example.userservice.persistence.entity.User;

public interface UserDao {


    User createUser(User user);

    User getUserById(long userId);

    User getUserByUsername(String username);
}
