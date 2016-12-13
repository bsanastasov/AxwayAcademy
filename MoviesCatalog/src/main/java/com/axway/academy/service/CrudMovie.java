package com.axway.academy.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.axway.academy.Movies;

public class CrudMovie {
	public static void addMovie() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("MoviesCatalog");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Movies movie = new Movies();
		entitymanager.persist(movie);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}
}
