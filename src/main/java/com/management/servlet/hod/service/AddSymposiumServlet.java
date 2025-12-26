package com.management.servlet.hod.service;


import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.management.dao.SymposiumDao;
import com.management.model.Symposium;

/**
 * Servlet implementation class AddSymposiumServlet
 */
@WebServlet("/hod/AddSymposium")
public class AddSymposiumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String dept_str = req.getParameter("dept_id");
        String title = req.getParameter("title");
        String year_str = req.getParameter("academic_year");
        String start_str = req.getParameter("start_date");
        String end_str = req.getParameter("end_date");
        String sponsor_str = req.getParameter("sponsor_deadline");
        String claim_str = req.getParameter("claim_deadline");
        String allocation_str = req.getParameter("allocation");
        String president_str = req.getParameter("president_id");
        String auditor_str = req.getParameter("auditor_id");

        
        if(title == null || year_str == null || start_str == null || end_str == null || sponsor_str == null || claim_str == null ||
            allocation_str == null || president_str == null || auditor_str == null) {
        	
        	req.setAttribute("errorMessage", "All fields are required");
        	req.setAttribute("title", title);
        	req.setAttribute("academic_year", year_str);
        	req.setAttribute("start_date", start_str);
        	req.setAttribute("end_date", end_str);
        	req.setAttribute("sponsor_deadline", sponsor_str);
        	req.setAttribute("claim_deadline", claim_str);
        	req.setAttribute("allocation", allocation_str);
        	req.setAttribute("president_id", president_str);
        	req.setAttribute("auditor_id", auditor_str);
        	
            req.getRequestDispatcher("hod/SymposiumForm").forward(req, res);
            return;
        }
        
    	int dept_id = Integer.parseInt(dept_str);
    	int academic_year = Integer.parseInt(year_str);
    	double allocation = Double.parseDouble(allocation_str);
    	int president_id = Integer.parseInt(president_str);
    	int auditor_id = Integer.parseInt(auditor_str);        	
        
    	String errorUrl = "hod/SymposiumForm";
    	
        if(academic_year < 2025 || academic_year > 2030) {
            req.setAttribute("errorMessage", "Academic year must be between 2025 and 2030");
        	req.setAttribute("title", title);
        	req.setAttribute("academic_year", year_str);
        	req.setAttribute("start_date", start_str);
        	req.setAttribute("end_date", end_str);
        	req.setAttribute("sponsor_deadline", sponsor_str);
        	req.setAttribute("claim_deadline", claim_str);
        	req.setAttribute("allocation", allocation_str);
        	req.setAttribute("president_id", president_str);
        	req.setAttribute("auditor_id", auditor_str);
        	
            req.getRequestDispatcher(errorUrl).forward(req, res);
            return;
        }
        
        if(allocation < 0) {
            req.setAttribute("error", "Allocation must be non-negative value");
        	req.setAttribute("title", title);
        	req.setAttribute("academic_year", year_str);
        	req.setAttribute("start_date", start_str);
        	req.setAttribute("end_date", end_str);
        	req.setAttribute("sponsor_deadline", sponsor_str);
        	req.setAttribute("claim_deadline", claim_str);
        	req.setAttribute("allocation", allocation_str);
        	req.setAttribute("president_id", president_str);
        	req.setAttribute("auditor_id", auditor_str);
        	
            req.getRequestDispatcher(errorUrl).forward(req, res);
            return;
        }
        
        Date start_date = Date.valueOf(start_str);
        Date end_date = Date.valueOf(end_str);
        Date sponsor_deadline = Date.valueOf(sponsor_str);
        Date claim_deadline = Date.valueOf(claim_str);

        if(end_date.before(start_date)) {
            req.setAttribute("errorMessage", "End date cannot be before start date.");
        	req.setAttribute("title", title);
        	req.setAttribute("academic_year", year_str);
        	req.setAttribute("start_date", start_str);
        	req.setAttribute("end_date", end_str);
        	req.setAttribute("sponsor_deadline", sponsor_str);
        	req.setAttribute("claim_deadline", claim_str);
        	req.setAttribute("allocation", allocation_str);
        	req.setAttribute("president_id", president_str);
        	req.setAttribute("auditor_id", auditor_str);
        	
            req.getRequestDispatcher(errorUrl).forward(req, res);
            return;
        }
        
        if(sponsor_deadline.after(start_date)) {
            req.setAttribute("errorMessage", "Sponsor deadline must be before the symposium start date.");
        	req.setAttribute("title", title);
        	req.setAttribute("academic_year", year_str);
        	req.setAttribute("start_date", start_str);
        	req.setAttribute("end_date", end_str);
        	req.setAttribute("sponsor_deadline", sponsor_str);
        	req.setAttribute("claim_deadline", claim_str);
        	req.setAttribute("allocation", allocation_str);
        	req.setAttribute("president_id", president_str);
        	req.setAttribute("auditor_id", auditor_str);
        	
            req.getRequestDispatcher(errorUrl).forward(req, res);
            return;
        }
        
        if(claim_deadline.after(end_date)) {
            req.setAttribute("errorMessage", "Claim deadline must be before symposium end date.");
        	req.setAttribute("title", title);
        	req.setAttribute("academic_year", year_str);
        	req.setAttribute("start_date", start_str);
        	req.setAttribute("end_date", end_str);
        	req.setAttribute("sponsor_deadline", sponsor_str);
        	req.setAttribute("claim_deadline", claim_str);
        	req.setAttribute("allocation", allocation_str);
        	req.setAttribute("president_id", president_str);
        	req.setAttribute("auditor_id", auditor_str);
        	
            req.getRequestDispatcher(errorUrl).forward(req, res);
            return;
        }
        
        Symposium symp = new Symposium(
        	dept_id, academic_year, title, start_date, end_date, sponsor_deadline, claim_deadline, allocation, president_id, auditor_id
        );

        SymposiumDao dao = new SymposiumDao();
        
        double sum = 0;
        try {
			sum = dao.getCarryForwardSum(dept_id, academic_year);
			dao.resetTotalAmount(dept_id, academic_year);
		} catch (SQLException e) {
			e.printStackTrace();
		}
                
        symp.setCarry_forward(sum);
        symp.setTotal(allocation + symp.getCarry_forward());
        
        Symposium newSymp = dao.addSymposium(symp);

        if (newSymp != null && newSymp.getSymp_id() > 0) {
        	session = req.getSession();
        	
            res.sendRedirect(req.getContextPath() + "/dashboard");
            
        } else {
            req.setAttribute("errorMessage", "Failed to add symposium.");
        	req.setAttribute("title", title);
        	req.setAttribute("academic_year", year_str);
        	req.setAttribute("start_date", start_str);
        	req.setAttribute("end_date", end_str);
        	req.setAttribute("sponsor_deadline", sponsor_str);
        	req.setAttribute("claim_deadline", claim_str);
        	req.setAttribute("allocation", allocation_str);
        	req.setAttribute("president_id", president_str);
        	req.setAttribute("auditor_id", auditor_str);
        	
            req.getRequestDispatcher(errorUrl).forward(req, res);
        }
	}
}
