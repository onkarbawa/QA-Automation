package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import cucumber.api.java.en.And;

/**
 * Created by hitesh.grover on 27/06/17.
 */
public class Account {

    BaseTest baseTest = new BaseTest();
    CustomerBaseTestCucumber customerBaseTestCucumber;

    @And("^I click on 'Sign up' button on Account page$")
    public void iClickOnAccountPageSignUpButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        customerBaseTestCucumber.getAccountPageAndroid().getSignUpButton().click();
    }
}
