<%@ page pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Objects" %>

<%@include file="header.jsp" %>

<c:choose>
	<c:when test="${result == null || result == \"\"}">
		<center>
			<h4><font color="#C0C0C0">Transfer player:</font></h4><br/>
		</center>
		
		<form action="/player" method="post">
		<table>
			<tr><td></td><td><input type="hidden" id="postId" name="playerId" value="${player.id}"></td></tr>
		    <tr><td>Player: </td><td>${player.lastName} ${player.firstName}</td></tr>
			<tr><td>From team: </td><td>${player.teamName}</td></tr>
			<tr><td>To team: </td><td><select name="newTeamId">
				<c:forEach var="team" items="${listOfTeams}">
					<option value="${team.id}">${team.name}</option>
				</c:forEach>
			</select></td></tr>
		    <tr><td></td><td align="right"><input type="submit" value="Submit"/></td></tr>
		</table>
		</form>
	
		<ul>
			<c:forEach var="error" items="${errorList}">
		    	<li><c:out value="${error}" /></li>
			</c:forEach>
		</ul>
	</c:when>
	
	<c:otherwise>
		<h3>${result}</h3>
	</c:otherwise>
	
</c:choose>

<%@include file="footer.jsp" %>
