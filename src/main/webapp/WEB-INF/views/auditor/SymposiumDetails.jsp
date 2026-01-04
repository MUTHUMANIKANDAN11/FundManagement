<%@ page language="java" contentType="text/html; charset=UTF-8"
import="com.management.model.Symposium, java.util.List, com.management.model.Expense"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/dashboard">
		<Button type="submit" >Back</Button>
	</form>
	
	<h2>Symposium Details</h2>
	
    <p>Acadamic Year : ${symp.academic_year} </p>
    <p>Title : ${symp.title} </p>
    <p>Start Date : ${symp.start_date} </p>
    <p>End Date : ${symp.end_date} </p>
    <p>Sponsor DeadLine : ${symp.sponsor_deadLine} </p>
    <p>Claim DeadLine : ${symp.claim_deadLine} </p>
    <p>Allocation : ${symp.allocation} </p>
    <p>Carry Forward : ${symp.carry_forward} </p>
    <br />
     
    <h2>Summary</h2>
	<p>Allocation: ${symp.allocation}</p>
	<p>Sponsorship Collected: ${totalSponsorship}</p>
	<p>Total Collected: ${totalCollection}</p>
	<p>Total Expenses: ${totalExpenses}</p>
	<p>Balance: ${balance}</p>
	<hr/>
     
    <h2>Expenses</h2>
     
    <form action="/FundManagement/AddExpense" method="post" enctype="multipart/form-data" >
		<input type="hidden" value="${symp.symp_id}" name="symp_id" >
		<input type="hidden" value="Components/Auditor/SymposiumDetails.jsp" name="url" >
		<input type="hidden" value="${balance}" name="balance" >
		
		Purpose: <input type="text" name="purpose" value="<%= request.getAttribute("purpose") != null ? request.getAttribute("purpose") : "" %>" > <br /><br />
		Amount: <input type="text" name="amount" <%= request.getAttribute("amount") != null ? request.getAttribute("amount") : "" %> > <br /><br />
		Date: <input type="date" name="bill_date" <%= request.getAttribute("bill_date") != null ? request.getAttribute("bill_date") : "" %> > <br /><br />
		Bill: <input type="file" name="bill_file" accept=".jpg,.jpeg,.png,.pdf" required><br><br>
		
		<button type="submit" >Add Expense</button>
    </form> <br /><br />
     
    <% if(request.getAttribute("errorMessage") != null){ %>
    	"${errorMessage}"
    <% } %>
     
	<form action="Components/Auditor/ViewExpenses.jsp" >
		<input type="hidden" value="${symp.symp_id}" name="symp_id" >
		<button type="submit" >View Expense</button>
	</form>
     
</body>
</html>