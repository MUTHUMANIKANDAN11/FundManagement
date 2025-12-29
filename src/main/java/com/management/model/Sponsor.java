package com.management.model;

public class Sponsor {
	int sponsor_id;
	String name;
	String contact_info;
	boolean state;
	int dept_id;

	public Sponsor(String name, String contact_info, int dept_id) {
		super();
		this.name = name;
		this.contact_info = contact_info;
		this.dept_id = dept_id;
	}
	
	public int getSponsor_id() {
		return sponsor_id;
	}
	public String getName() {
		return name;
	}
	public String getContact_info() {
		return contact_info;
	}
	public void setSponsor_id(int sponsor_id) {
		this.sponsor_id = sponsor_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getDept_id() {
		return dept_id;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

	@Override
	public String toString() {
		return "Sponsor [sponsor_id=" + sponsor_id + ", name=" + name + ", contact_info=" + contact_info + ", state="
				+ state + ", dept_id=" + dept_id + "]";
	}
}