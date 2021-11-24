<%@ page pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Objects" %>

<%@include file="header.jsp" %>

<c:choose>
	<c:when test="${result == null || result == \"\"}">
		<center>
			<h4><font color="#C0C0C0">Fill in the form to add a new player:</font></h4><br/>
		</center>
		
		<form action="/player" method="post">
		<table>
		    <tr><td>Last name: </td><td><input type="text" name="lastName" 
					value="<%=(request.getParameter("lastName") != null) ? request.getParameter("lastName") : ""%>"/></td></tr>
			<tr><td>First name: </td><td><input type="text" name="firstName"
					value="<%=(request.getParameter("firstName") != null) ? request.getParameter("firstName") : ""%>"/></td></tr>
			<tr><td>Career start date: </td><td><input type="date" name="careerStartDate"
					value="<%=(request.getParameter("careerStartDate") != null) ? request.getParameter("careerStartDate") : null%>"/></td></tr>
			<tr><td>Age (years): </td><td><input type="number" size="2" min="18" max="60" name="age"
					value="<%=(request.getParameter("age") != null) ? request.getParameter("age") : 18%>"/></td></tr>
			<tr><td>Team: </td><td><select name="teamId">
				<c:forEach var="team" items="${listOfTeams}">
					<option value="${team.id}">${team.name}</option>
				</c:forEach>
			</select></td></tr>
		    <tr><td> </td><td align="right"><input type="submit" value="Add"/></td></tr>
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
