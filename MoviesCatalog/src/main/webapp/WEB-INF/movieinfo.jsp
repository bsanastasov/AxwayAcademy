
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> Movies</title>
		<body>
			<h1> Movie Information </h1>
			<table>
				<tr>
					<td> Movie ID</td>
					<td><input type="text" name="movieId" value="${movie.movieId}"/></td>
				</tr>
				<tr>
					<td> Title </td>
					<td> <input type="text" name="title" value="${movie.title}"/></td>
				</tr>
				<tr>
					<td> Star </td>
					<td><input type="text" name="star" value="${movie.star}"/></td>
				
				</tr>
				<tr>
					<td> Director </td>
					<td><input type="text" name="director" value="${movie.director}"/></td>
				
				</tr>
				<tr>
					<td> Budget </td>
					<td> <input type="text" name="budget" value="${movie.budget}"/></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="submit" name="action" value="Add">
						<input type="submit" name="action" value="Edit">
						<input type="submit" name="action" value="Delete">
						<input type="submit" name="action" value="Search">
					</td>
				</tr>
			</table>
			<br>
			<table border="1">
			
			</table>
		</body>
	</head>
</html>