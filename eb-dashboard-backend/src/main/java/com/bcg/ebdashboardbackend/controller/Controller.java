package com.bcg.ebdashboardbackend.controller;


import com.bcg.ebdashboardbackend.dto.BasicStatsDTO;
import com.bcg.ebdashboardbackend.dto.CustomerDetailDTO;
import com.bcg.ebdashboardbackend.dto.ConnectionRequestDTO;
import com.bcg.ebdashboardbackend.dto.MonthlyChartDataDTO;
import com.bcg.ebdashboardbackend.service.EbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/eb")
@CrossOrigin
public class Controller {

    @Autowired
    EbService service;

    @GetMapping("/list-all")
    public List<ConnectionRequestDTO> getListOfCustomers(){
        List<ConnectionRequestDTO> customers = service.getListOfConnectionRequests(20, 1);
        return customers;
    }

    @GetMapping("/details")
    public CustomerDetailDTO getCustomerDetails(@RequestParam Long id){
        CustomerDetailDTO details = service.getCustomerDetails(id);
        return details;
    }

    @GetMapping("/chart-data")
    public MonthlyChartDataDTO getChartDataForAYear(@RequestParam String year){
        return service.getChartDataForAYear(year);
    }

    @GetMapping("/stats")
    public BasicStatsDTO getBasicStatsInfo(){
        return service.getBasicStats();
    }
}
