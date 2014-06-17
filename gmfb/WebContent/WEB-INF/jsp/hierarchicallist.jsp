<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Hierarchical List</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/css/fonts.css" />" rel="stylesheet">
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial"
	rel="stylesheet" />


<script src="http://connect.facebook.net/en_US/all.js"></script>
<script type="text/javascript">
	function Facebook() {
		FB.init({
			appId : '279105595599278',
			cookie : true,
			status : true,
			xfbml : true
		});
		FB.getLoginStatus(function(response) {
			if (response.status === 'unknown') {// && response.status !== 'not_authorized'

				document.getElementById("disconnectionForm").submit();

			}
		});
	}
	window.onload = Facebook;
	function logoutFacebook() {
		FB.logout(function(response) {
			console.log("Here logout response", response);
			document.getElementById("disconnectionForm").submit();

		});
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
				<div id="menu">
					<ul>
						<li class="active"><a href="/gmfb" title="">Homepage</a></li>
						<li><a href="#" title="">About Us</a></li>
						<li><a href="javascript:logoutFacebook()">Disconnect</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="page-wrapper">
		<div id="welcome" class="container">
			<div class="title">
				<h2>Selected Friends</h2>
			</div>
			Hello, <span>${facebookProfile.name}</span>! <br> <br>
			<form action="List" method="POST" class="form1">
				<input type="submit" class="button" value="Back to your friends" formaction="List"/>
			</form>
			
			<form action="openGraph" method="POST" class="form1">
				<input
					type="submit" value="Open Graph" class="button">
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
			<form id="disconnectionForm" action="/gmfb" method="POST"></form>

		</div>
	</div>
	<div class="wrapper"></div>
	<div id="copyright" class="container">
		<p>POLIMI - Advanced Web Technology</p>

	</div>
</body>
</html>
