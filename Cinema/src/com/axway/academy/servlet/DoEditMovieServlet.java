package com.axway.academy.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axway.academy.beans.Movie;
import com.axway.academy.utils.DBUtils;
import com.axway.academy.utils.Utils;

@WebServlet(urlPatterns = { "/doEditMovie" })
public class DoEditMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoEditMovieServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = Utils.getStoredConnection(request);

		String idStr = (String) request.getParameter("id");
		String title = (String) request.getParameter("title");
		String star = (String) request.getParameter("star");
		String director = (String) request.getParameter("director");
		String budgetStr = (String) request.getParameter("budget");

		double budget = 0;

		String errorString = null;

		try {
			budget = Double.parseDouble(budgetStr);
		} catch (Exception e) {
			errorString = "Wrong number of budget. ";
			e.printStackTrace();
		}

		int id = 0;

		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
			errorString += "Wrong format of id";
			e.printStackTrace();
		}

		Movie movie = new Movie(id, title, star, director, budget);

		if (errorString == null) {
			try {
				DBUtils.updateMovie(conn, movie);
			} catch (SQLException e) {
				throw new RuntimeException("Failed to update movie due to SQLException", e);
			}
		}

		request.setAttribute("movie", movie);
	    request.setAttribute("errorString", errorString);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editMovieView.jsp");
			dispatcher.forward(request, response);
		}

		// If everything nice.
		// Redirect to the product listing page.
		else {
			response.sendRedirect(request.getContextPath() + "/movieList");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
