package com.management.model;

import java.util.ArrayList;
import java.util.List;

public class SponsorDetails {
	private Sponsor sponsor;
	double total;
    private List<SponsorshipInfo> sponsorshipInfos = new ArrayList<>();

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<SponsorshipInfo> getSponsorshipInfos() {
        return sponsorshipInfos;
    }

    public void add(Sponsorship sponsorship, Symposium symposium) {
        sponsorshipInfos.add(new SponsorshipInfo(sponsorship, symposium));
    }
}
