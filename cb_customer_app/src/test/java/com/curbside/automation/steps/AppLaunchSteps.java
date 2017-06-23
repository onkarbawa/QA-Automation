package com.curbside.automation.steps;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTest;
import com.curbside.automation.customerApp.ios.pages.applicationLaunch.ApplicationLaunchPageIOS;
import com.curbside.automation.customerApp.ios.pages.settingsPage.Settings;
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
    ApplicationLaunchPageIOS applicationLaunchPageIOS;// = new ApplicationLaunchPageIOS(BaseTest.driver);
    Settings settings;
    @Given("^I launch the Customer App$")
    public void iLaunchTheCustomerApp () {
      baseTest.setUp();
      applicationLaunchPageIOS = new ApplicationLaunchPageIOS(BaseTest.driver);
      settings = new Settings(BaseTest.driver);
    }

    @And("^I clicked on Allow to send notifications$")
    public void iClickedOnAllowToSendNotifications () {
       applicationLaunchPageIOS.getAllow().click();
    }

    @And("^I click three times for Scroll left$")
    public void iClickTimesForScrollLeft () {
        applicationLaunchPageIOS.doSwipe(2);
    }

    @And("^I click on 'Get Started' button$")
    public void iClickOnGetStartedButton () {
        applicationLaunchPageIOS.getStarted().click();
    }

    @And("^I click on 'Ok with me' button on access landing page$")
    public void iClickOnOkWithMeButtonOnAccessLandingPage () {
        applicationLaunchPageIOS.getOkWithMe().click();
    }

    @When("^I click on 'Allow Access Location' button$")
    public void iClickOnAllowAccessLocationButton () {
        applicationLaunchPageIOS.getAllowLocation().click();
    }

    @Then("^I should see the 'Store Selection Page'$")
    public void iShouldSeeTheStoreSelectionPage () {
      Assert.assertEquals(applicationLaunchPageIOS.getCurrentLocation().getText(), "Current Location",
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
        settings.doScroll();
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
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on question mark icon in the top left corner$")
    public void iClickOnQuestionMarkIconInTheTopLeftCorner () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on 'Skip Intro' button$")
    public void iClickOnSkipIntroButton () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on 'Location' & select 'NEVER'$")
    public void iClickOnLocationSelectNEVER () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on 'Home' button from device$")
    public void iClickOnHomeButtonFromDevice () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on Customer app to launch$")
    public void iClickOnCustomerAppToLaunch () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on 'Background App Refresh' to toggle 'OFF'$")
    public void iClickOnBackgroundAppRefreshToToggleOFF () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on 'Retailer partner' at nears by screen$")
    public void iClickOnRetailerPartnerAtNearsByScreen () {
      // Write code here that turns the phrase above into concrete actions
    }

    @When("^I click on 'Product'$")
    public void iClickOnProduct () {
      // Write code here that turns the phrase above into concrete actions
    }

    @Then("^I able to view product landing page$")
    public void iAbleToViewProductLandingPage () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on 'Add To Cart'$")
    public void iClickOnAddToCart () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on Cart icon in bottom menu$")
    public void iClickOnCartIconInBottomMenu () {
      // Write code here that turns the phrase above into concrete actions
    }

    @When("^I click on 'Place Order' button$")
    public void iClickOnPlaceOrder$xxXxButton () {
      // Write code here that turns the phrase above into concrete actions
    }

    @Then("^I checked I am not able to checkout from cart$")
    public void iCheckedIAmNotAbleToCheckoutFromCart () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on 'Background App Refresh' to toggle 'ON'$")
    public void iClickOnBackgroundAppRefreshToToggleON () {
      // Write code here that turns the phrase above into concrete actions
    }

    @Then("^I checked I am able to checkout from cart$")
    public void iCheckedIAmAbleToCheckoutFromCart () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on 'Privacy and emergency'$")
    public void iClickOnPrivacyAndEmergency () {
      // Write code here that turns the phrase above into concrete actions
    }

    @And("^I click on 'Location'$")
    public void iClickOnLocation () {
      settings.getPrivacy();
    }

    @And("^I click on customer button to enable$")
    public void iClickOnCustomerButtonToEnable () {
      // Write code here that turns the phrase above into concrete actions
    }


    @When("^I scroll down & click on Customer App and click$")
    public void iScrollDownClickOnCustomerAppAndClick() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
