package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.example.controller.MovieCatalogController;

@SpringBootApplication
@ComponentScan(basePackageClasses = MovieCatalogController.class)
@EnableEurekaClient
public class XmMovieCatalogServiceApplication {

	@Bean
	@LoadBalanced	// service discovery and load baalance and more in 1 annotation
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(XmMovieCatalogServiceApplication.class, args);
	}
}
