package com.bcg.ebdashboardbackend.batchProcess;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CsvItemReader {

    final String[] FIELD_NAMES = new String[] {"ID","Applicant_Name",	"Gender",	"District",	"State",	"Pincode",	"Ownership",	"GovtID_Type",	"ID_Number",	"Category",	"Load_Applied",	"Date_of_Application",	"Date_of_Approval",	"Modified_Date",	"Status",	"Reviewer_ID",	"Reviewer_Name",	"Reviewer_Comments"};


    public ItemReader<CustomerDataInput> reader() {
        log.info("Reading the csv file");
        FlatFileItemReader<CustomerDataInput> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("electricity_board_case_study.csv")); // Update with your file path
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(FIELD_NAMES);
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(CustomerDataInput.class);
            }});
        }});
        return reader;
    }
}

