package com.bcg.ebdashboardbackend.service.impl;

import com.bcg.ebdashboardbackend.dto.CustomerDetailDTO;
import com.bcg.ebdashboardbackend.dto.EbConnectionRequestDTO;
import com.bcg.ebdashboardbackend.entity.Customer;
import com.bcg.ebdashboardbackend.repository.CustomerRepository;
import com.bcg.ebdashboardbackend.service.EbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EbServiceImpl implements EbService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerDetailDTO getCustomerDetails(Integer id) {
        return null;
    }

    @Override
    public List<EbConnectionRequestDTO> getListOfConnectionRequests(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageSize, pageNumber);
        Page<Customer> customerPage = customerRepository.findAll(page);
        List<Customer> customerList = customerPage.getContent();
    }

    Function<Customer, EbConnectionRequestDTO> convertCustomerToEbConnectionDto = (cust) -> {

        EbConnectionRequestDTO dto = new EbConnectionRequestDTO();
        dto.
    }
}
