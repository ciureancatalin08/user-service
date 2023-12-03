package com.example.userservice.core.service;

import com.example.userservice.controller.datamodel.BugDataModel;

import java.util.List;

public interface BugService {

    BugDataModel createBug(BugDataModel bugDataModel);

    List<BugDataModel> getAllBugs();

}
