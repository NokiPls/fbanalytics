<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Friends List</title>
<link href="<c:url value="/css/grid.css" />" rel="stylesheet">
</head>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>

<body>
	<div id="wrapper">
		<div id="header-wrapper">
			<div id="header" class="container">
				<div id="logo">
					<h1>
						<a href="#">Facebook Analytics</a>
					</h1>
					<div id="menu">
						<ul>
							<li class="active"><a href="#" title="">Homepage</a></li>
							<li><a href="javascript:logoutFacebook()" title="">Log
									Out</a></li>
							<li><a href="#" title="">About Us</a></li>

						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="page-wrapper">
			<div id="welcome" class="container">
				Mr ${facebookProfile.name}, here's the list of your friends :)<br>
				<form action="checkboxes" method="post">
					<input type="submit" value="Fetch Common Friends" class="button" /> <input
						type="reset" value="Reset" class="button" /> <br>
					<ul class="checkbox-grid">
						<c:forEach var="i" items="${names}" varStatus="status">
							<li><input type="checkbox" name="id[]"
								value="${id[status.index]}" />${i}</li>
						</c:forEach>
					</ul>
				</form>

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