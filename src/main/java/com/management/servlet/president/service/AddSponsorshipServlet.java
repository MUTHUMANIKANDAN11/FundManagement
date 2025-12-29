package com.management.servlet.president.service;

import java.io.IOException;
import java.sql.Date;

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
 * Servlet implementation class AddSponsorshipServlet
 */
@WebServlet("/president/AddSponsorship")
public class AddSponsorshipServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String sponsorIdStr = req.getParameter("sponsor_id");
        String sympIdStr = req.getParameter("symp_id");
        String amountStr = req.getParameter("amount");

        String url = "AddSponsorshipForm";

        // Validation
        if (sponsorIdStr == null || sponsorIdStr.isBlank() ||
            sympIdStr == null || sympIdStr.isBlank() ||
            amountStr == null || amountStr.isBlank()) {

            req.setAttribute("errorMessage", "All fields are required.");
            req.setAttribute("sponsor_id", sponsorIdStr);
            req.setAttribute("symp_id", sympIdStr);
            req.setAttribute("amount", amountStr);

            req.getRequestDispatcher(url).forward(req, res);
            return;
        }

        try {
            int sponsor_id = Integer.parseInt(sponsorIdStr);
            int symp_id = Integer.parseInt(sympIdStr);
            double amount = Double.parseDouble(amountStr);

            Date today = Date.valueOf(java.time.LocalDate.now());

            SymposiumDao sympDao = new SymposiumDao();
            Symposium symp = sympDao.getSymposiumById(symp_id);

            if (symp == null) {
                req.setAttribute("errorMessage", "Invalid symposium.");
                req.getRequestDispatcher(url).forward(req, res);
                return;
            }

            if (today.after(symp.getSponsor_deadLine())) {
                req.setAttribute("errorMessage", "Sponsorship deadline has passed.");
                req.getRequestDispatcher(url).forward(req, res);
                return;
            }

            SponsorshipDao dao = new SponsorshipDao();
            Sponsorship existing = dao.getBySponsorAndSymposium(sponsor_id, symp_id);

            if (existing != null) {
                existing.setAmount(existing.getAmount() + amount);
                dao.updateSponsorship(existing);
                req.setAttribute("success", "Sponsorship updated successfully");
                res.sendRedirect(req.getContextPath() + "/dashboard");
                return;
                
            } else {
                Sponsorship sponsorship = new Sponsorship(sponsor_id, amount, today, symp_id);
                Sponsorship saved = dao.addSponsorship(sponsorship);

                if (saved != null && saved.getSponsorship_id() > 0) {
                    req.setAttribute("success", "Sponsorship added successfully");
                    res.sendRedirect(req.getContextPath() + "/dashboard");
                    return;
                    
                } else {
                    req.setAttribute("errorMessage", "Failed to add sponsorship.");
                }
            }

            req.getRequestDispatcher(url).forward(req, res);

        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Invalid input format.");
            req.getRequestDispatcher(url).forward(req, res);
        }
    }
}