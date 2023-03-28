package com.abernathy.mediscreen.massessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MediscreenAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenAssessmentApplication.class, args);
	}

}
