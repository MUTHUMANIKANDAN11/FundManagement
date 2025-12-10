package com.fundmanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fundmanagement.model.Symposium;

public class SymposiumDao {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/symposium_management";
	static String name = "root";
	static String password = "Mk11MYSQL@2004";
	
	Symposium formSymposium(ResultSet rs) throws SQLException {
		Symposium symp = new Symposium(
				rs.getInt("dept_id"),
	            rs.getInt("academic_year"),
	            rs.getString("title"), 
	            rs.getDate("start_date"),
	            rs.getDate("end_date"),
	            rs.getDate("sponsor_deadLine"),
	            rs.getDate("claim_deadLine"), 
	            rs.getDouble("allocation"),
	            rs.getInt("president_id"), 
	            rs.getInt("auditor_id")
		);
		
		symp.setSymp_id(rs.getInt("symp_id"));
		
		return symp;
	}
	
	public List<Symposium> getAllSymposium(){
		 List<Symposium> symps = new ArrayList<>();
		 String query = "Select * from Symposiums";
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 ps = con.prepareStatement(query);

			 rs = ps.executeQuery();
			 
			 while(rs.next()) {
				Symposium symp = formSymposium(rs);
				symp.setCarry_forward(rs.getDouble("carry_forward"));
				symp.setTotal(rs.getDouble("total"));
				
				symps.add(symp);
			 }

		 }
		 catch (Exception e) {
			 e.printStackTrace(); 
			 System.out.println("Error " + e);
		 }
		 finally {
			 try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		 }
		 
		 return symps;
	 }
	
	public List<Symposium> getSymposiumByDept_id(int dept_id) {
		 List<Symposium> symps = new ArrayList<> ();
		 String query = "Select * from Symposiums where dept_id = ? order by academic_year desc";
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 ps = con.prepareStatement(query);
			 
			 ps.setInt(1, dept_id);
			 rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 Symposium symp = formSymposium(rs);
				 symp.setCarry_forward(rs.getDouble("carry_forward"));
				 symp.setTotal(rs.getDouble("total"));
				 symps.add(symp);
			 }
			 
		 }
		 catch (Exception e) {
			 e.printStackTrace(); 
		 }
		 finally {
			 try {if(rs != null) rs.close();} catch (Exception e) {}
			 try {if(ps != null) ps.close();} catch (Exception e) {}
			 try {if(con != null) con.close();} catch(Exception e) {}
		 }
		 
		 return symps;
	 }
	
	public Symposium getSymposiumById(int symp_id) {
		 Symposium symp = null;
		 String query = "Select * from Symposiums where symp_id = ?";
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 ps = con.prepareStatement(query);
			 
			 ps.setInt(1, symp_id);
			 rs = ps.executeQuery();
			 
			 if(rs.next()) {
				symp = formSymposium(rs);
				symp.setCarry_forward(rs.getDouble("carry_forward"));
				symp.setTotal(rs.getDouble("total"));
			 }

		 }
		 catch (Exception e) {
			 e.printStackTrace(); 
		 }
		 finally {
			try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		 }
		 
		 return symp;
	 }
	
	public List<Symposium> getSymposiumByAcademic_yearAndDept_id(int year, int dept_id) {
		 String query = "select * from Symposiums where academic_year = ? and dept_id = ?";

		 List<Symposium> symps = new ArrayList<> ();
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 ps = con.prepareStatement(query);
			 
			 ps.setInt(1, year);
			 ps.setInt(2, dept_id);

			 rs = ps.executeQuery();
			 
			 while(rs.next()) {
				Symposium symp = formSymposium(rs);
				symp.setCarry_forward(rs.getDouble("carry_forward"));
				symp.setTotal(rs.getDouble("total"));
				symps.add(symp);
			 }

		 }
		 catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		 }
		 
		 return symps;
	 }
	
	public Symposium addSymposium(Symposium symp) {
		 String query = "Insert into Symposiums (dept_id, title, academic_year, start_date, end_date, sponsor_deadline, claim_deadline, allocation, president_id, auditor_id, carry_forward, total)"
		 		+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 
			 ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			 
			 ps.setInt(1, symp.getDept_id());
	         ps.setString(2, symp.getTitle());
	         ps.setInt(3, symp.getAcademic_year());
	         ps.setDate(4, new java.sql.Date(symp.getStart_date().getTime()));
	         ps.setDate(5, new java.sql.Date(symp.getEnd_date().getTime()));
	         ps.setDate(6, new java.sql.Date(symp.getSponsor_deadLine().getTime()));
	         ps.setDate(7, new java.sql.Date(symp.getClaim_deadLine().getTime()));
	         ps.setDouble(8, symp.getAllocation());
	         ps.setInt(9, symp.getPresident_id());
	         ps.setInt(10, symp.getAuditor_id());
	         ps.setDouble(11, symp.getCarry_forward());
	         ps.setDouble(12, symp.getTotal());
			 
			 int row = ps.executeUpdate();
			 
			 if(row > 0) {
				 rs = ps.getGeneratedKeys();
				 
				 if(rs.next()) {
					 int symp_id = rs.getInt(1);
					 symp.setSymp_id(symp_id);
				 }		 
			 }
			 
		 } catch (Exception e) {
			 e.printStackTrace(); 
		 }
		 finally {
			try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		 }
		 
		 return symp;
	 }
	
	public double getCarryForwardSum(int deptId, int year) throws SQLException {
	    String query = "select sum(total) as total from Symposiums where dept_id = ? and academic_year = ?";
	    
	    Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		double total = 0;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			ps = con.prepareStatement(query);
			
	        ps.setInt(1, deptId);
	        ps.setInt(2, year - 1);
	        
	        rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
	        
	    } catch (Exception e) {
			 e.printStackTrace(); 
		}
		finally {
			try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		}
	    return total;
	}
	
	public void resetTotalAmount(int deptId, int year) throws SQLException {
	    String query = "update Symposiums set total = 0 where dept_id = ? and academic_year = ?";
	    
	    Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			ps = con.prepareStatement(query);
			
	        ps.setInt(1, deptId);
	        ps.setInt(2, year - 1);
	        
	        ps.executeUpdate();
	        
	    } catch (Exception e) {
			 e.printStackTrace(); 
		}
		finally {
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		}
	}
	
	public void updateTotal(int sympId, double newTotal) {
        String query = "UPDATE Symposiums SET total = ? WHERE symp_id = ?";
        try (Connection con = DriverManager.getConnection(url, name, password);
             PreparedStatement ps = con.prepareStatement(query)) {

            Class.forName(driver);
            ps.setDouble(1, newTotal);
            ps.setInt(2, sympId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
