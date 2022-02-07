package com.javaexpress.ratingsdataservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.ratingsdataservice.model.Rating;
import com.javaexpress.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsController {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") int movieId) {
		return new Rating(movieId,3);
	}

	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(
				new Rating(100, 3), 
				new Rating(200, 5), 
				new Rating(300, 6));
		UserRating userRating = new UserRating();
		userRating.setRatings(ratings);
		return userRating;
	}
}
