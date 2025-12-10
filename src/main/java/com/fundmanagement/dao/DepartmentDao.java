package com.fundmanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fundmanagement.model.Department;

public class DepartmentDao {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/symposium_management";
	static String name = "root";
	static String password = "Mk11MYSQL@2004";
	
	public boolean isPresentByid(int dept_id) {
		String query = "Select * from Departments where dept_id = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet  rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			ps = con.prepareStatement(query);
			
			ps.setInt(1, dept_id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		}
		
		return false;
	}
	
	public boolean isPresentByName(String dept_name) {
		String query = "Select * from Departments where dept_name = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet  rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			ps = con.prepareStatement(query);
			
			ps.setString(1, dept_name);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		}
		
		return false;
	}
	
	public Department getDepartmentById(int dept_id) {
		String query = "Select * from Departments where dept_id = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet  rs = null;
		Department dept = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			ps = con.prepareStatement(query);
			
			ps.setInt(1, dept_id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dept = new Department(rs.getString("name"));
				dept.setDept_id(dept_id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		}
		
		return dept;
	}
	
	public List<Department> getAllDepartment() {
		String query = "Select * from Departments";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet  rs = null;

		List<Department> depts = new ArrayList<Department>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();
			
			while(rs.next()) {
				Department dept = new Department(rs.getString("dept_name"));
				dept.setDept_id(rs.getInt("dept_id"));
				depts.add(dept);
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
		 
		return depts;
	}
	
	public Department addDepartment(String dept_name) {
		String query = "Insert into Departments (dept_name) values(?)";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Department dept = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, dept_name);
			int row = ps.executeUpdate();
			
			if(row > 0) {
				rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					dept = new Department(dept_name);
					dept.setDept_id(rs.getInt(1));
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
		
		return dept;
	}
	
	public boolean removeDepartment(int dept_id) {
		String query = "delete from Departments where dept_id = ?";
		Connection con = null;
		PreparedStatement ps = null;
		 
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			ps = con.prepareStatement(query);
			 
			ps.setInt(1, dept_id);
			int row = ps.executeUpdate();
			 
			if(row > 0) {
				return true;
			}
			 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		finally {
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		}
		 
		return false;
	}
	
	public boolean updateDepartment(int dept_id, String dept_name) {
		String query = "Update Departments set dept_name = ? where dept_id = ?";
		Connection con = null;
		PreparedStatement ps = null;
		 
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			ps = con.prepareStatement(query);
			 
			ps.setString(1,  dept_name);
			ps.setInt(2, dept_id);
			 
			int row = ps.executeUpdate();
			 
			if(row > 0) {
				return true;
			}
			 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		finally {
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		}
		 
		return false;
	}
	
	public void updatePresident(int deptId, int newPresidentId) throws SQLException {
		 String query = "update Departments set president_id = ? where dept_id = ?";
		 
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 
			 ps = con.prepareStatement(query);
   		
			 ps.setInt(1, newPresidentId);
			 ps.setInt(2, deptId);
			 ps.executeUpdate();
			 
   	 } catch (Exception e) {
			 e.printStackTrace(); 
		 }
		 
		 finally {
			try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		 }
	 }

	 public void updateAuditor(int deptId, int newAuditorId) throws SQLException {
		 String query = "update Departments set auditor_id = ? where dept_id = ?";

		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 
			 ps = con.prepareStatement(query);
   		
			 ps.setInt(1, newAuditorId);
			 ps.setInt(2, deptId);
			 ps.executeUpdate();
			 
   	 } catch (Exception e) {
			 e.printStackTrace(); 
		 }
		 
		 finally {
			try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		 }
	}
}
