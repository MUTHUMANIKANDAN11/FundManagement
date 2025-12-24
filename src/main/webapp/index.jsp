<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Fund Management System</h2>

    <!-- This form will call the LoginServlet -->
    <form action="${pageContext.request.contextPath}/dashboard" method="get">
        <button type="submit">Get Started</button>
    </form>
</body>
</html>
