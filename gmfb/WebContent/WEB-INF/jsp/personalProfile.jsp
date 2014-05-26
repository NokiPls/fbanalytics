<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Hello Facebook</title>
	</head>
	<body>
	<image src="https://graph.facebook.com/${facebookProfile.id}/picture?redirect=1&height=200&type=normal&width=200"/>
		<h3>Name: <span th:text="${facebookProfile.name}">${facebookProfile.name}</span></h3>
		ID: <span th:text="${facebookProfile.id}">${facebookProfile.id}</span><br>
		Gender: <span th:text="${facebookProfile.gender}">${facebookProfile.gender}</span><br>
		Locale: <span th:text="${facebookProfile.locale}">${facebookProfile.locale}</span><br>
		
		
		Click <a href="List">here</a> to see your friends.
		
		<form action="connect/facebook" method="POST">
			<input type="hidden" name="_method" value="delete">
			<input type="submit" value="Disconnect">
		</form>
	


	</body>
</html>