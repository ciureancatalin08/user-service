package com.example.bugservice.core.service;

import com.example.bugservice.controller.datamodel.BugDataModel;

import java.util.List;

public interface BugService {

    BugDataModel createBug(BugDataModel bugDataModel);

    List<BugDataModel> getAllBugs();

}
