package com.bcg.ebdashboardbackend.controller;


import com.bcg.ebdashboardbackend.dto.*;
import com.bcg.ebdashboardbackend.entity.Connection;
import com.bcg.ebdashboardbackend.service.EbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(path = "/eb")
@CrossOrigin
public class Controller {

    @Autowired
    EbService service;

    @GetMapping("/details")
    public ResponseEntity<CustomerDetailDTO> getCustomerDetails(@RequestParam Long id){
        CustomerDetailDTO details = service.getCustomerDetails(id);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/chart-data")
    public ResponseEntity<MonthlyChartDataDTO> getChartDataForAYear(@RequestParam String year){
        MonthlyChartDataDTO monthlyChartDataDTO = service.getChartDataForAYear(year);
        return ResponseEntity.ok(monthlyChartDataDTO);
    }

    @GetMapping("/stats")
    public ResponseEntity<BasicStatsDTO> getBasicStatsInfo(){
        BasicStatsDTO stats = service.getBasicStats();
        return ResponseEntity.ok(stats) ;
    }

    @PostMapping("/update")
    public ResponseEntity<CustomerDetailDTO> updateTheDetails(@RequestBody RequestUpdateDTO request){
        CustomerDetailDTO detail = service.updateTheDetails(request);
        return ResponseEntity.ok(detail);
    }

    @GetMapping("/search")
    public ResponseEntity<ConnectionsPage> searchEntities(
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        LocalDate localStartDate = LocalDate.of(1971, 1, 1);
        LocalDate localEndDate = LocalDate.now();

        if(startDate != null && !startDate.isEmpty() && !startDate.isBlank() && !startDate.equals("null")){
            localStartDate = LocalDate.parse(startDate,  DateTimeFormatter.ISO_DATE_TIME);
        }
        if(endDate != null && !endDate.isEmpty() && !endDate.isBlank() && !endDate.equals("null")){
            localEndDate = LocalDate.parse(endDate,  DateTimeFormatter.ISO_DATE_TIME);
        }

        Pageable pageable = PageRequest.of(page, size);
        ConnectionsPage connectionsPage = service.findByFilterAndDateRange(filter, localStartDate, localEndDate, pageable);
        return ResponseEntity.ok(connectionsPage);
    }
}
