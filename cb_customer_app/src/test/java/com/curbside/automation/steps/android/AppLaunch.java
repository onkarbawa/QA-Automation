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
    CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();


    @And("^I scroll left 3 times$")
    public void iClickThreeTimesForScrollToLeft() throws Throwable {
        Thread.sleep(1000);
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().scrollIntroPage(3);
    }

    @And("^I tap on 'Get Started' button on the Intro screen$")
    public void iClickOnGetStartedButtonOnTheIntroPage() throws Throwable {
        System.out.print("getstarted step file");
        Thread.sleep(1000);
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getStartedButton().click();
    }

    @And("^I tap on 'Ok with me' button on Location access screen$")
    public void iClickOnOkWithMeButtonOnAccessPage() throws Throwable {
        Thread.sleep(1000);
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getLocationOkButton().click();
    }

    @When("^I tap on 'Allow Access Location' pop up$")
    public void iClickOnAllowAccessLocationPopUp() throws Throwable {
        try {
            Thread.sleep(1000);
            customerBaseTestCucumber.getApplicationLaunchPageAndroid().getAllowButton().click();
            customerBaseTestCucumber.getApplicationLaunchPageAndroid().getAllowButton().click();
        }catch (Exception e){}

    }

    @Then("^I should see the Nearby Stores screen$")
    public void iShouldSeeTheCurrentLocationButton() throws Throwable {
        Assert.assertTrue(customerBaseTestCucumber.getUtilities().isElementPresent(customerBaseTestCucumber.getCommonLocatorsPageAndroid().accountTabButton),"Android : Not able to land on Near by Stores screen");
    }

    @And("^I tap on 'Skip Intro' button on the screen$")
    public void iClickOnSkipIntroButtonOnTheScreen() throws Throwable {
        System.out.print("skip intro step file");
        customerBaseTestCucumber.getApplicationLaunchPageAndroid().getSkipIntro().click();
    }

    @And("^I land on store selection screen$")
    public void iLandOnStoreSelectionPage() throws Throwable {
        Thread.sleep(1000);
        iClickOnSkipIntroButtonOnTheScreen();
        iClickOnOkWithMeButtonOnAccessPage();
        iClickOnAllowAccessLocationPopUp();
    }
}
