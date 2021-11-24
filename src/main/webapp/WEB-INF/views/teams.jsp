<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<table>
<c:forEach var="team" items="${listOfTeams}">
	<tr><td colspan="2"><a href="team?id=${team.id}">${team.name}</a></td><td></td></tr>
	<tr><td rowspan="4"><a href="team?id=${team.id}"><img src="static/images/${team.id}.jpg" width="120" height="100%"/></a></td><td></td></tr>
	<tr><td></td><td>City: ${team.city}</td></tr>
	<tr><td></td><td>Country: ${team.country}<td></tr>
	<tr><td></td><td><input onclick="deleteTeamById('${team.id}')" type="button" value="Remove team" hspace="5"/></td></tr>
    <tr><td><br/><br/></td><td></td></tr>
</c:forEach>
</table>

<%@include file="footer.jsp" %>

