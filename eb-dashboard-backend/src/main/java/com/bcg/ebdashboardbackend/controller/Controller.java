package com.bcg.ebdashboardbackend.controller;


import com.bcg.ebdashboardbackend.entity.Customer;
import com.bcg.ebdashboardbackend.service.impl.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/eb")
@CrossOrigin
public class Controller {

    @Autowired
    MyService service;

    @GetMapping("/hello")
    public String dummy(){


        System.out.println("controller to repo");
        service.saveUser();
        return "hello";
    }


    public List<Customer> getListOfCustomers(){
        return null;
    }

    public Customer getCustomerDetails(String id){
        return null;
    }
}
