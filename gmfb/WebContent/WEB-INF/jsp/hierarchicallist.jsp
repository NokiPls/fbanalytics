<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Hello Facebook</title>
</head>
<body>

		Hello, <span th:text="${facebookProfile.name}">${facebookProfile.name}</span>!
	<form action="connect/facebook" method="POST">
		<input type="hidden" name="_method" value="delete"> <input
			type="submit" value="Disconnect">
	</form>




</body>
</html>