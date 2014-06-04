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
				
			</div>
		</div>
		<div id="contentwrap">
		<div id="content">
			
			<h3>	Connect to facebook</h3>
					
				<form action="facebook" method="POST">
					<div class="formInfo">
							<button type="submit"  class="button">Connect </button>
				</form>
				
			<p>You aren't connected to Facebook yet. Click the button to
					connect this WebApp with your Facebook account.</p>
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