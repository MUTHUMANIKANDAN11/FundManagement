package com.management.servlet.auditor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.dao.ExpenseDao;
import com.management.dao.SponsorshipDao;
import com.management.dao.SymposiumDao;
import com.management.model.Expense;
import com.management.model.Symposium;

/**
 * Servlet implementation class SymposiumDetailsServlet
 */
@WebServlet("/auditor/SymposiumDetails")
public class SymposiumDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int symp_id =  Integer.parseInt((String) request.getParameter("symp_id"));
		
		SymposiumDao dao = new SymposiumDao();
		Symposium symp = dao.getSymposiumById(symp_id);
		
		SymposiumDao sympDao = new SymposiumDao();
        ExpenseDao expenseDao = new ExpenseDao();
        SponsorshipDao sponsorshipDao = new SponsorshipDao();

        List<Expense> expenses = expenseDao.getBySympId(symp_id);
        double totalSponsorship = sponsorshipDao.totalSponsorshipsPerSymposium(symp_id);
        double totalExpenses = expenseDao.totalExpensePerSymposium(symp_id);
        double totalCollection = symp.getAllocation() + totalSponsorship + symp.getCarry_forward();
        double balance = symp.getTotal();
        
        request.setAttribute("symp", symp);
        request.setAttribute("expenses", expenses);
        request.setAttribute("totalCollection", totalCollection);
        request.setAttribute("totalSponsorship", totalSponsorship);
        request.setAttribute("totalExpenses", totalExpenses);
        request.setAttribute("balance", balance);

		request.setAttribute("symp", symp);
		
		request.getRequestDispatcher("/WEB-INF/views/auditor/SymposiumDetails.jsp").forward(request, response);
	}

}
