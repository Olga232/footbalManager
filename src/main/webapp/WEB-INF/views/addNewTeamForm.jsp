<%@ page pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Objects" %>

<%@include file="header.jsp" %>

<c:choose>
	<c:when test="${result == null || result == \"\"}">
		<center>
			<h4><font color="#C0C0C0">Fill in the form to add a new team:</font></h4><br/>
		</center>
		
		<table>
		<form action="/team" method="post">
		    <tr><td>Name of team: </td><td><input type="text" name="name" 
					value="<%=(request.getParameter("name") != null) ? request.getParameter("name") : ""%>"/></td></tr>
			<tr><td>City: </td><td><input type="text" name="city"
					value="<%=(request.getParameter("city") != null) ? request.getParameter("city") : ""%>"/></td></tr>
			<tr><td>Country: </td><td><input type="text" name="country"
					value="<%=(request.getParameter("country") != null) ? request.getParameter("country") : ""%>"/></td></tr>
			<tr><td>Transfer commission (%): </td><td><input type="number" size="2" min="0" max="10" name="commission"
					value="<%=(request.getParameter("commission") != null) ? request.getParameter("commission") : 0%>"/></td></tr>
			<tr><td>Money: </td><td><input type="number" step="0.01" min="0" name="money"
					value="<%=(request.getParameter("money") != null) ? request.getParameter("money") : 0%>"/></td></tr>
		    <tr><td> </td><td align="right"><input type="submit" value="Add"/></td></tr>
		</form>
		</table>
	
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
