package com.management.servlet.president.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.dao.SponsorDao;
import com.management.dao.UserDao;

/**
 * Servlet implementation class SelectUserServlet
 */
@WebServlet("/president/DeleteSponsor")
public class DeleteSponsorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("sponsor_id");
		String errorMessage = null;
		
		int sponsor_id = 0;
		if(input == null || input.isEmpty()) {
			errorMessage = "Id is required";
		} else {
			sponsor_id = Integer.parseInt(input);
		}
		
		if(errorMessage == null && sponsor_id <= 0) {
			errorMessage = "Id is invalid";
		}

		SponsorDao dao = new SponsorDao();
		boolean state = false;
		
		if(errorMessage == null) {
			state = dao.deactivateSponsor(sponsor_id);
			if(!state) {
				errorMessage = "Something went wrong while removing the Sponsor. Please try again.";
			}
		}
		
		if(state) {
			request.setAttribute("message", "Successfully sponsor removed");
		}
		else {
			request.setAttribute("errorMessage", errorMessage);
		}
		
		request.getRequestDispatcher("ManageSponsors").forward(request, response);
	}
}
