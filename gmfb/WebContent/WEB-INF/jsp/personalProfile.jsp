<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Personal Profile</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">


    <script src="http://connect.facebook.net/en_US/all.js"></script>
    <script type="text/javascript">
        function Facebook() {
          FB.init({ 
            appId:'279105595599278', cookie:true, 
            status:true, xfbml:true
          });
    
        }
        window.onload = Facebook;
        function logoutFacebook() {
            FB.logout(function (response) {
                console.log("Here logout response", response);
                document.getElementById("disconnessionForm").submit();

            });
          }
      </script>
      </head>
<body>
	<div id="wrapper">
		<div id="headerwrap">
			<div id="header">
				<h1>Facebook Analyzer</h1>
			</div>
		</div>
		<div id="contentwrap">
			<div id="content">
				<image
					src="https://graph.facebook.com/${facebookProfile.id}/picture?redirect=1&height=200&type=normal&width=200" />
				<h3>
					Name: <span th:text="${facebookProfile.name}">${facebookProfile.name}</span>
				</h3>
				ID: <span th:text="${facebookProfile.id}">${facebookProfile.id}</span><br>
				Gender: <span th:text="${facebookProfile.gender}">${facebookProfile.gender}</span><br>
				Locale: <span th:text="${facebookProfile.locale}">${facebookProfile.locale}</span><br>



				<form id="disconnessionForm" action="connect/facebook" method="POST">
					<a href="List" class="button">see your friends</a> <input
						type="hidden" name="_method" value="delete"> <input
						type="button" class="button" value="Disconnect" onclick="javascript:logoutFacebook()">

				</form>
			</div>
		</div>
		<div id="footerwrap">
			<div id="footer">
				<p>POLIMI - Advanced Web Technologies project</p>
			</div>
		</div>
	</div>


</body>
</html>