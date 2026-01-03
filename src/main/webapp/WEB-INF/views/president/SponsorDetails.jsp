<%@ page language="java" contentType="text/html; charset=UTF-8"
	import=" com.management.model.SponsorDetails "
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Sponsor Details</h1>
	
	<p><b>Name:</b> ${details.sponsor.name}</p>
	<p><b>Contact Info.:</b> ${details.sponsor.contact_info}</p>
	<p><b>Total Fund for Department:</b> ${details.total}</p>

	<%
		SponsorDetails details = (SponsorDetails) request.getAttribute("details");
	%>
	<h1>Sponsorship Details</h1>
	
	<c:forEach var="detail" items="${details.sponsorshipInfos}">
        <p>Title : ${detail.symposium.title}</p>
        <p>Year : ${detail.symposium.academic_year}</p>
        <p>Sponsor Fund : ${detail.sponsorship.amount}</p>
        <p>Total Fund : ${detail.symposium.total}</p>
		<br />
    </c:forEach>
</body>
</html>