package com.example.dubsy.constructionestimator.Database.Model;

public class ContractsModel {

    public String siteAddress;
    private int boardFootage;
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
        try {
            return this.siteAddress;
        } catch (Exception e) {
            return " ";
        }
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

        if (m instanceof ContractsModel) {
            String mSiteAddress = ((ContractsModel) m).getSiteAddress();
            int mBoardFootage = ((ContractsModel) m).getBoardFootage();
            return m == this || this.getSiteAddress().equals(mSiteAddress) && this.getBoardFootage() == mBoardFootage;
        }
        return false;

    }
}
