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
    SettingsIOS settingsIOS ;

    @Given("^I launch the Customer App$")
    public void iLaunchTheCustomerApp () {
      baseTest.setUp();
      customerBaseTestCucumber = new CustomerBaseTestCucumber(BaseTest.driver);
    }

    @And("^I clicked on Allow to send notifications$")
    public void iClickedOnAllowToSendNotifications () {
        customerBaseTestCucumber.getiOSApplicationLaunch().getAllow().click();
    }

    @And("^I click \"([^\"]*)\" times for Scroll left$")
    public void iClickTimesForScrollLeft(int noOfTimes) throws Throwable {
        customerBaseTestCucumber.getiOSApplicationLaunch().doSwipe(noOfTimes);
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
      settingsIOS = new SettingsIOS(BaseTest.driver);
    }

    @And("^I click on Privacy button$")
    public void iClickOnPrivacyButton()  {
        settingsIOS.getPrivacy();
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
      Assert.assertTrue(settingsIOS.isCheckMarkDisplayed(),
              "Checkmark of Always is not enabled");
    }

    @And("^I click on 'Location'$")
    public void iClickOnLocation () {
        settingsIOS.getLocation();
    }

    @And("^I click on Customer App$")
    public void iClickOnCustomerApp() {
        settingsIOS.getCurbsideApp();
    }


    @And("^I click on 'Skip Intro' button$")
    public void iClickOnSkipIntroButton()  {
       customerBaseTestCucumber.getiOSApplicationLaunch().skipInroButton.click();
    }

    @And("^I click on question mark icon in the top left corner$")
    public void iClickOnQuestionMarkIconInTheTopLeftCorner(){
        customerBaseTestCucumber.getiOSApplicationLaunch().tapOnHelpIcon();
    }

    @Then("^I should see 'Curbside Settings Page'$")
    public void iShouldSeeCurbsideSettingsPage() {
        Assert.assertEquals(customerBaseTestCucumber.getiOSApplicationLaunch().curbsideSettings.getText(),
                "Settings", "Settings Page is not displayed");
    }

    @And("^I click on 'Settings' on curbside page$")
    public void iClickOnSettingsOnCurbsidePage() {
        customerBaseTestCucumber.getiOSApplicationLaunch().curbsideSettings.click();
    }

    @And("^I click on 'Background App Refresh' to toggle 'OFF'$")
    public void iClickOnBackgroundAppRefreshToToggleOFF() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I click on 'Retailer partner' at nears by screen$")
    public void iClickOnRetailerPartnerAtNearsByScreen() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I click on 'Product'$")
    public void iClickOnProduct() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I able to view product landing page$")
    public void iAbleToViewProductLandingPage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I click on 'Add To Cart'$")
    public void iClickOnAddToCart() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I click on Cart icon in bottom menu$")
    public void iClickOnCartIconInBottomMenu() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I click on 'Place Order' button$")
    public void iClickOnPlaceOrderButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I checked I am not able to checkout from cart$")
    public void iCheckedIAmNotAbleToCheckoutFromCart() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I click on 'Background App Refresh' to toggle 'ON'$")
    public void iClickOnBackgroundAppRefreshToToggleON() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I checked I am able to checkout from cart$")
    public void iCheckedIAmAbleToCheckoutFromCart() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I click on 'Privacy and emergency'$")
    public void iClickOnPrivacyAndEmergency() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I click on customer button to enable$")
    public void iClickOnCustomerButtonToEnable() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I click on 'Allow' button$")
    public void iClickOnAllowButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the retailer selection page should show up$")
    public void theRetailerSelectionPageShouldShowUp() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I scroll down and click on Curbside App$")
    public void iScrollDownAndClickOnCurbsideApp() {
        settingsIOS.doScrollAndClickOnCurbsideApp();
    }

    @And("^I click on \"([^\"]*)\"$")
    public void iClickOn(String element) {
        settingsIOS.tapOnLocationSelection(element);
    }
}
