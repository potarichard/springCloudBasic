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
		return new Movie(movieId, "Test name");
	}
	
}
