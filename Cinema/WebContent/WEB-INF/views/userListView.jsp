<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User List</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="admin_menu.jsp"></jsp:include>
 
    <h3>User List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>UserName</th>
          <th>Password</th>
          <th>Address</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${userList}" var="user" >
          <tr>
             <td>${user.userName}</td>
             <td>${user.password}</td>
             <td>${user.address}</td>

             <td>
                <a href="deleteUser?userName=${user.userName}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>

 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>