package com.axway.academy.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.axway.academy.Movies;

public class MovieDao implements MovieDaoLocal {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addMovie(Movies movie) {
		em.persist(movie);
		
	}

	@Override
	public void editMovie(Movies movie) {
		em.merge(movie);
		
	}

	@Override
	public void deleteMovie(int movieId) {
		em.remove(getMovie(movieId));
		
	}

	@Override
	public Movies getMovie(int movieId) {
		
		return em.find(Movies.class, movieId);
	}
	
	public List<Movies> getAllMovies() {
		return em.createNamedQuery("Movies.getAll").getResultList();
	}
	
	

}
