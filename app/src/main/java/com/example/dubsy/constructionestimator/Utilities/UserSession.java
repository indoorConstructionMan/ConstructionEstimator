package com.example.dubsy.constructionestimator.Utilities;

public class UserSession {
    int uid;
    String username;
    String email;

    private static volatile UserSession sUserInstance = new UserSession();

    private UserSession(){};

    public static UserSession getInstance() {
        return sUserInstance;
    }

    public void setUserId(int newId){
        this.uid = newId;
    }

    public int getUserId() {
        return this.uid;
    }

    public void setUserName(String uname) {
        this.username = uname;
    }

    public String getUserName() {
        return this.username;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getEmail() {
        return this.email;
    }

}
