package com.fundmanagement.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.fundmanagement.dao.DepartmentDao;
import com.fundmanagement.dao.UserDao;
import com.fundmanagement.model.Department;
import com.fundmanagement.model.User;

/**
 * Servlet implementation class SymposiumFormServlet
 */
@WebServlet("/SymposiumForm")
public class SymposiumFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User");
		
		if(user == null) {
    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/login.jsp");
    		rd.forward(request, response);
    	}
		
		UserDao user_dao = new UserDao();
		List<User> presidents = user_dao.getUsersDeptRole(user.getDept_id(), "PRESIDENT");
		List<User> auditors = user_dao.getUsersDeptRole(user.getDept_id(), "AUDITOR");
		
		DepartmentDao dept_dao = new DepartmentDao();
		Department dept = dept_dao.getDepartmentById(user.getDept_id());
		
		request.setAttribute("dept", dept);
		request.setAttribute("Presidents", presidents);
		request.setAttribute("Auditors", auditors);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/forms/SymposiumForm.jsp");
		rd.forward(request, response);
	}
}
