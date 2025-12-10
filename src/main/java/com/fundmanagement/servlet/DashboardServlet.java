package com.fundmanagement.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fundmanagement.model.User;
import com.fundmanagement.dao.SymposiumDao;
import com.fundmanagement.model.Symposium;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
    	
    	if(user == null) {
    		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/common/login.jsp");
    		rd.forward(req, res);
    	}
    	else {
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
}
