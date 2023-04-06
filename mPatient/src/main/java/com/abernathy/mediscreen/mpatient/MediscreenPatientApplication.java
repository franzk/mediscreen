package com.abernathy.mediscreen.mpatient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MediscreenPatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediscreenPatientApplication.class, args);
    }


}
