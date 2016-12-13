package com.axway.academy.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.axway.academy.Movies;

public class CreateMovie {
	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("MoviesCatalog");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Movies movie = new Movies();
		movie.setId(1);
		movie.setTitle("Troy");
		movie.setStar("Brad Pitt");
		movie.setDirector("Steven Spielberg");
		movie.setBudget(2300000);

		entitymanager.persist(movie);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}
}
