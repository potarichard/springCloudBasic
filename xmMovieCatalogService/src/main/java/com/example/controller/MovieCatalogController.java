package com.example.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.dao.CatalogItem;
import com.example.dao.Movie;
import com.example.dao.Rating;
import com.example.dao.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate resttemp;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
	{
			
		UserRating userRating = resttemp.getForObject("http://localhost:4457/ratingsdata/users/" + userId, UserRating.class);
		
		return userRating.getUserRating().stream()
				.map(rating -> 
				{
					Movie movie = resttemp.getForObject("http://localhost:4456/movies/" + rating.getMovieId(), Movie.class);
					return new CatalogItem(movie.getName(), "Test", rating.getRating());
				})
				.collect(Collectors.toList());
		
		
//		the future is webclient isntead of resttemplate
	}
}
