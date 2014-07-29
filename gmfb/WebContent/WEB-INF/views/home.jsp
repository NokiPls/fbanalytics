<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Personal Profile</title>
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
						<li class="active"><a href="/gmfb" title="">${facebookProfile.name}
						</a></li>
						<li><a href="aboutUs" title="">About Us</a></li>
						<li><a href="javascript:logoutFacebook()">Disconnect</a></li>
					</ul>
				</div>
			</div>
		</div>

	</div>
	<div id="page-wrapper">
		
			<div class="title">
				<h2>Personal Profile</h2>
			</div>
			<img
				src="https://graph.facebook.com/${facebookProfile.id}/picture?redirect=1&height=200&type=normal&width=200" class="imageborder" />
			<h3>
				Name: <span>${facebookProfile.name}</span>
			</h3>
			ID: <span>${facebookProfile.id}</span><br> Gender: <span>${facebookProfile.gender}</span><br>
			Locale: <span>${facebookProfile.locale}</span><br>



			<form id="disconnectionForm"
				action="${pageContext.request.contextPath}/signout">
				<a href="friendsList" class="button">See your friends</a> <input
					type="hidden" name="_method" value="delete">

			</form>
		
	</div>
	<div class="wrapper"></div>
	<div id="copyright" class="container">
		<p>POLIMI - Advanced Web Technology</p>

	</div>
</body>
</html>
