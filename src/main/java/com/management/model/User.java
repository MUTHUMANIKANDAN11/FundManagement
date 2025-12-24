package com.management.model;

public class User {
	private int user_id;
	private String name;
	private String email;
	private String password;
	private String role;
	private int dept_id;
	
	public User(String name, String email, String password, String role, int dept_id) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.dept_id = dept_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public int getDept_id() {
		return dept_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + ", dept_id=" + dept_id + "]";
	}
}