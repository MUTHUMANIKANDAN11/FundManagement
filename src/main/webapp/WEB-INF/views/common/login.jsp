<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post" autocomplete="on" >
		Email: <input type="text" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" > <br /><br />
		Password: <input type="text" name="password" > <br /><br />
		<button type="submit" >login</button>
	</form>
	
	<% if(request.getAttribute("errorMessage") != null) { %>
		<p> ${errorMessage} </p>
	<% } %>
	
</body>
</html>