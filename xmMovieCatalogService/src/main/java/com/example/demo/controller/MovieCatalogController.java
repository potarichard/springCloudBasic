package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.CatalogItem;
import com.example.dao.UserRating;
import com.example.demo.services.MovieInfo;
import com.example.demo.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
	{			
		UserRating userRating = userRatingInfo.getUserRating(userId);
		
		return userRating.getUserRating().stream()
				.map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
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



// ez a teljes methodra vonatkozik amiben 2 service call van, ha az egyik rossz ez hivodik az nem jo
//public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId)	{	
//	return Arrays.asList(new CatalogItem("Movie name Not available", "", 0));
//}




















