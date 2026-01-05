<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/auditor/SymposiumDetails" method="post" >
		<input type="hidden" name="symp_id" value="${expense.symp_id}" >
		<Button type="submit" >Back</Button>
	</form>
	
	<h1>Expense Details</h1>
	
	<p>Purpose: ${expense.purpose}</p>
	<p>Amount: ${expense.amount}</p>
	<p>Date: ${expense.expense_date}</p>
	<p>Reference: ${expense.reference}</p>
</body>
</html>