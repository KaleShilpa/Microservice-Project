package com.javaexpress.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javaexpress.movieinfoservice.model.Movie;
import com.javaexpress.movieinfoservice.model.MovieSummary;

@RestController
@RequestMapping("/movies")
@RefreshScope
public class MovieInfoController {
	
	@Value("${api.key:default message}")
	private String apiKey;
	@Value("${my.greeting:default message}")
	private String greeting;
	
	@Autowired
	private RestTemplate restTemplate;
	//https://api.themoviedb.org/3/movie/100?api_key=cea3b7a0b210db1ea9f3707365849dd8
	
	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") int movieId) {
		System.out.println("******************** api key =" +apiKey);
		System.out.println("******************** movieId =" +movieId);
		MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey, MovieSummary.class);
		return new Movie(movieId,movieSummary.getTitle(),movieSummary.getOverview());
	}
	
	@GetMapping("/config-parameters")
	public String getConfigParameters() {
		return apiKey +"----- "+ greeting;
	}

}
