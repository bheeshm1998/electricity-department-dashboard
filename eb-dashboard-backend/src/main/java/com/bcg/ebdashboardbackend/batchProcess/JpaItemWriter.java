package com.bcg.ebdashboardbackend.batchProcess;

import com.bcg.ebdashboardbackend.entity.Customer;
import com.bcg.ebdashboardbackend.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class JpaItemWriter implements ItemWriter<Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void write(List<? extends Customer> customers) throws Exception {
        log.info("Writing to the DB");
        customerRepository.saveAll(customers);
    }
}

