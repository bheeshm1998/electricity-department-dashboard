package com.bcg.ebdashboardbackend.batchProcess;

import com.bcg.ebdashboardbackend.entity.Customer;
import com.bcg.ebdashboardbackend.model.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CsvItemProcessor implements ItemProcessor<CustomerDataInput, Customer> {

    @Override
    public Customer process(CustomerDataInput customerDataInput) {
        log.info("Processing the input");
        final Customer customer = new Customer();
        customer.setId(Integer.parseInt(customerDataInput.getID()));
        customer.setDistrict(customerDataInput.getDistrict());
        customer.setName(customerDataInput.getApplicant_Name());
        customer.setGender(Gender.valueOf(customerDataInput.getGender().toUpperCase()));
        return customer;
    }
}

