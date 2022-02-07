package com.javaexpress.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.moviecatalogservice.model.CatalogItem;
import com.javaexpress.moviecatalogservice.model.Movie;
import com.javaexpress.moviecatalogservice.model.UserRating;
import com.javaexpress.moviecatalogservice.service.MovieInfoService;
import com.javaexpress.moviecatalogservice.service.UserRatingsService;

@RestController
@RequestMapping("/catalog")
@RefreshScope
public class MovieCatalogController {
	
	@Autowired
	private MovieInfoService movieInfoService;
	
	@Autowired
	private UserRatingsService userRatingsService;
	
	@Value("${my.greeting:default message}")
	private String greeting;
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getMovieCatalog(@PathVariable("userId") String userId){
		
		//get all rated movie ids
		UserRating userRating = userRatingsService.getUserRating(userId);		
		
		return userRating.getRatings().stream().map(rating -> {
				//for each movie id , call the movie info service
				Movie movie = movieInfoService.getMovieInfo(rating);
				
				return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
			}).collect(Collectors.toList());
	}
	
	@GetMapping("/config-parameters")
	public String getConfigParameters() {
		return greeting;
	}
	
	
	
		
	
		

		
	

}
