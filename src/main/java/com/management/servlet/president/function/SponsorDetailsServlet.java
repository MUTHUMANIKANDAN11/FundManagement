package com.management.servlet.president.function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.dao.SponsorshipDao;
import com.management.dao.SymposiumDao;
import com.management.model.Sponsorship;
import com.management.model.Symposium;


/**
 * Servlet implementation class SponsorDetailsServlet
 */
@WebServlet("/president/SponsorDetails")
public class SponsorDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sponsor_id = Integer.parseInt(request.getParameter("sponsor_id"));
		int dept_id = Integer.parseInt(request.getParameter("dept_id"));
		
		SponsorshipDao dao = new SponsorshipDao();
		
		List<Sponsorship> sponsorships = dao.getBySponsorId(sponsor_id);
		SymposiumDao symp_dao = new SymposiumDao();
		
		List<Sponsorship> dept_spon = new ArrayList();
		
		for (Sponsorship sponsorship: sponsorships) {
			int symp_id = sponsorship.getSymp_id();
			Symposium symp = symp_dao.getSymposiumById(symp_id);
			
			if(symp.getDept_id() == dept_id) {
				dept_spon.add(sponsorship);
			}
		}
		
		System.out.println(dept_spon);
		System.out.println(sponsorships);
		
		request.getRequestDispatcher("/WEB-INF/views/president/SponsorDetails.jsp").forward(request, response);
	}
}
