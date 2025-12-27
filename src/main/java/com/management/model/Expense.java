package com.management.model;

import java.sql.Date;

public class Expense {
	int expense_id;
	int symp_id;
	double amount;
	Date expense_date;
	String purpose;
	String reference;
	
	public Expense(int symposium_id, double amount, Date expense_date, String purpose, String reference) {
		super();
		this.symp_id = symposium_id;
		this.amount = amount;
		this.expense_date = expense_date;
		this.purpose = purpose;
		this.reference = reference;
	}
	
	public int getExpense_id() {
		return expense_id;
	}
	public int getSymp_id() {
		return symp_id;
	}
	public double getAmount() {
		return amount;
	}
	public Date getExpense_date() {
		return expense_date;
	}
	public String getPurpose() {
		return purpose;
	}
	public String getReference() {
		return reference;
	}
	public void setExpense_id(int expense_id) {
		this.expense_id = expense_id;
	}
	public void setSymp_id(int symposium_id) {
		this.symp_id = symposium_id;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setExpense_date(Date expense_date) {
		this.expense_date = expense_date;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
}
