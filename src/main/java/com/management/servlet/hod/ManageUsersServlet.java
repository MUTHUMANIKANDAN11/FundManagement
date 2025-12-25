package com.management.servlet.hod;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.dao.UserDao;
import com.management.model.User;

/**
 * Servlet implementation class ManageUsersServlet
 */
@WebServlet("/ManageUsers")
public class ManageUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute("User");
		
		if(user == null) {
    		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/common/login.jsp");
    		rd.forward(req, res);
    		return;
    	}
		int dept_id = user.getDept_id();
		
		UserDao dao = new UserDao();
		List<User> auditors = dao.getUsersDeptRole(dept_id, "AUDITOR");
		List<User> presidents = dao.getUsersDeptRole(dept_id, "PRESIDENT");

		req.setAttribute("Auditors", auditors);
		req.setAttribute("Presidents", presidents);
		
		req.getRequestDispatcher("/WEB-INF/views/hod/ManageUsers.jsp").forward(req, res);
	}

}
