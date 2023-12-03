package com.example.userservice.persistence.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "bug")
@NamedQueries({
        @NamedQuery(name = Bug.BUG_GET_ALL,
                query = "select b from Bug b ORDER BY b.targetDate desc"),
//        @NamedQuery(name = BugEntity.BUG_FIND_BY_ID,
//                query = "select b from BugEntity b where b.id = :id" ),
//
})

public class Bug extends BaseEntity<Long> {
    public static final String BUG_GET_ALL = "BugEntity.getAllBugEntities";
    public static final String BUG_GET_BY_TITLE = "BugEntity.getBugByTitle";
    public static final String UPDATE_BUG = "BugEntity.updateBug";
    public static final String BUG_FIND_BY_ID = "BugEntity.findById";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String VERSION = "version";
    public static final String FIXEDVERSION = "fixedVersion";
    public static final String SEVERITY = "severity";
    public static final String STATUS = "status";

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "targetDate")
    private Date targetDate;

    @Column(name = "severity")
    private String severity;

    @Column(name = "status")
    private String status;

    @Column(name = "fixedVersion", nullable = true)
    private String fixedVersion;

    @ManyToOne
    @JoinColumn(name = "ASSIGNED_ID", nullable = true)
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "CREATED_ID", nullable = false)
    private User createdBy;

}
