package com.bcg.ebdashboardbackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reviewer {

    @Id
    private Integer id;

    private String name;
}
