<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.management.model.User, com.management.model.Sponsor, java.util.List"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Sponsors</title>
</head>
<body>
	<h1>Manage Sponsors</h1>
	<%
		User user = (User) session.getAttribute("User");
		List<Sponsor> sponsors = (List<Sponsor>) request.getAttribute("sponsors");
	%>
	
	<form action="${pageContext.request.contextPath}/dashboard">
		<Button type="submit" >Back</Button>
	</form>
	<br>
	<form action="AddSponsorForm" method="post" >
		<button type="submit" >Add Sponsors</button>
	</form>
	
	<h2>Sponsors</h2>
	
	<c:forEach var="sponsor" items="${sponsors}">
	    <form action="DeleteSponsor" method="post">
	        <div>${sponsor.name}</div>
	        <div>${sponsor.contact_info}</div>
	        <input type="hidden" name="sponsor_id" value="${sponsor.sponsor_id}" />
	        <button type="submit">Delete</button>
	    </form>
	    <br>
	</c:forEach>
	
</body>
</html>