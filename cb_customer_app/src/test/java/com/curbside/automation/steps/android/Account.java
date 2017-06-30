package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

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

    @Then("^I should see the Account details with Email id in it$")
    public void iShouldSeeTheAccountDetailsSameAsProvidedOneS() throws Exception {
        Thread.sleep(1000);
        Assert.assertTrue(customerBaseTestCucumber.getAccountPageAndroid().getEmail().isDisplayed(),"Android : User is not able to Sign up");
    }
}
