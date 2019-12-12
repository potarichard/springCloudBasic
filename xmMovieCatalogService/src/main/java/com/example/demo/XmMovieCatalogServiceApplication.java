package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controller.MovieCatalogController;

@SpringBootApplication
//@ComponentScan(basePackageClasses = MovieCatalogController.class)
@EnableEurekaClient
@EnableCircuitBreaker
public class XmMovieCatalogServiceApplication {

	
	// ebbena  cloudban, ez a service viselkedik API gateway-ként
	// https://www.youtube.com/watch?v=sPgwbt7iREk - ez es a tobbi video zuul proxy
	// https://www.youtube.com/watch?v=dZ8Z5DpcdrM
	// https://www.youtube.com/watch?v=no-m5JK-gGc  lattam
	// https://www.youtube.com/watch?v=rlS9eH5tEnY  hosszú
	// https://www.youtube.com/watch?v=dZ8Z5DpcdrM  zuul & hystrix  ez pont az ai kell
	
	// zuul proxyval lehet lecserélni ezt a cuccot
	// zuulba vannak filterek (pre, route, post, error) azok pontosan mire jók ?
	// histrix a zuulban, mehet ?
	
	@Bean
	@LoadBalanced	// service discovery and load baalance and more in 1 annotation
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(XmMovieCatalogServiceApplication.class, args);
	}
}
