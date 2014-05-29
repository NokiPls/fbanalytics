<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Hello Facebook</title>
<link href="<c:url value="/css/grid.css" />" rel="stylesheet"></head>
<body>
Hello, <span th:text="${facebookProfile.name}">${facebookProfile.name}</span>
	<form action="checkboxes" method="post">
		<input type="submit" value="Show Common Friends" /> <input type="reset"
			value="Reset" /> <br>
			<ul class="checkbox-grid">
		<c:forEach var="i" items="${names}" varStatus="status">
			<li><input type="checkbox" name="id[]" value="${id[status.index]}" />${i} </li>
		</c:forEach>
		</ul>
		<p></p>
	</form>
</body>
</html>