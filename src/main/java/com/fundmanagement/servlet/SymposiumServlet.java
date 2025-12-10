package com.fundmanagement.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.fundmanagement.dao.DepartmentDao;
import com.fundmanagement.dao.SymposiumDao;
import com.fundmanagement.dao.UserDao;
import com.fundmanagement.model.Department;
import com.fundmanagement.model.Symposium;
import com.fundmanagement.model.User;

/**
 * Servlet implementation class SymposiumById
 */
@WebServlet("/Symposium")
public class SymposiumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int symp_id = Integer.parseInt(request.getParameter("symp_id"));
		User user = (User) request.getSession().getAttribute("User");
		
		if(user == null) {
    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/login.jsp");
    		rd.forward(request, response);
    	}
		
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
		request.getRequestDispatcher("/WEB-INF/views/SymposiumDetails.jsp").forward(request, response);
	}
}
