package com.bcg.ebdashboardbackend.dto;

import com.bcg.ebdashboardbackend.model.*;

import java.time.LocalDate;

public class CustomerDetailDTO {

    Integer id;

    String name;

    Gender gender;

    String district;

    String state;

    String pinCode;

    OwnershipType ownershipType;

    GovtIdType gotIdType;

    String idNumber;

    PlotType plotType;

    Float loadAppliedInKW;

    LocalDate applicationDate;

    LocalDate approvalDate;

    LocalDate modifiedDate;

    ApplicationStatus applicationStatus;

    String reviewerName;

    String reviewerComments;
}
