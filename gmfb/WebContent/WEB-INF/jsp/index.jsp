<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Click to say hi!</title>
</head>
<body>
	<spring:url value="/sayHelloWorld" var="sayHelloWorld"/>
	<a href="${sayHelloWorld}">click me, we have cookies</a>
</body>
</html>