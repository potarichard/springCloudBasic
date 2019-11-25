package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Rating;
import com.example.demo.dao.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RaitingsResource {

	@RequestMapping("/{movieID}")
	public Rating getRating(@PathVariable("movieID") String movieId)
	{
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("/users/{userID}")
	public UserRating getUserRating(@PathVariable("userID") String userId)
	{
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 3));
		
		UserRating ur = new UserRating();
		ur.setUserRating(ratings);
		
		return ur;
	}
}
