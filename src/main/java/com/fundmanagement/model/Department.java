package com.fundmanagement.model;

import java.util.List;

public class Department {
	int dept_id;
	String dept_name;
	int president_id;
	int auditor_id;
	
	private List<Symposium> symposiums;

	public Department(String dept_name) {
		this.dept_name = dept_name;
	}

	public List<Symposium> getSymposiums() {
		return symposiums;
	}

	public void setSymposiums(List<Symposium> symposiums) {
		this.symposiums = symposiums;
	}

	public int getDept_id() {
		return dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public int getPresident_id() {
		return president_id;
	}

	public void setPresident_id(int president_id) {
		this.president_id = president_id;
	}

	public int getAuditor_id() {
		return auditor_id;
	}

	public void setAuditor_id(int auditor_id) {
		this.auditor_id = auditor_id;
	}
}
