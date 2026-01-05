package com.management.servlet.auditor.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.management.dao.ExpenseDao;
import com.management.model.Expense;

@WebServlet("/auditor/UpdateExpenseFile")
@MultipartConfig(
    maxFileSize = 5 * 1024 * 1024,      // 5MB
    maxRequestSize = 10 * 1024 * 1024
)
public class UpdateExpenseFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String expenseIdStr = request.getParameter("expense_id");

        if (expenseIdStr == null || expenseIdStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Invalid expense.");
            request.getRequestDispatcher("/auditor/ExpenseDetails").forward(request, response);
            return;
        }

        int expense_id = Integer.parseInt(expenseIdStr);

        Part filePart = request.getPart("bill_file");
        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("errorMessage", "Please select a file.");
            request.getRequestDispatcher("/auditor/ExpenseDetails").forward(request, response);
            return;
        }

        try {
            ExpenseDao expenseDao = new ExpenseDao();

            Expense expense = expenseDao.getExpenseById(expense_id);
            if (expense == null) {
                request.setAttribute("errorMessage", "Expense not found.");
                request.getRequestDispatcher("/auditor/ExpenseDetails").forward(request, response);
                return;
            }

            String oldReference = expense.getReference();
            if (oldReference != null && !oldReference.isEmpty()) {
                String oldFilePath =
                        getServletContext().getRealPath("/") + oldReference;
                File oldFile = new File(oldFilePath);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            String originalFileName =
                    Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            int dotIndex = originalFileName.lastIndexOf(".");
            if (dotIndex == -1) {
                request.setAttribute("errorMessage", "Invalid file type.");
                request.getRequestDispatcher("/auditor/ExpenseDetails").forward(request, response);
                return;
            }

            String fileExt = originalFileName.substring(dotIndex).toLowerCase();
            if (!fileExt.matches("\\.(pdf|jpg|jpeg|png)")) {
                request.setAttribute("errorMessage", "Only PDF, JPG, PNG files are allowed.");
                request.getRequestDispatcher("/auditor/ExpenseDetails").forward(request, response);
                return;
            }

            String newFileName = UUID.randomUUID() + fileExt;

            String uploadPath = getServletContext().getRealPath("/") + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            filePart.write(uploadPath + File.separator + newFileName);

            String newBillPath = "uploads/" + newFileName;

            boolean updated = expenseDao.updateExpenseReference(expense_id, newBillPath);

            if (updated) {
            	request.getRequestDispatcher("/auditor/ExpenseDetails").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to update reference.");
                request.getRequestDispatcher("/auditor/ExpenseDetails").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/auditor/ExpenseDetails").forward(request, response);
        }
    }
}
