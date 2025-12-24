package com.management.servlet.common;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.dao.UserDao;
import com.management.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = (String)req.getParameter("email");
		String password = (String)req.getParameter("password");
		
		UserDao dao = new UserDao();
		String errorMessage = null;
		
		if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
			errorMessage = "Both Email and Password are required";
		}
		
		if(errorMessage == null && !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
	    	errorMessage = "Invalid email format";
	    }
		
		User user = null; 
		if(errorMessage == null) {
			user = dao.getUserByLogin(email, password);
		}
		
		if(errorMessage == null && user == null) {
			errorMessage = "Email or Password is incorrect";
		}
		
		if(user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("User", user);
			
			res.sendRedirect("dashboard");
			
		} else {
			req.setAttribute("email", email);
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("email", email);
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/common/login.jsp");
			rd.forward(req, res);
		}
	}
}
