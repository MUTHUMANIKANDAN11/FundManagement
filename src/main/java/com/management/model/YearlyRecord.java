package com.management.model;

import java.util.List;

public class YearlyRecord {
	private Symposium symposium;
    private List<Sponsorship> sponsors;
    private List<Expense> expenses;
    
    
    public Symposium getSymposium() {
    	return symposium;
    }
    public List<Sponsorship> getSponsors() {
    	return sponsors;
    }
    public List<Expense> getExpenses() {
    	return expenses;
    }
    public void setSymposium(Symposium symposium) {
    	this.symposium = symposium;
    }
    public void setSponsors(List<Sponsorship> sponsors) {
    	this.sponsors = sponsors;
    }
    public void setExpenses(List<Expense> expenses) {
    	this.expenses = expenses;
    }
	@Override
	public String toString() {
		return "YearlyRecord [symposium=" + symposium + ", sponsors=" + sponsors + ", expenses=" + expenses + "]";
	}
    
    
}
