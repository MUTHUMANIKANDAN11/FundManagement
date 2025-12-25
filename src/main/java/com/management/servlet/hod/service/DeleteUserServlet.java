package com.management.servlet.hod.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.dao.UserDao;
import com.management.model.User;

/**
 * Servlet implementation class SelectUserServlet
 */
@WebServlet("/hod/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("user_id");
		String errorMessage = null;
		
		int user_id = 0;
		if(input == null || input.isEmpty()) {
			errorMessage = "Id is required";
		} else {
			user_id = Integer.parseInt(input);
		}
		
		if(errorMessage == null && user_id <= 0) {
			errorMessage = "Id is invalid";
		}
		
		UserDao dao = new UserDao();
		boolean state = false;
		
		if(errorMessage == null) {
			state = dao.deactivateUser(user_id);
			if(!state) {
				errorMessage = "Something went wrong while removing the User. Please try again.";
			}
		}
		
		if(state) {
			request.setAttribute("message", "Successfully user removed");
		}
		else {
			request.setAttribute("errorMessage", errorMessage);
		}
		
		request.getRequestDispatcher("/hod/ManageUsers").forward(request, response);
	}
}
