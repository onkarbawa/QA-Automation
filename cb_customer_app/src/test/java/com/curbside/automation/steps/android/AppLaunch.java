package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTest;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;


/**
 * Created by hitesh.grover on 23/06/17.
 */
public class AppLaunch extends CustomerBaseTest{
    @Given("^I launch the Android Customer App$")
    public void iLaunchTheAndroidCustomerApp() {
        BaseTest baseTest = new BaseTest();
        baseTest.setUp();
        //CustomerBaseTest customerBaseTest = new CustomerBaseTest(baseTest.driver);
    }

    @And("^I click three times for Scroll to left$")
    public void iClickThreeTimesForScrollToLeft() {
        this.getApplicationLaunchPageAndroid().scrollIntroPage(2);
    }

    @And("^I click on 'Get Started' button on the Intro page$")
    public void iClickOnGetStartedButtonOnTheIntroPage(){
        this.getApplicationLaunchPageAndroid().getStartedButton().click();
    }

    @And("^I click on 'Ok with me' button on access page$")
    public void iClickOnOkWithMeButtonOnAccessPage() {
        this.getApplicationLaunchPageAndroid().getLocationOkButton().click();
    }

    @When("^I click on 'Allow Access Location' pop up$")
    public void iClickOnAllowAccessLocationPopUp() {
        this.getApplicationLaunchPageAndroid().getAllowButton().click();
    }

    @Then("^I should see the 'Current Location'  button$")
    public void iShouldSeeTheCurrentLocationButton() {
        Assert.assertTrue(this.getUtilities().isElementPresent(this.getHomePageAndroid().currentLocationButton),"Android : HomePage is not visible yet");
    }
}
