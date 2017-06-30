package com.curbside.automation.steps.ios;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import com.curbside.automation.customerApp.ios.pages.home.HomePageIOS;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class LoginSteps {
    CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();
    
    @And("^I click on 'My Account' icon$")
    public void iClickOnMyAccountIcon() {
        customerBaseTestCucumber.getHomePageIOS().myAccount.click();
    }

    @And("^I click on 'Sign in with Facebook' button$")
    public void iClickOnSignInWithFacebookButton()  {

    }

    @And("^I click on 'log in with Facebook App' button$")
    public void iClickOnLogInWithFacebookAppButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @When("^I click on 'Open'$")
    public void iClickOnOpen() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @Then("^I click on Facebook' to continue$")
    public void iClickOnFacebookToContinue() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @Then("^I add 'Facebook Account' to continue$")
    public void iAddFacebookAccountToContinue() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }
}
