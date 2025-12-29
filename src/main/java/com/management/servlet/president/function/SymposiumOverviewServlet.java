package com.management.servlet.president.function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.dao.SponsorshipDao;
import com.management.dao.SymposiumDao;
import com.management.model.Symposium;
import com.management.model.TotalFundDept;

/**
 * Servlet implementation class SymposiumOverviewServlet
 */
@WebServlet("/president/SymposiumOverview")
public class SymposiumOverviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptIdParam = request.getParameter("dept_id");
        
        if (deptIdParam == null || deptIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Department ID is required");
            return;
        }
        
        int dept_id = Integer.parseInt(deptIdParam);

        SymposiumDao symp_dao = new SymposiumDao();
        SponsorshipDao sponsorship_dao = new SponsorshipDao();

        List<Symposium> symposiums = symp_dao.getSymposiumByDept_id(dept_id);
        List<TotalFundDept> reports = new ArrayList<>();

        for (Symposium symp : symposiums) {
            double sponsors = sponsorship_dao.totalSponsorshipsPerSymposium(symp.getSymp_id());
            double allocation = symp.getAllocation();
            double carryForward = symp.getCarry_forward();
            double totalFunds = allocation + sponsors + carryForward;

            TotalFundDept report = new TotalFundDept();
            report.setSymposium(symp);
            report.setAllocation(allocation);
            report.setCarryForward(carryForward);
            report.setSponsors(sponsors);
            report.setTotalFunds(totalFunds);

            reports.add(report);
        }

        request.setAttribute("fundReports", reports);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/president/SymposiumOverview.jsp");
        dispatcher.forward(request, response);
    }
}
