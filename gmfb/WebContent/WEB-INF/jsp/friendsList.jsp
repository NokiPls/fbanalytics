<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns:th="http://www.thymeleaf.org">
<html>
<head>
<title>Hello Facebook</title>
</head>
<body>


	<ul>
		<h1>Form</h1>
    <form action="#" th:action="@{/friends}" th:object="${friends}" method="post">

    	<p>Id: <input type="checkbox" th:field="*{name.get(1)}" /></p>

        <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
    </form>
</body>
</html>