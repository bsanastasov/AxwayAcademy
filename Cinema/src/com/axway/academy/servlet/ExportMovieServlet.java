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
 
@WebServlet(urlPatterns = { "/exportMovie" })
public class ExportMovieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ExportMovieServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = Utils.getStoredConnection(request);
 
        String idStr = (String) request.getParameter("id");
        int id = Integer.parseInt(idStr);
       // Movie movie = null;
 
        String errorString = null;
 
        try {
        	String filename = "movieWithId".concat(idStr);
            DBUtils.exportMovie(conn, id, filename);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
         
        // If no error.
        // The movie does not exist to edit.
        // Redirect to movietList page.
        if (errorString != null) {
            response.sendRedirect(request.getServletPath() + "/movieListLoggedUser");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
     //   request.setAttribute("movie", movie);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/exportMovieView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
