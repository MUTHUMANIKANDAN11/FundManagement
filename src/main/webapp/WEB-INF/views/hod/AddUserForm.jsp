<%@ page language="java" contentType="text/html; charset=UTF-8"
import="com.management.model.Symposium, com.management.model.User, java.util.List"
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
		
		List<User> presidents = null;
		List<User> auditors = null;
	%>

	<form action="hod/ManageUsers" >
		<Button type="submit" >Back</Button>
	</form>

	<h2>Fill the details to add Symposium</h2>
    
	<form action="AddUser" method="post">
		<input type="hidden" name="dept_id" value="<%= dept_id %>" ><br><br>

		
		<label>Name:</label>
		<input type="hidden" name="dept_id" value="${dept_id}" >
		<input type="text" name="name" 
		       value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>" required><br><br>
		<label>Email:</label>
		<input type="text" name="email" 
		       value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required><br><br>
		<label>Password:</label>
		<input type="text" name="password" 
		       value="<%= request.getAttribute("password") != null ? request.getAttribute("password") : "" %>" required><br><br>
		       	
		<select name="role" required>
		    <option value="">  Select Role </option>
		    <option value="PRESIDENT">  President </option>
		    <option value="AUDITOR">  Auditor </option>
		</select>
		
		<button type="submit">Create Symposium</button>
    </form>
    
    <% if(request.getAttribute("errorMessage") != null){ %>
    	${errorMessage}
    <% } %>
</body>
</html>