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

import com.axway.academy.beans.UserAccount;
import com.axway.academy.utils.DBUtils;
import com.axway.academy.utils.Utils;

@WebServlet(urlPatterns = { "/doRegister" })
public class DoRegisterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DoRegisterServlet() {
		
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		
		
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null
                 || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = Utils.getStoredConnection(req);
            try {
              
                user = DBUtils.findUser(conn, userName, password);
                 
                if (user != null) {
                    hasError = true;
                    errorString = "There is already user with name: " + userName;
                } else {
                	
                	
                	UserAccount u = new UserAccount(userName, password, address);
                	DBUtils.addUser(conn, u);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        
        req.setAttribute("errorString", errorString);
        RequestDispatcher dispatcher //
        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registerView.jsp");

        dispatcher.forward(req, resp);

		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
}
