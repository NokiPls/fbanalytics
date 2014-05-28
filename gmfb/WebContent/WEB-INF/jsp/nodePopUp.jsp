<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Hello Facebook</title>
<meta charset="utf-8">

</head>
<body>


	<image src="https://graph.facebook.com/${profile.id}/picture?redirect=1&height=90&type=normal&width=90"/>
	<br>
	Name: ${profile.name}
	<br>
	Nick Name: ${profile.username}
	<br>
	
	Degree centrality:
	<br>
	Betwennes centrality:
	<br>
	Closeness centrality:
	<br>
	
	Normalized Degree centrality:
	<br>
	Normalized Betwennes centrality:
	<br>
	Normalized Closeness centrality:
	<br>




</body>
</html>