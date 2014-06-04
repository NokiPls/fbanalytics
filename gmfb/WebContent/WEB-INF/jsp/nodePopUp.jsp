<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Hello Facebook</title>
		<link href="<c:url value="/css/style.css" />" rel="stylesheet"></head>

<meta charset="utf-8">

</head>
<body>
<

	<image src="https://graph.facebook.com/${profile.id}/picture?redirect=1&height=100&type=normal&width=100"/>
	<br>
	Name: ${profile.name}
	<br>
	Nick Name: ${profile.username}
	<br><br>
	
	Degree centrality =		${degree}
		<br>
	Normalized Degree centrality = ${norm_degree}
		<br>
	Betweenness centrality = ${betweenness}
		<br>
	Closeness centrality =  ${closeness}
		<br>
	Normalized Closeness centrality  =${norm_closeness}



</body>
</html>