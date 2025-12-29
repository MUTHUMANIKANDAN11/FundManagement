package com.management.servlet.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.management.model.User;
import com.management.dao.DepartmentDao;
import com.management.dao.SponsorDao;
import com.management.dao.SponsorshipDao;
import com.management.dao.SymposiumDao;
import com.management.model.Department;
import com.management.model.Sponsor;
import com.management.model.Sponsorship;
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
	        int dept_id =  user.getDept_id();

            DepartmentDao deptDao = new DepartmentDao();
            Department department = deptDao.getDepartmentById(dept_id);

            SponsorshipDao sponsorshipDao = new SponsorshipDao();
            List<Sponsorship> sponsorships = sponsorshipDao.getSponsorshipsByDept(dept_id);
	        
	        Map<Integer, String> sponsorMap = new HashMap<>();
			SponsorDao sponsor_dao = new SponsorDao();
			
			for (Sponsor sp : sponsor_dao.getAllSponsors()) {
			    sponsorMap.put(sp.getSponsor_id(), sp.getName());
			}
			
			Map<Integer, String> symposiumMap = new HashMap<>();
			SymposiumDao symposiumDao = new SymposiumDao();
			for (Symposium symp : symposiumDao .getAllSymposium()) {
			    symposiumMap.put(symp.getSymp_id(), symp.getTitle());
			}
			
			req.setAttribute("symposiums", symposiumMap);
			req.setAttribute("sponsors", sponsorMap);
	        req.setAttribute("department", department);
	        req.setAttribute("sponsorships", sponsorships);
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
