package com.bcg.ebdashboardbackend.batchProcess;

import com.bcg.ebdashboardbackend.entity.*;
import com.bcg.ebdashboardbackend.model.*;
import com.bcg.ebdashboardbackend.utils.EbDateFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Slf4j
@Component
public class CsvItemProcessor implements ItemProcessor<CustomerDataInput, Connection> {

    DateTimeFormatter dateFormatter = EbDateFormatter.indianDateTimeFormatter;
    @Override
    public Connection process(CustomerDataInput customerDataInput) {
        final Connection connection = Connection.builder()
                .id(Long.valueOf(customerDataInput.getID()))
                .customer(Customer.builder()
                        .gender(Gender.valueOf(customerDataInput.getGender().toUpperCase()))
                        .name(customerDataInput.getApplicant_Name())
                        .address(Address.builder()
                                .state(customerDataInput.getState())
                                .district(customerDataInput.getDistrict())
                                .pinCode(customerDataInput.getPincode())
                                .ownershipType(OwnershipType.valueOf(customerDataInput.getOwnership().toUpperCase()))
                                .build())
                        .govtIdentification(GovtIdentification.builder()
                                .idNumber(customerDataInput.getID_Number())
                                .idType(IdType.valueOf(customerDataInput.getGovtID_Type().toUpperCase()))
                                .build())
                        .build())
                .applicationDate(stringToLocalDateConverter.apply(customerDataInput.getDate_of_Application()))
                .approvalDate(stringToLocalDateConverter.apply(customerDataInput.getDate_of_Approval()))
                .modifiedDate(stringToLocalDateConverter.apply(customerDataInput.getModified_Date()))
                .loadAppliedInKW(Float.valueOf(customerDataInput.getLoad_Applied()))
                .plotType(PlotType.valueOf(customerDataInput.getCategory().toUpperCase()))
                .applicationStatus(ApplicationStatus.valueOf(customerDataInput.getStatus().toUpperCase().replace(" ", "_")))
                .reviewerComments(customerDataInput.getReviewer_Comments())
                .yearOfRequest(String.valueOf(stringToLocalDateConverter.apply(customerDataInput.getDate_of_Application()).getYear()))
                .reviewer(Reviewer.builder()
                        .id(Long.valueOf(customerDataInput.getReviewer_ID()))
                        .name(customerDataInput.getReviewer_Name())
                        .build())
                .build();

        return connection;
    }

    Function<String, LocalDate> stringToLocalDateConverter = (dateInStringFormat) -> {
        if(dateInStringFormat.trim().isEmpty()) return null;
        LocalDate localDate = LocalDate.parse(dateInStringFormat.trim(), dateFormatter);
        return localDate;
    };
}

