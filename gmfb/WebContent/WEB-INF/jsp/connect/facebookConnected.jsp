<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><html>
<head>
		<title>Connected to Facebook</title>
			<link href="<c:url value="/css/style.css" />" rel="stylesheet">
	<link href="<c:url value="/css/fonts.css" />" rel="stylesheet">
	<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial" rel="stylesheet" />
	
      </head>
	<body>
	<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#">Facebook Analytics</a></h1>
			<div id="menu">
				<ul>
					<li class="active"><a href="#" title="">Homepage</a></li>
					<li><a href="#"  title="">About Us</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div id="page-wrapper">
	<div id="welcome" class="container">
				<p>
					You are now connected to your Facebook account. <br> <a
						href="/gmfb" class="button">See your Profile</a>
				</p>
		
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