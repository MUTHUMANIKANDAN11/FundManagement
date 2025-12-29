package com.management.model;

public class TotalFundDept {
	private Symposium symposium;
    private double allocation;
    private double carryForward;
    private double sponsors;
    private double totalFunds;
    
    
	public Symposium getSymposium() {
		return symposium;
	}
	public double getAllocation() {
		return allocation;
	}
	public double getCarryForward() {
		return carryForward;
	}
	public double getSponsors() {
		return sponsors;
	}
	public double getTotalFunds() {
		return totalFunds;
	}
	public void setSymposium(Symposium symposium) {
		this.symposium = symposium;
	}
	public void setAllocation(double allocation) {
		this.allocation = allocation;
	}
	public void setCarryForward(double carryForward) {
		this.carryForward = carryForward;
	}
	public void setSponsors(double sponsors) {
		this.sponsors = sponsors;
	}
	public void setTotalFunds(double totalFunds) {
		this.totalFunds = totalFunds;
	}
}