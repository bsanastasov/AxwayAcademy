<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Edit Movie</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Edit Movie</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <c:if test="${not empty movie}">
       <form method="POST" action="doEditMovie">
          <input type="hidden" name="id" value="${movie.id}" />
          <table border="1">
             <tr>
                <td>ID</td>
                <td style="color:red;">${movie.id}</td>
             </tr>
             <tr>
                <td>Title</td>
                <td><input type="text" name="title" value="${movie.title}" /></td>
             </tr>
             <tr>
                <td>Star</td>
                <td><input type="text" name="star" value="${movie.star}" /></td>
             </tr>
              <tr>
                <td>Director</td>
                <td><input type="text" name="director" value="${movie.director}" /></td>
             </tr>
              <tr>
                <td>Star</td>
                <td><input type="text" name="budget" value="${movie.budget}" /></td>
             </tr>
             <tr>
                <td colspan = "2">
                    <input type="submit" value="Submit" />
                    <a href="${pageContext.request.contextPath}/movieList">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
    </c:if>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>