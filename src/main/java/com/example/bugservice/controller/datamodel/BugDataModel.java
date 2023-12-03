package com.example.bugservice.controller.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BugDataModel {

    private String title;
    private String description;
    private String version;
    private String targetDate;
    private String fixedVersion;
    private String severity;
    private Long createdId;
    private Long ASSIGNED_ID;

    private String usernameAssignTo;
    private String usernameCreatedBy;
    private String status;


}
