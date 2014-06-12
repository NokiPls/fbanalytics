<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Personal Profile</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/css/fonts.css" />" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial" rel="stylesheet" />

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
			document.getElementById("disconnessionForm").submit();

		});
	}
</script>
</head>
<body>
	<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#">Facebook Analytics</a></h1>
			<div id="menu">
				<ul>
					<li class="active"><a href="#"  title="">Homepage</a></li>
					
					<li><a href="javascript:logoutFacebook()" title="">Disconnect</a></li>
			
					<li><a href="#"  title="">About Us</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
		<div id="page-wrapper">
	<div id="welcome" class="container">
	<div class="title">
	<h2>Personal Profile</h2>
	</div>
				<image src="https://graph.facebook.com/${facebookProfile.id}/picture?redirect=1&height=200&type=normal&width=200" />
				<h3>
					Name: ${facebookProfile.name}
				</h3>
				ID: ${facebookProfile.id}<br>
				Gender: ${facebookProfile.gender}<br>
				Locale:${facebookProfile.locale}<br>

				<a href="List" class="button">See Your Friends</a>

				<form id="disconnessionForm" action="connect/facebook" method="POST">
					<input
						type="hidden" name="_method" value="delete"> 
				</form>
				</div>
</div>
<div class="wrapper">

	
	</div>
</div>
<div id="copyright" class="container">
	<p>POLIMI - Advanced Web Technology</p>

</div>
</body>
</html>
