package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dao.CatalogItem;
import com.example.dao.Movie;
import com.example.dao.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

// igy mar nem belso fuggvenyhivas a getCatalogItem hanem az egesz classt autowireddel hivjabe masik classbol a spring
// igy mukodik a hystrix
@Service
public class MovieInfo {

	@Autowired
	private RestTemplate resttemp;
	
	@HystrixCommand(fallbackMethod = "getFlabbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = resttemp.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), "Test", rating.getRating());
	}
	
	public CatalogItem getFlabbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name Not available", "", rating.getRating());
	}
	
}
