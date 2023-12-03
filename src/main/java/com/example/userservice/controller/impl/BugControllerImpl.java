package com.example.userservice.controller.impl;

import com.example.userservice.controller.datamodel.BugDataModel;
import com.example.userservice.core.service.BugService;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BugControllerImpl {

    @Autowired
    private BugService bugService;

    @PostMapping(path = "/jbugs/jbugs-api/bugs",
            consumes = javax.ws.rs.core.MediaType.APPLICATION_JSON,
            produces = javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public ResponseEntity<BugDataModel> createBug(@Context SecurityContext securityContext, @RequestBody BugDataModel input) {

        //        if (securityContext.isUserInRole(String.valueOf(PermissionEnum.BUG_MANAGEMENT))) {
            BugDataModel response = bugService.createBug(input);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(new BugDataModel(), HttpStatus.UNAUTHORIZED);
//        }
    }

    @GetMapping(path = "/jbugs/jbugs-api/bugs")
    public ResponseEntity getAll(@Context SecurityContext securityContext) {
//        if (securityContext.isUserInRole(String.valueOf(PermissionEnum.BUG_MANAGEMENT))) {

            List<BugDataModel> response = bugService.getAllBugs();
            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(new BugDataModel(), HttpStatus.UNAUTHORIZED);
//        }
    }

}
