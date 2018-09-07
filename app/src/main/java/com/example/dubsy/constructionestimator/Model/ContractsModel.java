package com.example.dubsy.constructionestimator.Model;

public class ContractsModel {

    public String siteAddress;
    public int boardFootage;
    public float rate;

    public ContractsModel(String address, String footage, String rate){
        this.siteAddress = address;
        this.boardFootage = Integer.parseInt(footage);
        this.rate = Float.parseFloat(rate);
    }

    public void setSiteAddress(String newAddress) {
        this.siteAddress = newAddress;
    }

    public String getSiteAddress() {
        return this.siteAddress;
    }

    public void setBoardFootage(String footage) {
        this.boardFootage = Integer.parseInt(footage);
    }

    public int getBoardFootage() {
        return this.boardFootage;
    }

    public void setRate(String rate) {
        this.rate = Float.parseFloat(rate);
    }

    public float getRate() {
        return this.rate;
    }
}
