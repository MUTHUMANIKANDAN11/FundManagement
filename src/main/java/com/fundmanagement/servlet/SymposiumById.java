package com.fundmanagement.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.fundmanagement.dao.SymposiumDao;
import com.fundmanagement.model.Symposium;

/**
 * Servlet implementation class SymposiumById
 */
@WebServlet("/SymposiumById")
public class SymposiumById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int symp_id = Integer.parseInt(request.getParameter("symp_id"));
		
		SymposiumDao dao = new SymposiumDao();
		Symposium symp = dao.getSymposiumById(symp_id);
		
		request.setAttribute("symp", symp);
		request.getRequestDispatcher("/WEB-INF/views/SymposiumDetails.jsp").forward(request, response);
	}
}
