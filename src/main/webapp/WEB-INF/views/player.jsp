<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<table>
	<tr><td>Player: ${player.lastName} ${player.firstName}</td><td></td></tr>
	<tr><td>Career start: ${player.careerStartDate}</td><td></td></tr>
	<tr><td>Age: ${player.age}</td><td></td></tr>
	<tr><td>Team: <a href="team?id=${player.teamId}">${player.teamName}</a></td><td></td></tr>
	<tr><td></td><td align="right"><a href="player?update=true&id=${player.id}">Update player info</a></td></tr>
	<tr><td></td><td align="right"><a href="player?transfer=true&id=${player.id}">Transfer player</a></td></tr>
</table>
<br/><br/>

<%@include file="footer.jsp" %>

