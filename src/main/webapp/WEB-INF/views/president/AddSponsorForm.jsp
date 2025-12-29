<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.management.model.User"
    pageEncoding="UTF-8"%>
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
	%>
	
	<form action="${pageContext.request.contextPath}/president/ManageSponsors" method="post" >
		<Button type="submit" >Back</Button>
	</form>
	
	<h1>Sponsor add form</h1>
	
	<form action="AddSponsor" method="post">
	
		<input type="hidden" name="dept_id" value="${dept_id}" >
		<label>Name:</label>
		<input type="text" name="name" 
		       value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>" required><br><br>
		<label>Contact-Info:</label>
		<input type="text" name="email" 
		       value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required><br><br>
		
		<button type="submit">Create Symposium</button>
    </form>
    
    <% if(request.getAttribute("errorMessage") != null){ %>
    	${errorMessage}
    <% } %>
</body>
</html>