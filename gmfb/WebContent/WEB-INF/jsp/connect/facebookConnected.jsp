<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><html>
	<head>
		<title>Hello Facebook</title>
			<link href="<c:url value="/css/style.css" />" rel="stylesheet"></head>
		
	</head>
	<body>
	<div id="wrapper">
		<div id="headerwrap">
			<div id="header">
				<h1>Facebook Analyzer</h1>
				<p>POLIMI</p>
			</div>
		</div>
		<div id="contentwrap">
			<div id="content">
		<h3>Connected to Facebook</h3>

		
			You are now connected to your Facebook account.<br>
			Click <a href="/gmfb"  class="button">here</a> to see your Profile.
		
			</div>
		</div>
		<div id="footerwrap">
			<div id="footer">
				<p>This is the footer</p>
			</div>
		</div>
	</div>
	</body>
</html>