//package com.bcg.ebdashboardbackend.controller;
//
//
//import com.bcg.ebdashboardbackend.dto.CustomerDetailDTO;
//import com.bcg.ebdashboardbackend.dto.EbConnectionRequestDTO;
//import com.bcg.ebdashboardbackend.entity.Customer;
//import com.bcg.ebdashboardbackend.service.EbService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "/eb")
//@CrossOrigin
//public class Controller {
//
//    @Autowired
//    EbService service;
//
//    public List<EbConnectionRequestDTO> getListOfCustomers(){
//        List<EbConnectionRequestDTO> customers = service.getListOfConnectionRequests(5, 1);
//        return customers;
//    }
//
//    public CustomerDetailDTO getCustomerDetails(Integer id){
//        CustomerDetailDTO details = service.getCustomerDetails(id);
//        return details;
//    }
//}
