package com.axway.academy.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.axway.academy.beans.UserAccount;
import com.axway.academy.utils.DBUtils;
import com.axway.academy.utils.Utils;



@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {
 
   public CookieFilter() {
   }
 
   @Override
   public void init(FilterConfig fConfig) throws ServletException {
 
   }
 
   @Override
   public void destroy() {
 
   }
 
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
           throws IOException, ServletException {
       HttpServletRequest req = (HttpServletRequest) request;
       HttpSession session = req.getSession();
 
       UserAccount userInSession = Utils.getLoginedUser(session);
    
       if (userInSession != null) {
           session.setAttribute("COOKIE_CHECKED", "CHECKED");
           chain.doFilter(request, response);
           return;
       }
 
    
       // Connection was created in JDBCFilter.
       Connection conn = Utils.getStoredConnection(request);
 
  
       // Flag check cookie
       String checked = (String) session.getAttribute("COOKIE_CHECKED");
       if (checked == null && conn != null) {
           String userName = Utils.getUserNameInCookie(req);
           try {
               UserAccount user = DBUtils.findUser(conn, userName);
               Utils.storeLoginedUser(session, user);
           } catch (SQLException e) {
               e.printStackTrace();
           }
    
           // Mark checked.
           session.setAttribute("COOKIE_CHECKED", "CHECKED");
       }
 
       chain.doFilter(request, response);
   }
 
}