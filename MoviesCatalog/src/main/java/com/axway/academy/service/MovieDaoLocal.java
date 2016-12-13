package com.axway.academy.service;

import java.util.List;

import com.axway.academy.Movies;

public interface MovieDaoLocal {
	
	void addMovie(Movies movie);
	
	void editMovie(Movies movie);
	
	void deleteMovie(int movieId);
	
	Movies getMovie(int movieId);
	
	 List<Movies> getAllMovies();
	

}
