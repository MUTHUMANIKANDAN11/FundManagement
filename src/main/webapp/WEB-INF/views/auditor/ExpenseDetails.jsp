<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	<h3>Reference Document</h3>

	<c:choose>
	
	    <c:when test="${empty expense.reference}">
	        <p>No reference document uploaded.</p>
	    </c:when>
	
	    <c:when test="${expense.reference.endsWith('.pdf')}">
	        <iframe 
	            src="${pageContext.request.contextPath}/${expense.reference}"
	            width="600"
	            height="500">
	        </iframe>
	    </c:when>
	
	    <c:when test="${expense.reference.endsWith('.jpg') 
	                || expense.reference.endsWith('.jpeg') 
	                || expense.reference.endsWith('.png')}">
	        <img 
	            src="${pageContext.request.contextPath}/${expense.reference}"
	            width="400"
	            alt="Reference Image">
	    </c:when>

	    <c:otherwise>
	        <a href="${pageContext.request.contextPath}/${expense.reference}" target="_blank">
	            Download Reference
	        </a>
	    </c:otherwise>
	
	</c:choose>
	
	<hr>
	
	<h3>Upload / Change Reference</h3>
	
	<form action="UpdateExpenseFile" method="post" enctype="multipart/form-data">
	
	    <input type="hidden" name="expense_id" value="${expense.expense_id}">
	    <input type="hidden" name="symp_id" value="${expense.symp_id}">
	
	    <input type="file" name="bill_file" accept=".pdf,.jpg,.jpeg,.png" required>
	
	    <br><br>
	
	    <button type="submit">Upload / Change File</button>
	    <% if(request.getAttribute("errorMessage") != null){ %>
	    	"${errorMessage}"
	    <% } %>
	
	</form>
	
	</body>
</html>