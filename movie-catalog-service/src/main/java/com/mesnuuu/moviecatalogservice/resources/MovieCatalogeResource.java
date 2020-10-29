package com.mesnuuu.moviecatalogservice.resources;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mesnuuu.moviecatalogservice.models.CatalogItem;
import com.mesnuuu.moviecatalogservice.models.Movie;
import com.mesnuuu.moviecatalogservice.models.Rating;

@RestController
public class MovieCatalogeResource {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/catalog/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		List<Rating> ratingsList = Arrays.asList(
				new Rating("1234", 3),
				new Rating("5678", 4)

		);

		return ratingsList.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "description", rating.getRating());
		}).collect(Collectors.toList());
	}

}