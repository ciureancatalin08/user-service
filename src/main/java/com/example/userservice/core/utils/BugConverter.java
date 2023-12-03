package com.example.userservice.core.utils;

import com.example.userservice.controller.datamodel.BugDataModel;
import com.example.userservice.persistence.dao.UserDao;
import com.example.userservice.persistence.entity.Bug;
import com.example.userservice.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BugConverter {

    @Autowired
    private UserDao userDao;


    public Bug convertInputDataModelToEntity(BugDataModel input) {
        final Bug bugEntity = new Bug();
        bugEntity.setTitle(input.getTitle());
        bugEntity.setDescription(input.getDescription());
        bugEntity.setVersion(input.getVersion());
        bugEntity.setFixedVersion(input.getFixedVersion());
        bugEntity.setTargetDate(parseStringToDateCalendar(input.getTargetDate()));
        String severity = input.getSeverity().substring(0, 1).toUpperCase() + input.getSeverity().substring(1);
        bugEntity.setSeverity(severity);


        if (input.getCreatedId() != 0) {
            try {
                User userEntity = userDao.getUserById(input.getCreatedId());
                bugEntity.setCreatedBy(userEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (input.getASSIGNED_ID() != null) {
            try {
                User userEntity = userDao.getUserById(input.getASSIGNED_ID());
                bugEntity.setAssignedTo(userEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return bugEntity;
    }


    public BugDataModel convertEntityToInputDataMode(Bug bug) {
        final BugDataModel b = new BugDataModel();
//        b.setId(bug.getId());
        b.setTitle(bug.getTitle());
        b.setStatus(bug.getStatus());
        b.setDescription(bug.getDescription());
        b.setVersion(bug.getVersion());
        b.setFixedVersion(bug.getFixedVersion());
        b.setTargetDate(parseDateToString(bug.getTargetDate()));
        b.setSeverity(bug.getSeverity());
        try {
            b.setUsernameCreatedBy(userDao.getUserById(bug.getCreatedBy().getId()).getUsername());
            if (bug.getAssignedTo() != null) {
                b.setUsernameAssignTo(userDao.getUserById(bug.getAssignedTo().getId()).getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }



    // "yyyy-MM-ddT21:00:00.000Z   => "yyyy-MM-dd HH:mm:ss""
    public Date parseStringToDateCalendar(String input) {


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = null;
        try {
            date = dateFormat.parse(input);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public String parseDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = dateFormat.format(date);

        return str;

    }
}
