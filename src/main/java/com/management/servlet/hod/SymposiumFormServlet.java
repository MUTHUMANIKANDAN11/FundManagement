package com.management.servlet.hod;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.dao.DepartmentDao;
import com.management.dao.UserDao;
import com.management.model.Department;
import com.management.model.User;

/**
 * Servlet implementation class SymposiumFormServlet
 */
@WebServlet("/hod/SymposiumForm")
public class SymposiumFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User");
		
		UserDao user_dao = new UserDao();
		List<User> presidents = user_dao.getUsersDeptRole(user.getDept_id(), "PRESIDENT");
		List<User> auditors = user_dao.getUsersDeptRole(user.getDept_id(), "AUDITOR");
		
		DepartmentDao dept_dao = new DepartmentDao();
		Department dept = dept_dao.getDepartmentById(user.getDept_id());
		
		request.setAttribute("dept", dept);
		request.setAttribute("Presidents", presidents);
		request.setAttribute("Auditors", auditors);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/hod/SymposiumForm.jsp");
		rd.forward(request, response);
	}
}
