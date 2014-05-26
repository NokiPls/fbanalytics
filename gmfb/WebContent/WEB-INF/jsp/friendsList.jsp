<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Hello Facebook</title>
</head>
<body>
Hello, <span th:text="${facebookProfile.name}">${facebookProfile.name}</span>
	<form action="checkboxes" method="post">
		<input type="submit" value="Show Common Friends" /> <input type="reset"
			value="Reset" /> <br>
		<c:forEach var="i" items="${names}" varStatus="status">
			<input type="checkbox" name="id[]" value="${id[status.index]}" />${i} <br>
		</c:forEach>
		<p></p>
	</form>
</body>
</html>