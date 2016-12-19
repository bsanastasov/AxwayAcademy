package com.axway.academy.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns = { "/createMovie" })
public class CreateMovieServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
 
  public CreateMovieServlet() {
      super();
  }
 
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
 
      RequestDispatcher dispatcher = request.getServletContext()
              .getRequestDispatcher("/WEB-INF/views/createMovieView.jsp");
      dispatcher.forward(request, response);
 
  }
 
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }
 
}
