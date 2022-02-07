package com.javaexpress.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javaexpress.moviecatalogservice.model.Movie;
import com.javaexpress.moviecatalogservice.model.Rating;
import com.javaexpress.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfoService {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "fallBackMovieInfo")
	public Movie getMovieInfo(Rating rating) {
		System.out.println("MovieId == "+rating.getMovieId());
		return restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
	}

	public Movie fallBackMovieInfo(Rating rating){
		return new Movie(0,"Movie not available","NA");
	}
}
