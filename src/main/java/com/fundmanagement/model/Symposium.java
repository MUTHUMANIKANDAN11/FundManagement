package com.fundmanagement.model;

import java.sql.Date;

public class Symposium {
	int symp_id;
	int dept_id;
	int academic_year;
	String title;
	Date start_date;
	Date end_date;
	Date sponsor_deadLine;
	Date claim_deadLine;
	double total;
	double allocation;
	double carry_forward;
	int president_id;
	int auditor_id;
	
	public Symposium(int dept_id, int academic_year, String title, Date start_date, Date end_date,
			Date sponsor_deadLine, Date claim_deadLine, double allocation, int president_id,
			int auditor_id) {
		super();
		this.dept_id = dept_id;
		this.academic_year = academic_year;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.sponsor_deadLine = sponsor_deadLine;
		this.claim_deadLine = claim_deadLine;
		this.allocation = allocation;
		this.president_id = president_id;
		this.auditor_id = auditor_id;
		this.total = 0;
		this.carry_forward = 0;
	}
	
	public int getSymp_id() {
		return symp_id;
	}
	public void setSymp_id(int symp_id) {
		this.symp_id = symp_id;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public int getAcademic_year() {
		return academic_year;
	}
	public void setAcademic_year(int academic_year) {
		this.academic_year = academic_year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getSponsor_deadLine() {
		return sponsor_deadLine;
	}
	public void setSponsor_deadLine(Date sponsor_deadLine) {
		this.sponsor_deadLine = sponsor_deadLine;
	}
	public Date getClaim_deadLine() {
		return claim_deadLine;
	}
	public void setClaim_deadLine(Date claim_deadLine) {
		this.claim_deadLine = claim_deadLine;
	}
	public double getAllocation() {
		return allocation;
	}
	public void setAllocation(double allocation) {
		this.allocation = allocation;
	}
	public double getCarry_forward() {
		return carry_forward;
	}
	public void setCarry_forward(double carry_forward) {
		this.carry_forward = carry_forward;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Symposium [symp_id=" + symp_id + ", dept_id=" + dept_id + ", academic_year=" + academic_year
				+ ", title=" + title + ", start_date=" + start_date + ", end_date=" + end_date + ", sponsor_deadLine="
				+ sponsor_deadLine + ", claim_deadLine=" + claim_deadLine + ", allocation=" + allocation
				+ ", carry_forward=" + carry_forward + ", president_id=" + president_id + ", auditor_id=" + auditor_id
				+ "]";
	}
}
