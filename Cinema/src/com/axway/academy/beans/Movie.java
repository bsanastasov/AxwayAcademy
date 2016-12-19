package com.axway.academy.beans;

public class Movie {
	private int id;
	private String title;
	private String star;
	private String director;
	private double budget;
	public Movie(int id, String title, String star, String director, double budget) {
		super();
		this.id = id;
		this.title = title;
		this.star = star;
		this.director = director;
		this.budget = budget;
	}

	public Movie() {
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
