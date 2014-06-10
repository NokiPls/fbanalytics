<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Personal Profile</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>

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
				<image
					src="https://graph.facebook.com/${facebookProfile.id}/picture?redirect=1&height=200&type=normal&width=200" />
				<h3>
					Name: <span th:text="${facebookProfile.name}">${facebookProfile.name}</span>
				</h3>
				ID: <span th:text="${facebookProfile.id}">${facebookProfile.id}</span><br>
				Gender: <span th:text="${facebookProfile.gender}">${facebookProfile.gender}</span><br>
				Locale: <span th:text="${facebookProfile.locale}">${facebookProfile.locale}</span><br>




				<form action="connect/facebook" method="POST">
					<a href="List" class="button">see your friends</a> <input
						type="hidden" name="_method" value="delete"> <input
						type="submit" class="button" value="Disconnect">

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