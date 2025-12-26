package com.management.servlet.hod;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.dao.DepartmentDao;
import com.management.dao.SymposiumDao;
import com.management.dao.UserDao;
import com.management.model.Department;
import com.management.model.Symposium;
import com.management.model.User;

/**
 * Servlet implementation class SymposiumById
 */
@WebServlet("/hod/Symposium")
public class SymposiumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int symp_id = Integer.parseInt(request.getParameter("symp_id"));
		User user = (User) request.getSession().getAttribute("User");
		
		SymposiumDao dao = new SymposiumDao();
		Symposium symp = dao.getSymposiumById(symp_id);
		
		System.out.println(symp);
		
		DepartmentDao dept_dao = new DepartmentDao();
		Department dept = dept_dao.getDepartmentById(user.getDept_id());
		
		UserDao user_dao = new UserDao();
		String president = user_dao.getUserById(symp.getPresident_id()).getName();
		String auditor = user_dao.getUserById(symp.getAuditor_id()).getName();
		
		request.setAttribute("dept", dept);
		request.setAttribute("symp", symp);
		request.setAttribute("president", president);
		request.setAttribute("auditor", auditor);
		request.getRequestDispatcher("/WEB-INF/views/hod/SymposiumDetails.jsp").forward(request, response);
	}
}
