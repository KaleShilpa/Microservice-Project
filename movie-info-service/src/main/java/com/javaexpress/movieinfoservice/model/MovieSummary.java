package com.javaexpress.movieinfoservice.model;

public class MovieSummary {
	
	private int movieId;
	private String title;
	private String overview;
	
	public MovieSummary() {
		
	}
	public MovieSummary(int movieId, String title, String overview) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.overview = overview;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	

}
