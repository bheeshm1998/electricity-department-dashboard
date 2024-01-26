package com.bcg.ebdashboardbackend.service;

import com.bcg.ebdashboardbackend.dto.BasicStatsDTO;
import com.bcg.ebdashboardbackend.dto.CustomerDetailDTO;
import com.bcg.ebdashboardbackend.dto.ConnectionRequestDTO;
import com.bcg.ebdashboardbackend.dto.MonthlyChartDataDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EbService {

    public CustomerDetailDTO getCustomerDetails(Long id);

    public List<ConnectionRequestDTO> getListOfConnectionRequests(Integer pageSize, Integer pageNumber);

    BasicStatsDTO getBasicStats();

    MonthlyChartDataDTO getChartDataForAYear(String year);
}
