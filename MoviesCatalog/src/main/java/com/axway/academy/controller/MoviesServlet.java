package com.axway.academy.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axway.academy.Movies;
import com.axway.academy.service.MovieDaoLocal;

/**
 * Servlet implementation class MoviesServlet
 */
@WebServlet("/MoviesServlet")
public class MoviesServlet extends HttpServlet {
	
	@EJB
	private MovieDaoLocal movieDao;
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MoviesServlet() {
        // TODO Auto-generated constructor stub
    }


	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String movieIdStr = request.getParameter("movieId");
		int movieId = movieIdStr.equals("") ? 0 : Integer.parseInt(movieIdStr);
		String title = request.getParameter("title");
		String star = request.getParameter("star");
		String director = request.getParameter("director");
		String budgetStr = request.getParameter("budget");
		double budget = budgetStr.equals("") ? 0 : Double.parseDouble(budgetStr);
		
		Movies movie = new Movies(movieId, title, star, director, budget);
		
		switch (action.toLowerCase()) {
			case "add":
				movieDao.addMovie(movie);
				break;
			case "edit":
				movieDao.editMovie(movie);
				break;
			case "delete":
				movieDao.deleteMovie(movieId);
				break;
			case "search":
				movieDao.getMovie(movieId);
				break;
			default:
				System.out.println("Not a correct command");
		}
		request.setAttribute("movie", movie);
		request.setAttribute("AllMovie", movieDao.getAllMovies());
		request.getRequestDispatcher("movieinfo.jsp").forward(request, response);
	}

}
