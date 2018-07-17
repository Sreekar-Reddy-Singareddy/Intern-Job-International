package com.example.apple.interninternational.DataHolders;

public class InternationalCountry {

    private String whatTitle = "";
    private String whoTitle = "";
    private String whenTitle = "";

    private String whatContent = "";
    private String whoContent = "";
    private String whenContent = "";

    private String mainDesc = "";
    private String price = "";
    private String brochureUrl = "";

    public InternationalCountry(String whatTitle, String whoTitle, String whenTitle, String whatContent, String whoContent, String whenContent, String mainDesc, String price, String brochureUrl) {
        this.whatTitle = whatTitle;
        this.whoTitle = whoTitle;
        this.whenTitle = whenTitle;
        this.whatContent = whatContent;
        this.whoContent = whoContent;
        this.whenContent = whenContent;
        this.mainDesc = mainDesc;
        this.price = price;
        this.brochureUrl = brochureUrl;
    }

    public String getWhatTitle() {
        return whatTitle;
    }

    public void setWhatTitle(String whatTitle) {
        this.whatTitle = whatTitle;
    }

    public String getWhoTitle() {
        return whoTitle;
    }

    public void setWhoTitle(String whoTitle) {
        this.whoTitle = whoTitle;
    }

    public String getWhenTitle() {
        return whenTitle;
    }

    public void setWhenTitle(String whenTitle) {
        this.whenTitle = whenTitle;
    }

    public String getWhatContent() {
        return whatContent;
    }

    public void setWhatContent(String whatContent) {
        this.whatContent = whatContent;
    }

    public String getWhoContent() {
        return whoContent;
    }

    public void setWhoContent(String whoContent) {
        this.whoContent = whoContent;
    }

    public String getWhenContent() {
        return whenContent;
    }

    public void setWhenContent(String whenContent) {
        this.whenContent = whenContent;
    }

    public String getMainDesc() {
        return mainDesc;
    }

    public void setMainDesc(String mainDesc) {
        this.mainDesc = mainDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrochureUrl() {
        return brochureUrl;
    }

    public void setBrochureUrl(String brochureUrl) {
        this.brochureUrl = brochureUrl;
    }
}
