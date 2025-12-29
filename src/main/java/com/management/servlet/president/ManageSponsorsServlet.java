package com.management.servlet.president;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ManageSponsorsServlet
 */
@WebServlet("/president/ManageSponsors")
public class ManageSponsorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("User");
		int dept_id = user.getDept_id();

		SponsorDao dao = new SponsorDao();
		List<Sponsor> sponsors = dao.getByDeptId(dept_id);

		request.setAttribute("sponsors", sponsors);
		
		request.getRequestDispatcher("/WEB-INF/views/president/ManageSponsors.jsp").forward(request, response);
	}

}
