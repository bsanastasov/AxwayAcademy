package com.axway.academy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries(@NamedQuery(name="Movies.getAll", query="SELECT e FROM Movies e"))
public class Movies {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;
	private String title;
	private String star;
	private String director;
	private double budget;
	public Movies(int id, String title, String star, String director, double budget) {
		super();
		this.id = id;
		this.title = title;
		this.star = star;
		this.director = director;
		this.budget = budget;
	}

	public Movies() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}




}

