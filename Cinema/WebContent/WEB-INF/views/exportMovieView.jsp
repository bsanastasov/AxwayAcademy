<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Export Movie</title>
 </head>
 
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="loggedUserMenu.jsp"></jsp:include>
    
    <h3>Export Movie</h3>
    
    <p style="color: red;">${errorString}</p>
    <a href="movieListLoggedUser">Back to Movie List</a>
    
    <jsp:include page="_footer.jsp"></jsp:include>
    
 </body>
</html>