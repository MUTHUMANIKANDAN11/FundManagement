<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.management.model.User , java.util.List"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Manage Users</h1>
	
	<%
	User user = (User) session.getAttribute("User");
	request.setAttribute("user", user);
	
	if (user == null) {
	    response.sendRedirect("index.jsp");
	    return;
	}
	%>
	
	<form action="dashboard">
		<Button type="submit" >Back</Button>
	</form>
	
	<h2>Auditors</h2>
	
	<c:forEach var="auditor" items="${Auditors}">
	    <form action="Symposium" method="post">
	        <div>${auditor.name}</div>
	
	        <input type="hidden" name="url" value="/SymposiumDetails.jsp"/>
	        <button type="submit">view profile</button>
	    </form>
	</c:forEach>

	<h2>Presidents</h2>
	
	<c:forEach var="president" items="${Presidents}">
	    <form action="Symposium" method="post">
	        <div>${president.name}</div>
	
	        <input type="hidden" name="url" value="/SymposiumDetails.jsp"/>
	        <button type="submit">view profile</button>
	    </form>
	</c:forEach>

	
</body>
</html>