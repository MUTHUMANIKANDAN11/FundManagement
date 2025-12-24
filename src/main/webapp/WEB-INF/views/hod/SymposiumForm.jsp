<%@page import="java.awt.geom.Path2D"%>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.management.model.User, com.management.model.Symposium , java.util.List"
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
		User user = (User) session.getAttribute("User");
	
		int dept_id = user.getDept_id();

		List<User> presidents = (List<User>) request.getAttribute("Presidents");
		List<User> auditors = (List<User>) request.getAttribute("Auditors");
	%>

	<form action="dashboard" >
		<Button type="submit" >Back</Button>
	</form>

	<h2>Fill the details to add Symposium</h2>
    
    <p>Department : ${dept.dept_name}</p>
    
	<form action="${pageContext.request.contextPath}/AddSymposium" method="post">
		<input type="hidden" name="dept_id" value="<%= dept_id %>" ><br><br>

		
		<label>Title:</label>
		<input type="text" name="title" 
		       value="<%= request.getAttribute("title") != null ? request.getAttribute("title") : "" %>" required><br><br>
		
		<label>Academic Year:</label>
		<input type="number" name="academic_year" 
		       value="<%= request.getAttribute("academic_year") != null ? request.getAttribute("academic_year") : "" %>" required><br><br>
		
		<label>Start Date:</label>
		<input type="date" name="start_date" 
		       value="<%= request.getAttribute("start_date") != null ? request.getAttribute("start_date") : "" %>" required><br><br>
		
		<label>End Date:</label>
		<input type="date" name="end_date" 
		       value="<%= request.getAttribute("end_date") != null ? request.getAttribute("end_date") : "" %>" required><br><br>
		
		<label>Sponsor Deadline:</label>
		<input type="date" name="sponsor_deadline" 
		       value="<%= request.getAttribute("sponsor_deadline") != null ? request.getAttribute("sponsor_deadline") : "" %>" required><br><br>
		
		<label>Claim Deadline:</label>
		<input type="date" name="claim_deadline" 
		       value="<%= request.getAttribute("claim_deadline") != null ? request.getAttribute("claim_deadline") : "" %>" required><br><br>
		
		<label>Allocation:</label>
		<input type="number" step="0.01" name="allocation" 
		       value="<%= request.getAttribute("allocation") != null ? request.getAttribute("allocation") : "" %>" required><br><br>
		
		<select name="president_id" required>
		    <option value="">  Select President </option>
		    <% 
		    int select_pre = request.getAttribute("president_id") != null ?  Integer.parseInt(request.getAttribute("president_id").toString()) : -1;
		    
		    for(User president : presidents) { %>
		        <option value="<%= president.getUser_id() %>"
		        	<%= select_pre == president.getUser_id() ? "selected" : "" %>
		        >
		            <%= president.getName() %>
		        </option>
		    <% } %>
		</select>
		
		<select name="auditor_id" required>
		    <option value="">  Select Auditor </option>
		    <% 
		    int select_audit = request.getAttribute("auditor_id") != null ?  Integer.parseInt(request.getAttribute("auditor_id").toString()) : -1;
		    
		    for(User auditor : auditors) { %>
		        <option value="<%= auditor.getUser_id() %>"
		        	<%= select_audit == auditor.getUser_id() ? "selected" : "" %>
		        >
		            <%= auditor.getName() %>
		        </option>
		    <% } %>
		</select>
		<br><br>
		<button type="submit">Create Symposium</button>
    </form>
    
    <% if(request.getAttribute("errorMessage") != null){ %>
    	${errorMessage}
    <% } %>
</body>
</html>