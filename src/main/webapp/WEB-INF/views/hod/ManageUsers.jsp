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
	
	boolean isAdd = false;
	%>
	
	<form action="dashboard">
		<Button type="submit" >Back</Button>
	</form>
	
	<h2>Auditors</h2>
	
	<c:forEach var="auditor" items="${Auditors}">
	    <form action="DeleteUser" method="get">
	        <div>${auditor.name}</div>
	        <div>${auditor.email}</div>
	        <input type="hidden" name="user_id" value="${auditor.user_id}" />
	        <button type="submit">Delete</button>
	    </form>
	    <br>
	</c:forEach>
	
	<%
		User auditor = (User) session.getAttribute("selected-auditor");
		if(auditor != null){
		%>
			<jsp:include page="/WEB-INF/views/hod/UserDetails.jsp">
			    <jsp:param name="title" value="Dashboard"/>
			</jsp:include>
		<%
		}
	%>

	<h2>Presidents</h2>
	
	<c:forEach var="president" items="${Presidents}">
	    <form action="DeleteUser" method="get">
	        <div>${president.name}</div>
	        <div>${president.email}</div>
			<input type="hidden" name="user_id" value="${president.user_id}" />
	        <button type="submit">Delete</button>
	    </form>
	    <br>
	</c:forEach>

	
</body>
</html>