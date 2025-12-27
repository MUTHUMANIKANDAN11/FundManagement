package com.management.model;

import java.util.Date;

public class Sponsorship {
	int sponsorship_id;
	int sponsor_id;
	double amount;
	Date sponsorship_date;
	int symp_id;
	
	public Sponsorship(int sponsor_id, double amount, Date sponsorship_date, int symp_id) {
		this.sponsor_id = sponsor_id;
		this.amount = amount;
		this.sponsorship_date = sponsorship_date;
		this.symp_id = symp_id;
	}

	public int getSponsorship_id() {
		return sponsorship_id;
	}

	public int getSponsor_id() {
		return sponsor_id;
	}

	public double getAmount() {
		return amount;
	}

	public Date getSponsorship_date() {
		return sponsorship_date;
	}

	public int getSymp_id() {
		return symp_id;
	}

	public void setSponsorship_id(int sponsorship_id) {
		this.sponsorship_id = sponsorship_id;
	}

	public void setSponsor_id(int sponsor_id) {
		this.sponsor_id = sponsor_id;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setSponsorship_date(Date sponsorship_date) {
		this.sponsorship_date = sponsorship_date;
	}

	public void setSymp_id(int symposium_id) {
		this.symp_id = symposium_id;
	}
}
