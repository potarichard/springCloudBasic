package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@RequestMapping("/{movieID}")
	public Movie getMovieInfo(@PathVariable("movieID") String movieId)
	{
		// use https://www.themoviedb.org/?language=en-US  for get live data !!!
		// https://www.youtube.com/watch?v=7nKKD2rKpUk&list=PLqq-6Pq4lTTbXZY_elyGv7IkKrfkSrX5e&index=4
		return new Movie(movieId, "Test name");
	}
	
	
}
