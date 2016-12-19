<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>Home Page</title>
  </head>
  <body>
 
     <jsp:include page="_header.jsp"></jsp:include>
     <jsp:include page="loggedUserMenu.jsp"></jsp:include>
    
      <h3>Home Page</h3>
      
      <b> This is 'imdb' application for movies </b>
      <ul>
         <li>Login</li>
         <li>Storing user information in cookies</li>
         <li>Movie List</li>
         <li>Create Movie</li>
         <li>Edit Movie</li>
         <li>Delete Movie</li>
      </ul>
 
     <jsp:include page="_footer.jsp"></jsp:include>
 
  </body>
</html>