package com.management.servlet.president.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.dao.SponsorDao;
import com.management.dao.UserDao;
import com.management.model.Sponsor;
import com.management.model.User;

/**
 * Servlet implementation class AddSponsorServlet
 */
@WebServlet("/president/AddSponsor")
public class AddSponsorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("User");
		int dept_id = user.getDept_id(); 
			
		String name = req.getParameter("name");
		String email = req.getParameter("email");

		String errorMessage = null;	    

	    if(name == null || name.isEmpty() || email == null || email.isEmpty()) {
	        errorMessage = "All fields are required";
	    }	    
	    
	    if(errorMessage == null && name.length() < 3) {
			errorMessage = "Name must be at lest 3 characters long";
		}

	    if(errorMessage == null && email.length() < 3) {
	    	errorMessage = "Contact info must be at lest 3 characters long";
	    }
	    
	    if (errorMessage == null) {
	        SponsorDao dao = new SponsorDao();
	        Sponsor sponsor = new Sponsor(name, email, dept_id);
	        dao.addSponsor(sponsor);
	        
	        req.getRequestDispatcher("ManageSponsors").forward(req, res);;
	        return;
	    }
	    
        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("name", name);
        req.setAttribute("email", email);

        req.getRequestDispatcher("/WEB-INF/views/president/AddSponsorForm.jsp").forward(req, res);
	}
}
