<%@page import="java.awt.geom.Path2D"%>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.management.model.User, com.management.model.Symposium, java.util.List"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Dashboard</h1>
	<%
    User user = (User) session.getAttribute("User");
	request.setAttribute("user", user);
	
	if (user == null) {
	    response.sendRedirect("index.jsp");
	    return;
	} else if (user.getRole().equals("HOD")) {
		
	%>
	<form action="${pageContext.request.contextPath}/logout">
		<button type="submit">logout</button>
	</form>
	<h3>${user.getRole()}</h3>
		
	<form action="${pageContext.request.contextPath}/hod/SymposiumForm" method="Post" >
	    <button type="submit" >Add Symposium</button>	    
    </form>
    
    <form action="hod/ManageUsers">
		<input type="hidden" value="${user.getDept_id()}" name="dept_id" >
    	<button type="submit" >manage users</button>
    </form>
    
    <form action="hod/YearReportForm" method="post" >
    	<button type="submit" >Year Report</button>
    </form>
	
	<c:forEach var="symp" items="${Symps}">
		<form action="hod/Symposium">
			<input type="hidden" value="${symp.symp_id}" name="symp_id" >
			<input type="hidden" value="/SymposiumDetails.jsp" name="url" >
			<button type="submit" >
		        <p>Title : ${symp.title} </p>
		        <p>Academic Year : ${symp.academic_year} </p>
		        <p>Allocation : ${symp.allocation} </p>				
			</button>
		</form>
    </c:forEach>
	    
	
	<%
	}
	%>

	
</body>
</html>