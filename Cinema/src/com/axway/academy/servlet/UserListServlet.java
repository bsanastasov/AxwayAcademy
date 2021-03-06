package com.axway.academy.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axway.academy.utils.DBUtils;
import com.axway.academy.utils.Utils;
import com.axway.academy.beans.UserAccount;

@WebServlet(urlPatterns = { "/userList" })
public class UserListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UserListServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = Utils.getStoredConnection(request);
 
        String errorString = null;
        List<UserAccount> list = null;
        try {
            list = DBUtils.queryUser(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
   
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("userList", list);
    
        // Forward to /WEB-INF/views/movieListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/userListView.jsp");
        dispatcher.forward(request, response);
      
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}

