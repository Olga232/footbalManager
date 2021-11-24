<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<table>
	<tr><td colspan="2">Team: ${team.name}</td><td></td></tr>
	<tr><td rowspan="7"><img src="static/images/${team.id}.jpg" width="250" height="100%"/></td><td></td></tr>
	<tr><td></td><td>City: ${team.city}</td></tr>
	<tr><td></td><td>Country: ${team.country}</td>
	<tr><td></td><td>Transfer commission: ${team.commission}%</td>
	<tr><td></td><td>Money: ${team.money}</td></tr>
	<tr><td></td><td><h4 font color="#C0C0C0">Players:</h4>
		<ul>
			<c:forEach var="player" items="${listOfPlayers}">
				<li font color="#C0C0C0"><a href="player?id=${player.id}">${player.lastName} ${player.firstName}</a></li>
			</c:forEach>
		</ul>
	</td></tr>
	<tr><td></td><td><a href="team?update=true&id=${team.id}">Update team info</a></td></tr>
</table>
<br/><br/>

<%@include file="footer.jsp" %>

