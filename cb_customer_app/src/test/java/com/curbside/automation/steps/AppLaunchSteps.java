package com.curbside.automation.steps;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import com.curbside.automation.customerApp.ios.pages.settingsPage.SettingsIOS;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * Created by kumar.nipun on 6/21/2017.
 */
public class AppLaunchSteps  {

    BaseTest baseTest = new BaseTest();
    CustomerBaseTestCucumber customerBaseTestCucumber;

    @Given("^I launch the Customer App$")
    public void iLaunchTheCustomerApp () {
      baseTest.setUp();
      customerBaseTestCucumber = new CustomerBaseTestCucumber(BaseTest.driver);
    }

    @And("^I clicked on Allow to send notifications$")
    public void iClickedOnAllowToSendNotifications () {
        customerBaseTestCucumber.getiOSApplicationLaunch().getAllow().click();
    }

    @And("^I click three times for Scroll left$")
    public void iClickTimesForScrollLeft () {
        customerBaseTestCucumber.getiOSApplicationLaunch().doSwipe(2);
    }

    @And("^I click on 'Get Started' button$")
    public void iClickOnGetStartedButton () {
        customerBaseTestCucumber.getiOSApplicationLaunch().getStarted().click();
    }

    @And("^I click on 'Ok with me' button on access landing page$")
    public void iClickOnOkWithMeButtonOnAccessLandingPage () {
        customerBaseTestCucumber.getiOSApplicationLaunch().getOkWithMe().click();
    }

    @When("^I click on 'Allow Access Location' button$")
    public void iClickOnAllowAccessLocationButton () {
        customerBaseTestCucumber.getiOSApplicationLaunch().getAllowLocation().click();
    }

    @Then("^I should see the 'Store Selection Page'$")
    public void iShouldSeeTheStoreSelectionPage () {
      Assert.assertEquals(customerBaseTestCucumber.getiOSApplicationLaunch().getCurrentLocation().getText(), "Current Location",
              "The pointer is not landing on current location page");
    }

    @And("^I click on Home button$")
    public void iClickOnHomeButton () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on 'Settings' application$")
    public void iClickOnSettingsApplication () {
      baseTest.getIOSSettingApp();
    }

    @And("^I click on Privacy button$")
    public void iClickOnPrivacyButton()  {
        customerBaseTestCucumber.getIosSettings().doScroll();
    }
    @And("^I scroll down & click on Customer App to launch$")
    public void iScrollDownClickOnCustomerAppToLaunch () {

    }

    @When("^I click on 'Location' & select 'On'$")
    public void iClickOnLocationSelectOn () {
      // Write code here that turns the phrase above into concrete actions
    }

    @Then("^I saw checkmark is set 'Always'$")
    public void iSawCheckmarkIsSetAlways () {
      Assert.assertTrue(customerBaseTestCucumber.getIosSettings().alwaysenabled.isDisplayed(),
              "Checkmark of Always is not enabled");
    }

    @And("^I click on 'Location'$")
    public void iClickOnLocation () {
        customerBaseTestCucumber.getIosSettings().getLocation();
    }

    @When("^I scroll down & click on Customer App and click$")
    public void iScrollDownClickOnCustomerAppAndClick()  {
        customerBaseTestCucumber.getIosSettings().getCurbsideApp();
    }
}
