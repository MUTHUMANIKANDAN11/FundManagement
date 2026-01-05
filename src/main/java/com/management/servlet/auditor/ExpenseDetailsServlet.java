package com.management.servlet.auditor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.dao.ExpenseDao;
import com.management.model.Expense;

/**
 * Servlet implementation class ExpenseDetailsServlet
 */
@WebServlet("/auditor/ExpenseDetails")
public class ExpenseDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int expense_id = Integer.parseInt((String) request.getParameter("expense_id"));
		
		ExpenseDao dao = new ExpenseDao();
		Expense expense = dao.getExpenseById(expense_id);
		
		request.setAttribute("expense", expense);
		
		request.getRequestDispatcher("/WEB-INF/views/auditor/ExpenseDetails.jsp").forward(request, response);
	}

}
