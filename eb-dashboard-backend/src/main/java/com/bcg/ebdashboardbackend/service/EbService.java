package com.bcg.ebdashboardbackend.service;

import com.bcg.ebdashboardbackend.dto.*;
import com.bcg.ebdashboardbackend.entity.Connection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface EbService {

    public CustomerDetailDTO getCustomerDetails(Long id);

    public List<ConnectionRequestDTO> getListOfConnectionRequests(Integer pageSize, Integer pageNumber);

    BasicStatsDTO getBasicStats();

    MonthlyChartDataDTO getChartDataForAYear(String year);

    CustomerDetailDTO updateTheDetails(RequestUpdateDTO request);

    ConnectionsPage findByFilterAndDateRange(String filter, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
