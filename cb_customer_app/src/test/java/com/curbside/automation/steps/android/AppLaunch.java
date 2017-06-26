package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.android.pages.applicationLaunch.ApplicationLaunchPageAndroid;
import com.curbside.automation.customerApp.common.CustomerBaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;


/**
 * Created by hitesh.grover on 23/06/17.
 */
public class AppLaunch {
    BaseTest baseTest = new BaseTest();
    CustomerBaseTestCucumber customerBaseTestCucumber;


    @Given("^I launch the Android Customer App$")
    public void iLaunchTheAndroidCustomerApp() {
        baseTest.setUp();
        customerBaseTestCucumber = new CustomerBaseTestCucumber(baseTest.driver);
    }

    @And("^I click three times for Scroll to left$")
    public void iClickThreeTimesForScrollToLeft() {
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().scrollIntroPage(2);
    }

    @And("^I click on 'Get Started' button on the Intro page$")
    public void iClickOnGetStartedButtonOnTheIntroPage(){
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getStartedButton().click();
    }

    @And("^I click on 'Ok with me' button on access page$")
    public void iClickOnOkWithMeButtonOnAccessPage() {
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getLocationOkButton().click();
    }

    @When("^I click on 'Allow Access Location' pop up$")
    public void iClickOnAllowAccessLocationPopUp() {
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getAllowButton().click();
    }

    @Then("^I should see the 'Current Location'  button$")
    public void iShouldSeeTheCurrentLocationButton() {
        Assert.assertTrue(customerBaseTestCucumber.getUtilities().isElementPresent(customerBaseTestCucumber.getHomePageAndroid().currentLocationButton),"Android : HomePage is not visible yet");
    }

    @And("^I click on 'Skip Intro' button on the screen$")
    public void iClickOnSkipIntroButtonOnTheScreen()  {
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getSkipIntro().click();
    }
}
