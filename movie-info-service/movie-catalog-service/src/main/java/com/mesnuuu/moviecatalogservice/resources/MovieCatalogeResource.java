package com.mesnuuu.moviecatalogservice.resources;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mesnuuu.moviecatalogservice.models.CatalogItem;
import com.mesnuuu.moviecatalogservice.models.Movie;
import com.mesnuuu.moviecatalogservice.models.Rating;
import com.mesnuuu.moviecatalogservice.models.UserRating;

@RestController
@EnableEurekaClient
public class MovieCatalogeResource {

	@Autowired
	private RestTemplate restTemplate;
	private String ratingsDataServiceUrl = "http://localhost:8083/ratingsdata/user/fee";
	private String movieInfoServiceUrl = "http://localhost:8082/movies/";

	@GetMapping("/catalog/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating;
		userRating = restTemplate.getForObject(ratingsDataServiceUrl + userId,
				UserRating.class);

		return userRating.getRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject(movieInfoServiceUrl + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Description", rating.getRating());
		}).collect(Collectors.toList());

	}

}