package com.javaexpress.moviecatalogservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javaexpress.moviecatalogservice.model.Rating;
import com.javaexpress.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingsService {
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "fallBackUserRating")
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
	}
	
	
	public UserRating fallBackUserRating(String userId) {
		UserRating userRating = new UserRating();
		List<Rating> ratings = Arrays.asList(new Rating(0,0));
		userRating.setRatings(ratings);
		return userRating;
	}
}
