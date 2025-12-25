package com.management.servlet.hod.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.dao.UserDao;
import com.management.model.User;


@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User currentUser = (User) req.getSession().getAttribute("User");
		
		if(currentUser == null) {
			res.sendRedirect("index.jsp");
			return;
		}
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String role = req.getParameter("role");

		String errorMessage = null;

		int dept_id = 0;
	    if (errorMessage == null) {
	        try {
	            dept_id = Integer.parseInt(req.getParameter("dept_id"));
	        } catch (NumberFormatException e) {
	            errorMessage = "Invalid Department ID.";
	        }
	    }
	    

	    if(name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty() || role == null || role.isEmpty()) {
	        errorMessage = "All fields are required";
	    }	    
	    
	    if(errorMessage == null && name.length() < 3) {
			errorMessage = "Name must be at lest 3 characters long";
		}
	    
	    if(errorMessage == null && !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
	    	errorMessage = "Invalid email format";
	    }
	    
	    if (errorMessage == null && !password.matches("^(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[@#$%^&+=!_])" + "(?=\\S+$).{8,}$")) {
	        errorMessage = "Password must be at least 8 characters long, include upper/lowercase, number, and special character";
	    }
	    
	    if (errorMessage == null) {
	        String currentUserRole = currentUser.getRole();
	        int currentUserDept = currentUser.getDept_id();

	        UserDao dao = new UserDao();

	        if (currentUserRole.equals("HOD")) {
	            if (!(role.equals("PRESIDENT") || role.equals("AUDITOR"))) {
	                errorMessage = "As HOD you can add only the President or Auditor";
	            }
	            else if (dept_id != currentUserDept) {
	                errorMessage = "You can add President or Auditor only in your own department";
	            }
	        }
	        else {
	            errorMessage = "You do not have permission to add users.";
	        }
	    }
	    
	    if (errorMessage != null) {
	        req.setAttribute("errorMessage", errorMessage);
	        req.setAttribute("name", name);
	        req.setAttribute("email", email);
	        req.setAttribute("role", role);
	        req.setAttribute("dept_id", dept_id);

	        req.getRequestDispatcher("/WEB-INF/views/hod/AddUserForm.jsp").forward(req, res);
	        return;
	    }
		
	    UserDao dao = new UserDao();
 		User user = dao.addUser(name, email, password, role, dept_id);
		
 		res.sendRedirect("ManageUsers");
	}

}
