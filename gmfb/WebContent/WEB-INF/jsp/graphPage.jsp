<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Graph</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
	<link href="<c:url value="/css/fonts.css" />" rel="stylesheet">
	<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial" rel="stylesheet" />


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
                document.getElementById("disconnectionForm").submit();

            });
          }
      </script>

<meta charset="utf-8">
<style>
.link {
	stroke: #ccc;
}

.node text {
	pointer-events: none;
	font: 12px sans-serif;
}
</style>
</head>
<body>
<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#">Facebook Analytics</a></h1>
			<div id="menu">
				<ul>
					<li class="active"><a href="#" title="">Homepage</a></li>
					<li><a href="#"  title="">About Us</a></li>
					<li><a href="javascript:logoutFacebook()" >Disconnect</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div id="page-wrapper">
	<div id="welcome" class="container">
		<div class="title">
		<h2>Graph</h2>
			</div>

				Hello, <span th:text="${facebookProfile.name}">${facebookProfile.name}</span>!
				<br> You have selected:
				<c:forEach var="i" items="${Friends}" varStatus="status">
		
			-<c:out value="${i.name}"></c:out>

				</c:forEach>
				<script src="http://d3js.org/d3.v3.min.js"></script>
				<script>
					var data = eval('(' + '${graph}' + ')');

					var width = 930, height = 650;

					var svg = d3.select("#welcome").append("svg").attr("width",
							width).attr("height", height);

					var force = d3.layout.force().gravity(.05)
							.linkDistance(150).linkStrength(0.08).distance(250)
							.charge(-60).size([width, height]);

					force.nodes(data.nodes).links(data.links).start();

					var link = svg.selectAll(".link").data(data.links).enter()
							.append("line").attr("class", "link");

					var node = svg.selectAll(".node").data(data.nodes).enter()
							.append("g").attr("class", "node").call(force.drag);

					node
							.on(
									"click",
									function(d) {
										if (d3.event.defaultPrevented)
											return;
										var p1 = 'scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no'
										var p2 = 'width=80,height=80,right=100,top=100'
										open('GraphNode?id=' + d.id, 'test', p1
												+ p2)
									});

					node
							.append("image")
							.attr(
									"xlink:href",
									function(d) {
										return "https://graph.facebook.com/"
												+ d.id
												+ "/picture?redirect=1&height=56&type=normal&width=56"
									}).attr("x", -40).attr("y", -18).attr(
									"width", 56).attr("height", 56);

					node.append("text").attr("dx", 12).attr("dy", ".35em")
							.text(function(d) {
								return d.name;
							});

					force.on("tick", function() {
						link.attr("x1", function(d) {
							return d.source.x;
						}).attr("y1", function(d) {
							return d.source.y;
						}).attr("x2", function(d) {
							return d.target.x;
						}).attr("y2", function(d) {
							return d.target.y;
						});

						node.attr("transform", function(d) {
							return "translate(" + d.x + "," + d.y + ")";
						});
					});
				</script>
				<form id="disconnectionForm" action="connect/facebook" method="POST"></form>

			</div>
			</div>
<div class="wrapper">

	
</div>
<div id="copyright" class="container">
	<p>POLIMI - Advanced Web Technology</p>

</div>
</body>
</html>
