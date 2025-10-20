package com.fundmanagement.dao;

import java.sql.*;

import com.fundmanagement.model.User;

public class UserDao {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/symposium_management";
	static String name = "root";
	static String password = "Mk11MYSQL@2004";
	
	User formUser(ResultSet rs) throws SQLException {
		User user = new User(rs.getString("user_name"), rs.getString("email"), rs.getString("password"), rs.getString("role"), rs.getInt("dept_id"));
		user.setUser_id(rs.getInt("user_id"));
		
		return user;
	}
	
	public User getUserById(int user_id) {
		 User user = null;
		 String query = "Select * from Users where user_id = ?";
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 ps = con.prepareStatement(query);
			 
			 ps.setInt(1, user_id);
			 rs = ps.executeQuery();
			 
			 if(rs.next()) {
				user = formUser(rs);
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
		 
		 return user;
	 }
	
	 public User getUserByLogin(String user_email, String user_password) {
		 User user = null;
		 String query = "Select * from users where email = ? and password = ?";
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 
			 ps = con.prepareStatement(query);
			 ps.setString(1, user_email);
			 ps.setString(2, user_password);
			 
			 rs = ps.executeQuery();
			 
			 if(rs.next()) {
				 user = formUser(rs);
			 }
			 
		 } catch (Exception e) {
			 e.printStackTrace(); 
		 }
		 finally {
			try {if(rs != null) rs.close();} catch (Exception e) {}
			try {if(ps != null) ps.close();} catch (Exception e) {}
			try {if(con != null) con.close();} catch(Exception e) {}
		 }
		 
		 return user;
	 }
	 
	 public User addUser(String user_name, String user_email, String user_password, String role, int dept_id) {
		 User user = null;
		 String query = "Insert into Users (user_name, email, password, role, dept_id) values (?, ?, ?, ?, ?)";
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 
			 ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			 
			 ps.setString(1, user_name);
			 ps.setString(2, user_email);
			 ps.setString(3, user_password);
			 ps.setString(4, role);
			 ps.setInt(5, dept_id);
			 
			 int row = ps.executeUpdate();
			 
			 if(row > 0) {
				 rs = ps.getGeneratedKeys();
				 
				 if(rs.next()) {
					 int user_id = rs.getInt(1);
					 user = new User(user_name, user_email, user_password, role, dept_id);
					 user.setUser_id(user_id);
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
		 
		 return user;
	 }
	 
	 public boolean removeUser(int user_id) {
		 String query = "delete from Users where user_id = ?";
		 Connection con = null;
		 PreparedStatement ps = null;
		 
		 try {
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, name, password);
			 ps = con.prepareStatement(query);
			 
			 ps.setInt(1, user_id);
			 
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
}
