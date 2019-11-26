package com.example.demo.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dao.Rating;
import com.example.dao.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingInfo {

	@Autowired
	private RestTemplate resttemp;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId) {
		return resttemp.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
	}
	
	public UserRating getFallbackUserRating(String userId) {
		UserRating userrating = new UserRating();
		userrating.setId(userId);
		userrating.setUserRating(Arrays.asList(
				new Rating("0", 0)));
		
		return userrating;
	}	
	
}
