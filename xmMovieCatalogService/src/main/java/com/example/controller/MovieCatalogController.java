package com.example.controller;

import java.util.Arrays;
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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate resttemp;
	
	@RequestMapping("/{userId}")
	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
	{			
		UserRating userRating = getUserRating(userId);
		
		return userRating.getUserRating().stream()
				.map(rating -> getCatalogItem(rating))
				.collect(Collectors.toList());
	}

	private CatalogItem getCatalogItem(Rating rating) {
		Movie movie = resttemp.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), "Test", rating.getRating());
	}

	private UserRating getUserRating(String userId) {
		return resttemp.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
	}
	
	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId)
	{	
		return Arrays.asList(new CatalogItem("Not available", "", 0));
	}
}

//the future is webclient isntead of resttemplate


// problem can be, a microservice resonse is slow, or down
// request comes in -> spawns new threads, threads timeout/termination is slower than the requests
// every other stuff will be down cuz thread pool will be flooded and everything shuts down

// ultimate solution is : Hystix - Circuit breaker pattern 

// when the cicrcuit is broken -> need alternative response/method to call for that service response instead
// fallback mechanism
// can return default response or a cached response (better).

// the hystrix proxys (wraps the class, so hystrix makes the real calls and do the checks!) the requestapi ? and checks if its ok, if not then it uses the fallback method instead