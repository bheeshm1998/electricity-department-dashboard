package com.bcg.ebdashboardbackend.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Reviewer {

    @Id
    private Integer id;

    private String name;
}
