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
						<li class="active"><a href="/gmfb" title="">${name}</a></li>
						<li><a href="aboutUs" title="">About Us</a></li>
						<li><a href="javascript:logoutFacebook()">Disconnect</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="page-wrapper">
		<a href="/gmfb">Personal Profile</a> > <a href="friendsList">Your
			Friends</a> > Selected Friends
			<div class="title">
				<h2>Selected Friends</h2>
			</div>


			<form action="openGraph" method="POST" class="form1">
				<input type="submit" value="Open Graph" class="button">
			</form>
			<br> <br>
			<c:forEach var="i" items="${friends}" varStatus="status">
				<ul>
					<h3>
						<c:out value="${i.name}"></c:out>
					</h3>
					<ul>
						<c:forEach var="k" items="${i.commonFriends}" varStatus="status_k">
							<li><c:out value="${k.name}"></c:out></li>
						</c:forEach>
					</ul>
				</ul>
			</c:forEach>
			<form id="disconnectionForm"
				action="${pageContext.request.contextPath}/signout"></form>

	</div>
	<div class="wrapper"></div>
	<div id="copyright" class="container">
		<p>POLIMI - Advanced Web Technology</p>

	</div>
</body>
</html>
