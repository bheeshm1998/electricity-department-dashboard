package com.bcg.ebdashboardbackend.entity;

import com.bcg.ebdashboardbackend.model.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    String name;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String district;

    String state;

    String pinCode;

    @Enumerated(EnumType.STRING)
    OwnershipType ownershipType;

    @Enumerated(EnumType.STRING)
    GovtIdType gotIdType;

    @Column(unique = true)
    String idNumber;

    @Enumerated(EnumType.STRING)
    PlotType plotType;

    @Positive(message = "Load must be a positive")
    Float loadAppliedInKW;

    LocalDate applicationDate;

    LocalDate approvalDate;

    LocalDate modifiedDate;

    @Enumerated(EnumType.STRING)
    ApplicationStatus applicationStatus;

    @OneToOne
    Reviewer reviewer;

    @Column(columnDefinition = "TEXT")
    String reviewerComments;
}
