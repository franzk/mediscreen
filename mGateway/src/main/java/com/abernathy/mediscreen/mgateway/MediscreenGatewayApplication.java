package com.abernathy.mediscreen.mgateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MediscreenGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenGatewayApplication.class, args);
	}

}
