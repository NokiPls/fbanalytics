<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Hello Facebook</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>

<meta charset="utf-8">

</head>
<body>

	<br>
	<br>
	<br>
	<img
		src="https://graph.facebook.com/${profile.id}/picture?redirect=1&height=140&type=normal&width=140" class="imageborder" />
	<br> Name: ${profile.name}
	<br>
	<br> Degree centr. = ${degree}
	<br> Norm. Degree centr. = ${norm_degree}
	<br> Betw. centr. = ${betweenness}
	<br> Clos. centr. = ${closeness}
	<br> Norm. Clos. centr. = ${norm_closeness}



</body>
</html>