<%@ page pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Objects" %>

<%@include file="header.jsp" %>

<center>
	<h4><font color="#C0C0C0">Fill in the form to update player info:</font></h4><br/>
</center>
		
<form id="form">
<table>
	<tr><td>Last name: </td><td><input type="text" id="ln${player.id}" name="lastName" value="${player.lastName}"/></td></tr>
	<tr><td>First name: </td><td><input type="text" id="fn${player.id}" name="firstName" value="${player.firstName}"/></td></tr>
	<tr><td>Career start date: </td><td><input type="date" id="date${player.id}" name="careerStartDate" value="${player.careerStartDate}"/></td></tr>
	<tr><td>Age (years): </td>
		<td><input type="number" id="age${player.id}" size="2" min="18" max="60" name="age" value="${player.age}"/></td></tr>
   <tr><td></td><td align="right">
   		<input onclick="updatePlayerInfo('${player.id}', 'ln${player.id}', 'fn${player.id}', 'date${player.id}', 'age${player.id}')" 
   		type="button" value="Update" hspace="5"/></td></tr>
</table>
</form>

<%@include file="footer.jsp" %>
