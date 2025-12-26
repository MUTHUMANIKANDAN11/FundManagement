package com.management.servlet.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.model.User;
import com.management.dao.SymposiumDao;
import com.management.model.Symposium;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected List<Symposium> SymposiumByDepartment(int dept_id) throws ServletException, IOException {
        SymposiumDao dao = new SymposiumDao();
        List<Symposium> symps = dao.getSymposiumByDept_id(dept_id);
        
        return symps;
	}
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	HttpSession session = req.getSession();
    	User user = (User) session.getAttribute("User");
    	
		if(user.getRole().equals("ADMIN")) {
			req.setAttribute("dept_id", user.getDept_id());
			req.setAttribute("url", "pages/dashboard.jsp");
			
			req.getRequestDispatcher("SymposiumByDepartment").forward(req, res);
		}
		
		else if(user.getRole().equals("HOD")) {
			List<Symposium> symps = SymposiumByDepartment(user.getDept_id());
			symps.sort(Comparator.comparingInt(Symposium::getAcademic_year));
			req.setAttribute("Symps", symps);
		}
		
		else if(user.getRole().equals("PRESIDENT")) {
			req.setAttribute("url", "pages/dashboard.jsp");
			req.getRequestDispatcher("SponsorshipsOfDepartment").forward(req, res);
		}
		
		else if(user.getRole().equals("AUDITOR")) {
			req.setAttribute("dept_id", user.getDept_id());
			req.setAttribute("url", "pages/dashboard.jsp");

			req.getRequestDispatcher("SymposiumByDepartment").forward(req, res);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/common/dashboard.jsp");
		rd.forward(req, res);
	
    }
}
