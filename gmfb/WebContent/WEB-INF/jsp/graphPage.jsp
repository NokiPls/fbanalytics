<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	font: 12px sans-serif;
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

		<!-- to modify graph visualization   -->
		var force = d3.layout.force().gravity(.05).linkDistance(150).linkStrength(0.08).distance(250).charge(-60)
				.size([width, height]);



		force.nodes(data.nodes).links(data.links).start();

		var link = svg.selectAll(".link").data(data.links).enter().append(
				"line").attr("class", "link");

		var node = svg.selectAll(".node").data(data.nodes).enter().append("g")
				.attr("class", "node").call(force.drag);
		
		node.on("click", function(d) {
			var p1 = 'scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no'
				var p2 = 'width=80,height=80,right=100,top=100'
				open('GraphNode?id='+ d.id, 'test', p1+p2)
});

		node.append("image").attr("xlink:href",
				function(d) { return "https://graph.facebook.com/"+ d.id +"/picture?redirect=1&height=50&type=normal&width=50"} ).attr("x", -40).attr("y", -18)
				.attr("width", 50).attr("height", 50);

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