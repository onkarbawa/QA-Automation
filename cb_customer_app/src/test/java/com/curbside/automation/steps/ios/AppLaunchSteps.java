package com.curbside.automation.steps.ios;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import com.curbside.automation.customerApp.ios.pages.applicationLaunch.ApplicationLaunchPageIOS;
import com.curbside.automation.customerApp.ios.pages.settingsPage.SettingsIOS;
import com.curbside.automation.customerApp.ios.pages.signUp.SignUpPageIOS;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.MobileDevice;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * Created by bawa.onkar on 6/21/2017.
 */
public class AppLaunchSteps  {

    SettingsIOS settingsIOS ;
    CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();


    @Given("^I launch the iOS Curbside App$")
    public void iLaunchTheIOSCurbsideApp() throws Exception {
        DriverFactory.releaseDriver();
        DriverFactory.getDriver();
    }

    @And("^I accept Allow to send notifications$")
    public void iAcceptAllowToSendNotifications() throws Throwable {
        customerBaseTestCucumber.getiOSApplicationLaunch().allow.click();
    }
    @And("^Swipe left \"([^\"]*)\" times$")
    public void swipeLeftTimes(int noOfTimes) throws Throwable {
        customerBaseTestCucumber.getiOSApplicationLaunch().doSwipe(noOfTimes);
    }


    @And("^I tap on 'Get Started' button$")
    public void iTapOnGetStartedButton () throws Exception {
        customerBaseTestCucumber.getiOSApplicationLaunch().getStartedButton.click();
    }
    @And("^I tap on 'Ok with me' button on access landing screen$")
    public void iTapOnOkWithMeButtonOnAccessLandingScreen() {
        try {
            customerBaseTestCucumber.getiOSApplicationLaunch().okWithMe.click();
        }catch (Exception e){}
    }

    @When("^I accept 'Allow Access Location'$")
    public void iAcceptAllowAccessLocation()  {
        try {
            customerBaseTestCucumber.getiOSApplicationLaunch().allowToAccessCurrentLocation.click();
        }catch (Exception e){}
    }

    @Then("^I should see the 'Store Selection Screen Screen'$")
    public void iShouldSeeTheStoreSelectionScreenScreen() throws Throwable {
        Assert.assertEquals(customerBaseTestCucumber.getiOSApplicationLaunch().currentLocation.getText(), "Current Location",
                "The pointer is not landing on current location page");
    }


    @And("^I tap on 'Settings' application$")
    public void iTapOnSettingsApplication () throws Exception {
        MobileDevice.launchSettings();
       // baseTest.getIOSSettingApp();
      settingsIOS = new SettingsIOS((AppiumDriver) DriverFactory.getDriver());
    }

    @And("^Scroll to Curbside App$")
    public void scrollToCurbsideApp() {
        settingsIOS.doScrollAndClickOnCurbsideApp();
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

    @And("^I tap on 'Location'$")
    public void iTapOnLocation () {
        settingsIOS.getLocation();
    }

    @And("^I click on Customer App$")
    public void iClickOnCustomerApp() {
        settingsIOS.getCurbsideApp();
    }


    @And("^I click on 'Skip Intro' button$")
    public void iClickOnSkipIntroButton() throws Exception {
        customerBaseTestCucumber.getiOSApplicationLaunch().skipInroButton.click();
    }

    @And("^I tap on question mark icon in the top left corner$")
    public void iTapOnQuestionMarkIconInTheTopLeftCorner() throws Exception {
        customerBaseTestCucumber.getiOSApplicationLaunch().tapOnHelpIcon();
    }

    @Then("^I should see 'Curbside Settings Page'$")
    public void iShouldSeeCurbsideSettingsPage() throws Exception {
        Assert.assertEquals(customerBaseTestCucumber.getiOSApplicationLaunch().curbsideSettings.getText(),
                "SettingsAndroid", "SettingsAndroid Page is not displayed");
    }

    @And("^I click on 'Settings' on curbside page$")
    public void iClickOnSettingsOnCurbsidePage() throws Exception {
        customerBaseTestCucumber.getiOSApplicationLaunch().curbsideSettings.click();
    }

    @And("^I tap on 'Background App Refresh' to toggle 'OFF'$")
    public void iTapOnBackgroundAppRefreshToToggleOFF() throws Throwable {
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



    @And("^I tap on \"([^\"]*)\"$")
    public void iTapOn(String element) {
        settingsIOS.tapOnLocationSelection(element);
    }

    @And("^I close the web driver session for ios$")
    public void iCloseTheWebDriverSessionTt() throws Throwable {
        DriverFactory.releaseDriver();
    }



}
