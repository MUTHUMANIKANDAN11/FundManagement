package com.management.model;

public class SponsorshipInfo {
    private Sponsorship sponsorship;
    private Symposium symposium;

    public SponsorshipInfo(Sponsorship sponsorship, Symposium symposium) {
        this.sponsorship = sponsorship;
        this.symposium = symposium;
    }

    public Sponsorship getSponsorship() {
        return sponsorship;
    }

    public Symposium getSymposium() {
        return symposium;
    }
}