package com.example.dubsy.constructionestimator.Database.Model;

public class UsersModel {
    int uid;
    String username;
    String emailAddress;
    String password;

    public UsersModel(String username, String emailAddress, String hashedPassword) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = hashedPassword;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setEmailAddress(String newEmailAddress) {
        this.emailAddress = newEmailAddress;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setHashedPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getHashedPassword() {
        return this.password;
    }

    public void setId(int id) {
        this.uid = id;
    }

    public int getId() {
        return this.uid;
    }

}
