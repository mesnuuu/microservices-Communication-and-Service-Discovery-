package com.mesnuuu.movieinfoservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mesnuuu.movieinfoservice.models.Movie;

@RestController
public class MovieResource {

	@GetMapping("/movies/{movieId}")
	
	public Movie getMovies(@PathVariable("movieId") String movieId) {
		 return new Movie(movieId, "Name for ID " + movieId);
	}
}
