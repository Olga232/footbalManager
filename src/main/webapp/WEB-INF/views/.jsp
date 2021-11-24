<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Objects" %>

<%@include file="header.jsp" %>

<c:choose>
	<c:when test="${result == null || result == \"\"}">
		<center>
			<h4><font color="#C0C0C0">Форма регистрации</font></h4><br/>
		</center>
		
		<table align="left">
		<form action="user?registration" method="post">
		    <tr><td>Login (email):</td><td><input type="email" name="login" 
					value="<%=(request.getParameter("login") != null) ? request.getParameter("login") : ""%>"/></td></tr>
			<tr><td>Name:</td><td><input type="text" name="name"
					value="<%=(request.getParameter("name") != null) ? request.getParameter("name") : ""%>"/></td></tr>
		    <tr><td>Password:</td><td><input type="password" name="password" 
					value="<%=(request.getParameter("password") != null) ? request.getParameter("password") : ""%>"/></td></tr>
		    <tr><td>Repeat password:</td><td><input type="password" name="passwordRepeat"
					value="<%=(request.getParameter("passwordRepeat") != null) ? request.getParameter("passwordRepeat") : ""%>"/></td></tr>
		    <tr><td>Gender:</td><td>M<input type="radio" name="gender" value="M" <%=(Objects.equals("M", request.getParameter("gender"))) ? " checked=\"true\"" : ""%>/>
									F<input type="radio" name="gender" value="F" <%=(Objects.equals("F", request.getParameter("gender"))) ? " checked=\"true\"" : ""%>/>
									</td></tr>
		    <tr><td>Region:</td><td><select name="region">
				<option value="" <%=(Objects.equals("", request.getParameter("region"))) ? " selected=\"true\"" : ""%>> </option>
		        <option value="DNR" <%=(Objects.equals("DNR", request.getParameter("region"))) ? " selected=\"true\"" : ""%>>ДНР</option>
		        <option value="LNR" <%=(Objects.equals("LNR", request.getParameter("region"))) ? " selected=\"true\"" : ""%>>ЛНР</option>
		        <option value="Crimea" <%=(Objects.equals("Crimea", request.getParameter("region"))) ? " selected=\"true\"" : ""%>>Крым</option>
		    </select></td></tr>
		    <tr><td>Comment:</td><td><textarea rows=10 cols=20 name="comment"/><%=(request.getParameter("comment") != null) ? request.getParameter("comment") : ""%></textarea></td></tr>
		    <tr><td>I agree to install an Amigo Browser:</td><td><input type="checkbox" name="browser" checked="true"/></td></tr>
		    <tr><td> </td><td align="right"><input type="submit" value="SEND"/></td></tr>
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
