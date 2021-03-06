<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Movie List</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="admin_menu.jsp"></jsp:include>
 
    <h3>Movie List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Id</th>
          <th>Title</th>
          <th>Star</th>
          <th>Director</th>
          <th>Budget</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${movieList}" var="movie" >
          <tr>
             <td>${movie.id}</td>
            <td>${movie.title}</td>
             <td>${movie.star}</td>
             <td>${movie.director}</td>
             <td>${movie.budget}</td>
             <td>
                <a href="editMovie?id=${movie.id}">Edit</a>
             </td>
             <td>
                <a href="deleteMovie?id=${movie.id}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="createMovie" >Create Movie</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>