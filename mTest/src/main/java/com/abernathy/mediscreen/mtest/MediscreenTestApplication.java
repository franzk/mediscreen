package com.abernathy.mediscreen.mtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class MediscreenTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenTestApplication.class, args);
	}

	@GetMapping("/")
	public ResponseEntity<String> home() {
		return new ResponseEntity<>("salut !", HttpStatus.OK);
	}


}
