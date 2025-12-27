<%@ page language="java" contentType="text/html; charset=UTF-8"
import=" com.management.model.YearlyRecord, java.util.List "
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Year Report</h1>

	<form action="${pageContext.request.contextPath}/dashboard">
	    <button type="submit">Back</button>
	</form>
	
	<form action="${pageContext.request.contextPath}/hod/YearReport" method="post" >
		<input type="text" placeholder="year" name="year" >
		<button type="submit" >Click</button>
	</form>
	

	<%
		if(request.getAttribute("currentRecords") != null){
			%>
			
		<h2>Symposium Records</h2>
		
		<c:forEach var="record" items="${currentRecords}">
	        <h3>Symposium: ${record.symposium.title}</h3>
	        
	        <p><strong>Department ID:</strong> ${record.symposium.dept_id}</p>
	        <p><strong>Academic Year:</strong> ${record.symposium.academic_year}</p>
	        
	        <p><strong>Start Date:</strong> ${record.symposium.start_date}</p>
	        <p><strong>End Date:</strong> ${record.symposium.end_date}</p>
	        
	        <p><strong>Sponsor Deadline:</strong> ${record.symposium.sponsor_deadLine}</p>
	        <p><strong>Claim Deadline:</strong> ${record.symposium.claim_deadLine}</p>
	        
	        <p><strong>President ID:</strong> ${record.symposium.president_id}</p>
	        <p><strong>Auditor ID:</strong> ${record.symposium.auditor_id}</p>
	        
	        <p><strong>Allocation:</strong> ${record.symposium.allocation}</p>
	        <p><strong>Carry Forward:</strong> ${record.symposium.carry_forward}</p>
	        
	        <p><strong>Total Fund:</strong> ${record.symposium.allocation + record.symposium.carry_forward}</p>
	        
	        <br>
		</c:forEach>
				
			<%
		}
	%>
	
</body>
</html>