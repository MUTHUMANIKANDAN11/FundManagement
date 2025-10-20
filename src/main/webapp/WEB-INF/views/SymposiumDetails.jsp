<%@ page language="java" contentType="text/html; charset=UTF-8"
import="com.fundmanagement.model.Symposium"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="dashboard">
		<Button type="submit" >Back</Button>
	</form>
	<h2>Symposium Details</h2>
	
	<p>Id : ${symp.getSymp_id()} </p>
    <p>Department : ${symp.dept_id} </p>
    <p>Acadamic Year : ${symp.academic_year} </p>
    <p>Title : ${symp.title} </p>
    <p>Start Date : ${symp.start_date} </p>
    <p>End Date : ${symp.end_date} </p>
    <p>Sponsor DeadLine : ${symp.sponsor_deadLine} </p>
    <p>Claim DeadLine : ${symp.claim_deadLine} </p>
    <p>President : ${symp.president_id} </p>
    <p>Auditor : ${symp.auditor_id} </p>
    <p>Allocation : ${symp.allocation} </p>
    <p>Carry Forward : ${symp.carry_forward} </p>
    <p>Total Fund : ${symp.total} </p>
     <br />
</body>
</html>