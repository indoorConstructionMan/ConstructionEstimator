package com.example.dubsy.constructionestimator.Database.Model;

public class ContractsModel {

    public String siteAddress;
    public int boardFootage;
    public int rate;

    public ContractsModel(String address, String footage, String rate){

        this.siteAddress = address;
        try {
            this.boardFootage = Integer.parseInt(footage);
        } catch (Exception e){
            this.boardFootage = 0;
        }

        try {
            this.rate = Integer.parseInt(rate);
        } catch (Exception e){
            this.rate = 0;
        }

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
        this.rate = Integer.parseInt(rate);
    }

    public int getRate() {
        return this.rate;
    }

    @Override
    public boolean equals(Object m) {
        if (m == this) {
            return true;
        } else if (!(m instanceof ContractsModel)) {
            return false;
        } else {
            if (!(!this.getSiteAddress().equals(((ContractsModel) m).getSiteAddress()) ||
                    !(this.getBoardFootage() == (((ContractsModel) m).getBoardFootage())))) {
                return true;
            }
            return false;
        }

    }
}
