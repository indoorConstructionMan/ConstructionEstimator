package com.example.dubsy.constructionestimator;

import com.example.dubsy.constructionestimator.Database.Model.ContractsModel;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContractsModelUnitTest {

    @Test
    public void contractObjectCreated() {
        ContractsModel created = new ContractsModel("new york", "1211", "0.5");
        assertEquals(true, created instanceof ContractsModel);
    }

    @Test
    public void contractObjectEmptyInput() {
        ContractsModel created = new ContractsModel("", "", "");
        assertEquals("", created.getSiteAddress());
    }

    @Test
    public void contractObjectConstructorBadInput() {
        ContractsModel badInput = new ContractsModel("34","222vva","11ae");
        assertEquals(0.0f, badInput.getRate(), 0.001);
    }

    @Test
    public void contractObjectSetAndGetSiteAddress(){
        ContractsModel changeSiteAddress = new ContractsModel("old place","","");
        assertEquals("old place", changeSiteAddress.getSiteAddress());
        changeSiteAddress.setSiteAddress("new place");
        assertEquals("new place", changeSiteAddress.getSiteAddress());
    }

    @Test
    public void contractObjectSetAndGetBoardFootage() {
        ContractsModel changeBoardFootage = new ContractsModel("old place","444","");
        assertEquals(444, changeBoardFootage.getBoardFootage());
        changeBoardFootage.setBoardFootage("555");
        assertEquals(555, changeBoardFootage.getBoardFootage());
    }

    @Test
    public void contractObjectSetAndGetRate() {
        ContractsModel changeRate = new ContractsModel("old place","444","0.5");
        assertEquals(0.5f, changeRate.getRate(), 0.001);
        changeRate.setRate("0.24");
        assertEquals(0.24f, changeRate.getRate(), 0.001);
    }

    @Test
    public void contractObjectEquals() {
        ContractsModel contract = new ContractsModel("jim","1234", "0.5");
        assertEquals(true, contract.equals(new ContractsModel("jim","1234", "0.5")));
        assertEquals(false, contract.equals(new ContractsModel("jimmy","1234", "0.5")));
    }

}
