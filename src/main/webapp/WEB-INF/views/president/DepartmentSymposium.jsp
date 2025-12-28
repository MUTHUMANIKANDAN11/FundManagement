<%@ page language="java" contentType="text/html; charset=UTF-8"
import="models.TotalFundDept, java.util.List"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		List<TotalFundDept> reports = (List<TotalFundDept>) request.getAttribute("fundReports");
	%>
	
	<form action="/FundManagement/beforeDashboardServlet" method="Post" >
		<button type="submit" >Back</button>
	</form>
	
	<h2>Department Symposium Overview</h2>

	<c:forEach var="report" items="${fundReports}">
	    <div class="symposium-card">
	        <p>Symposium Id: ${report.symposium.symp_id}</p>
	        <p>Title: ${report.symposium.title}</p>
	        <p>Department Id: ${report.symposium.dept_id}</p>
	        <p>Academic Year: ${report.symposium.academic_year}</p>
	        <p>Start Date: ${report.symposium.start_date}</p>
	        <p>End Date: ${report.symposium.end_date}</p>
	        <p>Allocation: ${report.allocation}</p>
	        <p>Carry Forward: ${report.carryForward}</p>
	        <p>Sponsors: ${report.sponsors}</p>
	        <p>Total Funds: ${report.totalFunds}</p>
	    </div>
	    <hr>
	</c:forEach>
	
</body>
</html>