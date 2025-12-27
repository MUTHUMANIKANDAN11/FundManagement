package com.management.servlet.hod.function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.dao.ExpenseDao;
import com.management.dao.SponsorshipDao;
import com.management.dao.SymposiumDao;
import com.management.model.Expense;
import com.management.model.Sponsorship;
import com.management.model.Symposium;
import com.management.model.User;
import com.management.model.YearlyRecord;

/**
 * Servlet implementation class YearReportServlet
 */
@WebServlet("/hod/YearReport")
public class YearReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int year = Integer.parseInt(request.getParameter("year"));
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("User");
		
		int dept_id = user.getDept_id();
		
		SymposiumDao symp_dao = new SymposiumDao();
		SponsorshipDao sponsorship_dao = new SponsorshipDao();
		ExpenseDao expense_dao = new ExpenseDao();

		List<Symposium> currentSymps = symp_dao.getSymposiumByAcademic_yearAndDept_id(year, dept_id);
		List<YearlyRecord> currentRecords = new ArrayList<>();

		for (Symposium symp : currentSymps) {
		    List<Sponsorship> sponsors = sponsorship_dao.getBySympId(symp.getSymp_id());
		    List<Expense> expenses = expense_dao.getBySympId(symp.getSymp_id());

		    YearlyRecord record = new YearlyRecord();
		    record.setSymposium(symp);
		    record.setSponsors(sponsors);
		    record.setExpenses(expenses);

		    currentRecords.add(record);
		}

		request.setAttribute("currentRecords", currentRecords);

		System.out.println(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("YearReportForm");
		dispatcher.forward(request, response);
	}
}
