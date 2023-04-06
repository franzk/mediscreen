package com.abernathy.mediscreen.mnotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MediscreenNotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenNotesApplication.class, args);
	}

}
