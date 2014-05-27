<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Hello Facebook</title>
<meta charset="utf-8">
<style>
.link {
	stroke: #ccc;
}

.node text {
	pointer-events: none;
	font: 10px sans-serif;
}
</style>
</head>
<body>

	Hello,
	<span th:text="${facebookProfile.name}">${facebookProfile.name}</span>!
	<br> You have selected
	<c:forEach var="i" items="${Friends}" varStatus="status">
		<ul>
			<li><c:out value="${i.name}"></c:out></li>
		</ul>
	</c:forEach>
	<script src="http://d3js.org/d3.v3.min.js"></script>
	<script>
		var data = eval('(' + '${graph}' + ')');

		var width = 960, height = 500;

		var svg = d3.select("body").append("svg").attr("width", width).attr(
				"height", height);

		var force = d3.layout.force().gravity(.05).distance(100).charge(-100)
				.size([width, height]);

		<!--dot.on("click", click);
		function click(d) {
			console.log(d.title); //considering dot has a title attribute
		}
		-->

		force.nodes(data.nodes).links(data.links).start();

		var link = svg.selectAll(".link").data(data.links).enter().append(
				"line").attr("class", "link");

		var node = svg.selectAll(".node").data(data.nodes).enter().append("g")
				.attr("class", "node").call(force.drag);

		node.append("image").attr("xlink:href",
				"https://github.com/favicon.ico").attr("x", -8).attr("y", -8)
				.attr("width", 16).attr("height", 16);

		node.append("text").attr("dx", 12).attr("dy", ".35em").text(
				function(d) {
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




</body>
</html>