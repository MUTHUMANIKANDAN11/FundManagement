package com.management.servlet.president;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.dao.SponsorDao;
import com.management.dao.SymposiumDao;
import com.management.model.Sponsor;
import com.management.model.Symposium;
import com.management.model.User;

/**
 * Servlet implementation class AddSponsorshipFormServlet
 */
@WebServlet("/president/AddSponsorshipForm")
public class AddSponsorshipFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("User");
		int dept_id = user.getDept_id();
		
	
		SymposiumDao symp_dao = new SymposiumDao();
	    List<Symposium> symps = symp_dao.getSymposiumByDept_id(dept_id);
	    
	    SponsorDao sponsor_dao = new SponsorDao();
        List<Sponsor> sponsors = sponsor_dao.getByDeptId(dept_id);
        
        request.setAttribute("symposiums", symps);
        request.setAttribute("sponsors", sponsors);
        
		request.getRequestDispatcher("/WEB-INF/views/president/AddSponsorshipForm.jsp").forward(request, response);
	}

}
