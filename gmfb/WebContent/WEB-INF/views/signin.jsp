<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Connect to Facebook</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/css/fonts.css" />" rel="stylesheet">
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial"
	rel="stylesheet" />
<script type="text/javascript">
	function submit() {
		document.getElementById("connectForm").submit();
	}
</script>
</head>

<body>
	<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo">
				<h1>
					<a href="/gmfb">Facebook Analytics</a>
				</h1>
		<br><br><br>

			</div>
		</div>
	</div>
	<div id="page-wrapper">
	
			<form id="connectForm"
				action="${pageContext.request.contextPath}/signin/facebook"
				method="POST">
				<input type="submit" value="Sign in with Facebook" class="button">
				<input type="hidden" name="scope"
					value="email,publish_stream,offline_access" />
			</form>
			<p>You aren't connected to Facebook yet. Click the sign in button
				to start using this webapp :)</p>
	
	</div>
	<div id="copyright" class="container">
		<p>POLIMI - Advanced Web Technology</p>

	</div>
</body>
</html>

