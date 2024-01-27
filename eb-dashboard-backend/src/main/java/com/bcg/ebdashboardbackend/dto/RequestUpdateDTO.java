package com.bcg.ebdashboardbackend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestUpdateDTO {

    private Long id;

    private String reviewerComments;

    private Float loadAppliedInKW;

    private String applicationStatus;
}
