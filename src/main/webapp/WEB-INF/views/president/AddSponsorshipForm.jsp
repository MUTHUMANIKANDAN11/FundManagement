<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.management.model.*, java.util.List"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		List<Sponsor> sponsors = (List<Sponsor>) request.getAttribute("sponsors");
    	List<Symposium> symposiums = (List<Symposium>) request.getAttribute("symposiums");
        
    	System.out.println(symposiums);
        System.out.println(sponsors);
	%>
	
	<form action="${pageContext.request.contextPath}/dashboard" >
		<button type="submit" >Back</button>
	</form>
	
	<h2>Fill the details to add Symposium</h2>
	
	<form action="${pageContext.request.contextPath}/president/AddSponsorship" method="post" >
		
        <label>Sponsor ID:</label>
        <select name="sponsor_id" required>
		    <option value="">  Select Sponsor </option>
		    <%
			    if (sponsors != null) {
			        for (Sponsor sponsor : sponsors) {
			%>
			    <option value="<%= sponsor.getSponsor_id() %>">
			        <%= sponsor.getName() %> ( <%= sponsor.getContact_info() %> )
			    </option>
			<%
			        }
			    }
			%>

		</select>
		<br><br>

        <label>Select Symposium:</label>
        <select name="symp_id" required>
	    <option value="">  Select Symposium  </option>
		    <%
		        for (Symposium symp : symposiums) {
			%>
		        <option value="<%= symp.getSymp_id() %>">
		            <%= symp.getTitle() %> ( <%= symp.getSponsor_deadLine() %> )
		        </option>
		    <% } %>
		</select>
		
        <label>Amount:</label>
        <input type="text" name="amount" required><br><br>
		
		<button type="submit" >Add</button>
    </form>
    
	
	
    <% if(request.getAttribute("errorMessage") != null){ %>
    	${errorMessage}
    <% } %>
	
</body>
</html>