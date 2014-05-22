<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<html>
<head>
<title>Hello Facebook</title>
</head>
<body>

	<form action="checkboxes" method="post">
		<c:forEach var="i" items="${friends}">
			<input type="checkbox" name="names[]" value="${i}" />${i} <br>
		</c:forEach>
		<p>
			<input type="submit" value="Submit" /> <input type="reset"
				value="Reset" />
		</p>
	</form>
</body>
</html>