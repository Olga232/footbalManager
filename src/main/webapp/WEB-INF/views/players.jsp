<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<table>
<c:forEach var="player" items="${listOfPlayers}">
	<tr><td><a href="player?id=${player.id}">${player.lastName} ${player.firstName}</a></td></tr>
	<tr><td>Career start: ${player.careerStartDate}</td></tr>
	<tr><td>Team: ${player.teamName}<td></tr>
	<tr><td><input onclick="deletePlayerById('${player.id}')" type="button" value="Remove player" hspace="5"/></td></tr>
    <tr><td><br/><br/></td></tr>
</c:forEach>
</table>

<%@include file="footer.jsp" %>

