package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import cucumber.api.java.en.And;

/**
 * Created by hitesh.grover on 27/06/17.
 */
public class Account {

    BaseTest baseTest = new BaseTest();
    CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();

    @And("^I tap on 'Sign up' button on Account page$")
    public void iClickOnAccountPageSignUpButton() throws Exception {
        Thread.sleep(1000);
        customerBaseTestCucumber.getAccountPageAndroid().getSignUpButton().click();
    }
}
