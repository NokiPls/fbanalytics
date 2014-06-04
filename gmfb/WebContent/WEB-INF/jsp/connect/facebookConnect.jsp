<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>fbanalytics connect to Facebook</title>
		<link href="<c:url value="/css/style.css" />" rel="stylesheet"></head>
</head>

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
				<h2>Connect to facebook</h2>
				<p>connettiti per accedere alla webapp</p>
				<form action="facebook" method="POST">
					<div class="formInfo">
						<p>
							<button type="submit"  class="button">Connect to Facebook</button>
						</p>
				</form>
				<p>You aren't connected to Facebook yet. Click the button to
					connect Spring Social Showcase with your Facebook account.</p>
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