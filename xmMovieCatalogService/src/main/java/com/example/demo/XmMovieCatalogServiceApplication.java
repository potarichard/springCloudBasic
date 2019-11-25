package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.example.controller.MovieCatalogController;

@SpringBootApplication
@ComponentScan(basePackageClasses = MovieCatalogController.class)
public class XmMovieCatalogServiceApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(XmMovieCatalogServiceApplication.class, args);
	}
}
