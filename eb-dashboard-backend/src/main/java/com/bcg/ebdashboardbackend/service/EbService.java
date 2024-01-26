package com.bcg.ebdashboardbackend.service;

import com.bcg.ebdashboardbackend.dto.CustomerDetailDTO;
import com.bcg.ebdashboardbackend.dto.EbConnectionRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EbService {

    public CustomerDetailDTO getCustomerDetails(Integer id);

    public List<EbConnectionRequestDTO> getListOfConnectionRequests(Integer pageSize, Integer pageNumber);
}
