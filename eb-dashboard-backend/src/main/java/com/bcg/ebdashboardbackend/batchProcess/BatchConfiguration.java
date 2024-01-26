package com.bcg.ebdashboardbackend.batchProcess;

import com.bcg.ebdashboardbackend.entity.Connection;
import com.bcg.ebdashboardbackend.entity.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CsvItemReader csvItemReader;
    private final CsvItemProcessor csvItemProcessor;
    private final JpaItemWriter jpaItemWriter;

    public BatchConfiguration(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            CsvItemReader csvItemReader,
            CsvItemProcessor csvItemProcessor,
            JpaItemWriter jpaItemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.csvItemReader = csvItemReader;
        this.csvItemProcessor = csvItemProcessor;
        this.jpaItemWriter = jpaItemWriter;
    }

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("step")
                .<CustomerDataInput, Connection>chunk(100)
                .reader(csvItemReader.reader())
                .processor(csvItemProcessor)
                .writer(jpaItemWriter)
                .build();
    }

    @Bean
    public Job myJob(Step myStep) {
        return jobBuilderFactory.get("myJob")
                .incrementer(new RunIdIncrementer())
                .start(myStep)
                .build();
    }
}

