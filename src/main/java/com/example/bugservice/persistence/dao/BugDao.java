package com.example.bugservice.persistence.dao;

import com.example.bugservice.persistence.entity.Bug;

import java.util.List;

public interface BugDao {

    Bug createBug(Bug bug);

    List<Bug> getAll();

    void updateBug(Bug newBug);

    Bug getById(long id);

}
