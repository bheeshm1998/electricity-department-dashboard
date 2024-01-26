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
import java.util.Optional;
import java.util.function.Function;

public class EbServiceImpl implements EbService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerDetailDTO getCustomerDetails(Integer id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if(optCustomer.isPresent()){
            return convertCustomerCustomerDetailDTO.apply(optCustomer.get());
        }
        return null;
    }

    @Override
    public List<EbConnectionRequestDTO> getListOfConnectionRequests(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageSize, pageNumber);
        Page<Customer> customerPage = customerRepository.findAll(page);
        List<Customer> customerList = customerPage.getContent();
        List<EbConnectionRequestDTO> ebConnectionRequestDTOList = customerList.stream().map(convertCustomerToEbConnectionDto).toList();
        return ebConnectionRequestDTOList;
    }

    Function<Customer, EbConnectionRequestDTO> convertCustomerToEbConnectionDto = (cust) -> {

        EbConnectionRequestDTO dto = new EbConnectionRequestDTO();
        dto.setId(cust.getId());
        dto.setName(cust.getName());
        dto.setGender(cust.getGender());
        dto.setApplicationDate(cust.getApplicationDate());
        dto.setModifiedDate(cust.getModifiedDate());
        dto.setApprovalDate(cust.getApprovalDate());
        dto.setOwnershipType(cust.getOwnershipType());
        dto.setPinCode(cust.getPinCode());
        dto.setDistrict(cust.getDistrict());
        dto.setState(cust.getState());
        dto.setLoadAppliedInKW(cust.getLoadAppliedInKW());
        dto.setPlotType(cust.getPlotType());

        return dto;
    };

    Function<Customer, CustomerDetailDTO> convertCustomerCustomerDetailDTO = (cust) -> {

        CustomerDetailDTO dto = new CustomerDetailDTO();
        dto.setId(cust.getId());
        dto.setName(cust.getName());
        dto.setGender(cust.getGender());
        dto.setApplicationDate(cust.getApplicationDate());
        dto.setModifiedDate(cust.getModifiedDate());
        dto.setApprovalDate(cust.getApprovalDate());
        dto.setOwnershipType(cust.getOwnershipType());
        dto.setPinCode(cust.getPinCode());
        dto.setDistrict(cust.getDistrict());
        dto.setState(cust.getState());
        dto.setLoadAppliedInKW(cust.getLoadAppliedInKW());
        dto.setPlotType(cust.getPlotType());
        dto.setReviewerComments(cust.getReviewerComments());
        dto.setReviewerName(cust.getReviewer().getName());
        dto.setGovtIdType(cust.getGovtIdType());
        dto.setIdNumber(cust.getIdNumber());

        return dto;
    };
}
