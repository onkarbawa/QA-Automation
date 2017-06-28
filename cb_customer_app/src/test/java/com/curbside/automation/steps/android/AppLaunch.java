package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.android.pages.applicationLaunch.ApplicationLaunchPageAndroid;
import com.curbside.automation.customerApp.common.CustomerBaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import com.curbside.automation.uifactory.DriverFactory;
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
    CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();

    @Given("^I launch the Android Customer App$")
    public void iLaunchTheAndroidCustomerApp() throws Exception {
        DriverFactory.getDriver();
    }

    @And("^I click three times for Scroll to left$")
    public void iClickThreeTimesForScrollToLeft() throws Exception {
        Thread.sleep(1000);
        System.out.print("scrolling step file");
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().scrollIntroPage(4);
    }

    @And("^I click on 'Get Started' button on the Intro page$")
    public void iClickOnGetStartedButtonOnTheIntroPage() throws Exception {
        System.out.print("getstarted step file");
        Thread.sleep(1000);
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getStartedButton().click();
    }

    @And("^I click on 'Ok with me' button on access page$")
    public void iClickOnOkWithMeButtonOnAccessPage() throws Exception {
        Thread.sleep(1000);
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getLocationOkButton().click();
    }

    @When("^I click on 'Allow Access Location' pop up$")
    public void iClickOnAllowAccessLocationPopUp() throws Exception {
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getAllowButton().click();
        Thread.sleep(1000);
    }

    @Then("^I should see the 'Current Location'  button$")
    public void iShouldSeeTheCurrentLocationButton() throws Exception {
        Assert.assertTrue(customerBaseTestCucumber.getUtilities().isElementPresent(customerBaseTestCucumber.getHomePageAndroid().currentLocationButton),"Android : HomePage is not visible yet");
    }

    @And("^I click on 'Skip Intro' button on the screen$")
    public void iClickOnSkipIntroButtonOnTheScreen() throws Exception {
        System.out.print("skip intro step file");
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getSkipIntro().click();
    }

    @And("^I click on 'Allow Access Location' pop up if visible again$")
    public void iCheckAndClickOnAllowAccessLocationPopUp() throws Exception {
        if(customerBaseTestCucumber.getUtilities().isElementPresent(customerBaseTestCucumber.getApplicationLaunchPageAndroid().allowButton))
            customerBaseTestCucumber.getApplicationLaunchPageAndroid().getAllowButton().click();
    }

    @And("^I land on store selection page$")
    public void iLandOnStoreSelectionPage() throws Exception {
        Thread.sleep(1000);
        iClickOnSkipIntroButtonOnTheScreen();
        iClickOnOkWithMeButtonOnAccessPage();
        iClickOnAllowAccessLocationPopUp();
        iCheckAndClickOnAllowAccessLocationPopUp();


    }
}
