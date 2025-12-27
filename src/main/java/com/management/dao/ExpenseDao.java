package com.management.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.management.model.Expense;

public class ExpenseDao {
	static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/symposium_management";
    static String username = "root";
    static String password = "Mk11MYSQL@2004";

    private Expense formExpense(ResultSet rs) throws SQLException {
        Expense expense = new Expense(
            rs.getInt("symp_id"),
            rs.getDouble("amount"),
            rs.getDate("expense_date"),
            rs.getString("purpose"),
            rs.getString("reference")
        );
        expense.setExpense_id(rs.getInt("expense_id"));
        
        return expense;
    }
    
    public double totalExpensePerSymposium(int  symp_id) {
        String query = "select sum(amount) as total_expenses from Expenses where symp_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double total = 0;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, symp_id);

            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total_expenses");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return total;
    }

    public List<Expense> getAllExpenses() {
        String query = "select * from Expenses";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Expense> expenses = new ArrayList<>();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                expenses.add(formExpense(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return expenses;
    }
    
    public List<Expense> getBySympId(int symp_id) {
        String query = "select * from Expenses where symp_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Expense> expenses = new ArrayList<>();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, symp_id);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                expenses.add(formExpense(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return expenses;
    }

    public Expense getExpenseById(int expense_id) {
        String query = "select * from Expenses where expense_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Expense expense = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, expense_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                expense = formExpense(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return expense;
    }

    public Expense addExpense(Expense expense) {
        String query = "insert into Expenses (symp_id, amount, expense_date, purpose, reference) values (?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, expense.getSymp_id());
            ps.setDouble(2, expense.getAmount());
            ps.setDate(3, new java.sql.Date(expense.getExpense_date().getTime()));
            ps.setString(4, expense.getPurpose());
            ps.setString(5, expense.getReference());

            int row = ps.executeUpdate();
            
            if (row > 0) {
                rs = ps.getGeneratedKeys();
                
                if (rs.next()) {
                    expense.setExpense_id(rs.getInt(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return expense;
    }

    public boolean updateExpense(Expense expense) {
        String query = "update Expenses set symp_id = ?, amount = ?, expense_date = ?, purpose = ?, reference = ? where expense_id = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);

            ps.setInt(1, expense.getSymp_id());
            ps.setDouble(2, expense.getAmount());
            ps.setDate(3, new java.sql.Date(expense.getExpense_date().getTime()));
            ps.setString(4, expense.getPurpose());
            ps.setString(5, expense.getReference());
            ps.setInt(6, expense.getExpense_id());

            int row = ps.executeUpdate();
            
            if (row > 0) return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return false;
    }

    public boolean removeExpense(int expense_id) {
        String query = "delete from Expenses where expense_id = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);
            
            ps.setInt(1, expense_id);

            int row = ps.executeUpdate();
            
            if (row > 0) return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return false;
    }
}
