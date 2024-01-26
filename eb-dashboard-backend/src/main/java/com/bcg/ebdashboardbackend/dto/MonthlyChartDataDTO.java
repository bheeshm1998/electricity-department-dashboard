package com.bcg.ebdashboardbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MonthlyChartDataDTO {
    List<Integer> totalRequestsPendingEveryMonth;

    List<Integer> totalRequestsRejectedEveryMonth;

    List<Integer> totalConnectionsReleasedEveryMonth;

    List<Integer> totalRequestsApprovedEveryMonth;

    public MonthlyChartDataDTO() {

        totalRequestsPendingEveryMonth = new ArrayList<>();
        totalRequestsRejectedEveryMonth = new ArrayList<>();
        totalConnectionsReleasedEveryMonth = new ArrayList<>();
        totalRequestsApprovedEveryMonth = new ArrayList<>();

        for(int i=1; i<=12; i++){
            totalRequestsPendingEveryMonth.add(0);
            totalRequestsRejectedEveryMonth.add(0);
            totalConnectionsReleasedEveryMonth.add(0);
            totalRequestsApprovedEveryMonth.add(0);
        }
    }
}
