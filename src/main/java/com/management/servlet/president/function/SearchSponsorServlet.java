package com.management.servlet.president.function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.model.Sponsor;

/**
 * Servlet implementation class SearchSponsorServlet
 */
@WebServlet("/president/SearchSponsor")
public class SearchSponsorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Sponsor> sponsors = (List<Sponsor>) request.getSession().getAttribute("sponsors");
		String keyword = (String) request.getParameter("keyword").toLowerCase();
		
		List<Sponsor> newSponsors = new ArrayList();
		for (Sponsor sponsor: sponsors) {
			if(sponsor.getName().toLowerCase().contains(keyword) || sponsor.getContact_info().toLowerCase().contains(keyword)) {
				newSponsors.add(sponsor);
			}
		}
		
		request.setAttribute("sponsors", sponsors);
		request.setAttribute("newSponsors", newSponsors);
		request.getRequestDispatcher("/WEB-INF/views/president/ManageSponsors.jsp").forward(request, response);
	}
}
