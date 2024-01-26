package com.bcg.ebdashboardbackend.dto;

import com.bcg.ebdashboardbackend.model.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDetailDTO {

    Long id;

    String name;

    Gender gender;

    String district;

    String state;

    String pinCode;

    OwnershipType ownershipType;

    IdType govtIdType;

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
