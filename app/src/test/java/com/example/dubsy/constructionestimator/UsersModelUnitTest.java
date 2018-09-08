package com.example.dubsy.constructionestimator;

import com.example.dubsy.constructionestimator.Database.Model.UsersModel;

import org.junit.Test;
import static org.junit.Assert.*;

public class UsersModelUnitTest {
    @Test
    public void usersObjectCreated() {
        UsersModel created = new UsersModel("aaron", "somethin@yahoo.com", "alg43adf5q3fg34");
        assertEquals(true, created instanceof UsersModel);
    }

    @Test
    public void usersObjectEmptyInput() {
        UsersModel created = new UsersModel("", "", "");
        assertEquals("", created.getUsername());
        assertEquals("", created.getEmailAddress());
        assertEquals("", created.getHashedPassword());
    }

    @Test
    public void usersObjectSetAndGetUsername() {
        UsersModel username = new UsersModel("aaron", "somethin@yahoo.com", "alg43adf5q3fg34");
        assertEquals("aaron", username.getUsername());
        username.setUsername("Aaron");
        assertEquals("Aaron", username.getUsername());
    }

    @Test
    public void usersObjectSetAndGetEmailAddress() {
        UsersModel username = new UsersModel("aaron", "somethin@yahoo.com", "alg43adf5q3fg34");
        assertEquals("somethin@yahoo.com", username.getEmailAddress());
        username.setEmailAddress("legitimateEmailAddress@cool.ca");
        assertEquals("legitimateEmailAddress@cool.ca", username.getEmailAddress());
    }

    @Test
    public void usersObjectSetAndGetHashedPassword() {
        UsersModel username = new UsersModel("aaron", "somethin@yahoo.com", "alg43adf5q3fg34");
        assertEquals("alg43adf5q3fg34", username.getHashedPassword());
        username.setHashedPassword("2342342342fq34fqwv");
        assertEquals("2342342342fq34fqwv", username.getHashedPassword());
    }



}
