package com.example.apple.interninternational.Beans;

public class Opportunity {

    private String companyName;
    private String location;

    public Opportunity(String companyName, String location) {
        this.companyName = companyName;
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
