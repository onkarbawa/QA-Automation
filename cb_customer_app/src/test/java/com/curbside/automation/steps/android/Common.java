package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

/**
 * Created by hitesh.grover on 27/06/17.
 */
public class Common {

    BaseTest baseTest = new BaseTest();
    CustomerBaseTestCucumber customerBaseTestCucumber;

    @And("^I click on 'Account' icon$")
    public void iClickOnAccountButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        customerBaseTestCucumber.getCommonLocatorsPageAndroid().getAccountTabButton().click();
    }

}
