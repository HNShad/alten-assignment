package com.alten.services.vehicleinfomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class VehicleInfoManagerApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {

//		return new RestTemplate();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory( new HttpComponentsClientHttpRequestFactory() );

		return restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(VehicleInfoManagerApplication.class, args);
	}

}
