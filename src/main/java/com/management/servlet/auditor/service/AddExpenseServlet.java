package com.management.servlet.auditor.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.management.dao.ExpenseDao;
import com.management.dao.SymposiumDao;
import com.management.model.Expense;
import com.management.model.Symposium;

@WebServlet("/auditor/AddExpense")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,      // 1MB
    maxFileSize = 5 * 1024 * 1024,         // 5MB
    maxRequestSize = 10 * 1024 * 1024      // 10MB
)
public class AddExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String url = "SymposiumDetails";
        
        String sympIdStr = req.getParameter("symp_id");
        String amountStr = req.getParameter("amount");
        String purpose = req.getParameter("purpose");
        String dateStr = req.getParameter("bill_date");

        int symp_id = Integer.parseInt(sympIdStr);
        req.setAttribute("symp_id", symp_id);
        
        if (sympIdStr == null || sympIdStr.trim().isEmpty() ||
            amountStr == null || amountStr.trim().isEmpty() ||
            purpose == null || purpose.trim().isEmpty() ||
            dateStr == null || dateStr.trim().isEmpty()) {

            req.setAttribute("errorMessage", "All fields are required.");
            forwardWithValues(req, res, url, purpose, dateStr, amountStr);
            return;
        }

        double amount;
        Date expenseDate;

        try {
            amount = Double.parseDouble(amountStr);
            expenseDate = Date.valueOf(dateStr);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Invalid input values.");
            forwardWithValues(req, res, url, purpose, dateStr, amountStr);
            return;
        }

        if (amount <= 0) {
            req.setAttribute("errorMessage", "Amount must be greater than zero.");
            forwardWithValues(req, res, url, purpose, dateStr, amountStr);
            return;
        }

        try {
            SymposiumDao sympDao = new SymposiumDao();
            Symposium symp = sympDao.getSymposiumById(symp_id);

            if (symp == null) {
                req.setAttribute("errorMessage", "Invalid symposium.");
                req.getRequestDispatcher(url).forward(req, res);
                return;
            }

            if (expenseDate.after(symp.getClaim_deadLine())) {
                req.setAttribute("errorMessage",
                        "Can't add expenses after the claim deadline.");
                forwardWithValues(req, res, url, purpose, dateStr, amountStr);
                return;
            }

            double balance = symp.getTotal();
            if (amount > balance) {
                req.setAttribute("errorMessage", "Insufficient balance.");
                forwardWithValues(req, res, url, purpose, dateStr, amountStr);
                return;
            }

            Part filePart = req.getPart("bill_file");
            String billPath = "";
            if (filePart != null && filePart.getSize() != 0) {
            	String originalFileName =
                        Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileExt = originalFileName.substring(originalFileName.lastIndexOf("."));

                if (!fileExt.matches("\\.(pdf|jpg|jpeg|png)")) {
                    req.setAttribute("errorMessage", "Only PDF, JPG, PNG files are allowed.");
                    forwardWithValues(req, res, url, purpose, dateStr, amountStr);
                    return;
                }

                String newFileName = UUID.randomUUID() + fileExt;

                String uploadPath = getServletContext().getRealPath("/") + "uploads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                filePart.write(uploadPath + File.separator + newFileName);
                billPath = "uploads/" + newFileName;
            }

            Expense expense = new Expense(symp_id, amount, expenseDate, purpose, billPath);
            ExpenseDao expenseDao = new ExpenseDao();
            Expense savedExpense = expenseDao.addExpense(expense);

            if (savedExpense != null && savedExpense.getExpense_id() > 0) {

                double newTotal = balance - amount;
                sympDao.updateTotal(symp_id, newTotal);

                req.getRequestDispatcher(url).forward(req, res);
            } else {
                req.setAttribute("errorMessage", "Failed to add expense.");
                forwardWithValues(req, res, url, purpose, dateStr, amountStr);
            }

        } catch (Exception e) {
            req.setAttribute("errorMessage", "Error: " + e.getMessage());
            forwardWithValues(req, res, url, purpose, dateStr, amountStr);
        }
    }

    private void forwardWithValues(HttpServletRequest req, HttpServletResponse res,
                                   String url, String purpose, String date, String amount)
            throws ServletException, IOException {

        req.setAttribute("purpose", purpose);
        req.setAttribute("date", date);
        req.setAttribute("amount", amount);
        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req, res);
    }
}
