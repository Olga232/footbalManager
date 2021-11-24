<%@ page pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Objects" %>

<%@include file="header.jsp" %>

<center>
	<h4><font color="#C0C0C0">Fill in the form to update team info:</font></h4><br/>
</center>

<form>
<table>
    <tr><td>Name of team: </td><td>${team.name}</td></tr>
	<tr><td>City: </td><td>${team.city}</td></tr>
	<tr><td>Country: </td><td>${team.country}</td></tr>
	<tr><td>Transfer commission (%): </td>
		<td><input type="number" id="com${team.id}" size="2" min="0" max="10" name="commission" value="${team.commission}"/></td></tr>
	<tr><td>Money: </td>
		<td><input type="number" id="mon${team.id}" step="0.01" min="0" name="money" value="${team.money}"/></td></tr>
    <tr><td></td><td align="right">
    <input onclick="updateTeamInfo('${team.id}', 'com${team.id}', 'mon${team.id}')" type="button" value="Update" hspace="5"/>
    </td></tr>
</table>
</form>

<%@include file="footer.jsp" %>
