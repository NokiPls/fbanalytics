<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Hello Facebook</title>
		<link href="<c:url value="/css/style.css" />" rel="stylesheet"></head>

</head>
<body>
<div id="wrapper">
		<div id="headerwrap">
			<div id="header">
				<h1>Facebook Analyzer</h1>
			</div>
		</div>
		<div id="contentwrap">
			<div id="content">
	Hello,
	<span th:text="${facebookProfile.name}">${facebookProfile.name}</span>!
	<br><br>
	<form action="openGraph" method="POST" class="form1" >
		<input type="submit" value="Open Graph" class="button">
	</form>
	<form action="connect/facebook" method="POST"class="form1" >
		<input type="hidden" name="_method" value="delete">
		 <input type="submit" value="Disconnect"class="button">
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
	</div>
		</div>
		<div id="footerwrap">
			<div id="footer">
				<p>POLIMI - Advanced Web Technologies project</p>
			</div>
		</div>
	</div>



</body>
</html>