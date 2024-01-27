package com.bcg.ebdashboardbackend;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class EbDashboardBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbDashboardBackendApplication.class, args);

	}
}
