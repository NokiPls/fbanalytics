<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Hello Facebook</title>
</head>
<body>

	Hello,
	<span th:text="${facebookProfile.name}">${facebookProfile.name}</span>!
	<form action="connect/facebook" method="POST">
		<input type="hidden" name="_method" value="delete"> <input
			type="submit" value="Disconnect"> <input type="submit"
			value="Open Graph">
	</form>

	<c:forEach var="i" items="${Friends}" varStatus="status">
		<ul>
			<li><c:out value="${i.name}"></c:out></li>
			<ul>
				<c:forEach var="k" items="${i.commonFriends}" varStatus="status_k">
					<li><c:out value="${k.name}"></c:out></li>
				</c:forEach>
			</ul>
		</ul>
	</c:forEach>




</body>
</html>