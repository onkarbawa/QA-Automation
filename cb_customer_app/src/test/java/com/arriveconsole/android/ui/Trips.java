package com.arriveconsole.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 08/01/18.
 */
public class Trips extends AbstractScreen {

    UIElement siteName = UIElement.byXpath("//android.widget.ImageView[@resource-id='com.curbside.arriveconsole:id/imgMap']/../android.widget.TextView");
    UIElement openTrips = UIElement.byId("com.curbside.arriveconsole:id/tvSiteCustomerName");
    // openTrips = //android.widget.TextView[@resource-id='com.curbside.arriveconsole:id/tvSiteCustomerName' and @text='John Doe 2']
    UIElement btnMap = UIElement.byId("com.curbside.arriveconsole:id/imgMap");
    UIElement mapPin = UIElement.byXpath("//android.view.View[@content-desc='Google Map']/android.view.View");
    UIElement btnHome = UIElement.byXpath("//android.widget.ImageView[@resource-id='com.curbside.arriveconsole:id/imgMap']/../android.widget.ImageButton");
    UIElement btnCancelAll = UIElement.byId("com.curbside.arriveconsole:id/bCancelAll");
    UIElement btnChangeSite = UIElement.byId("android:id/button1");


    @Then("^I saw site header name and current open trips (.*) map$")
    public void iSawSiteHeaderNameAndCurrentOpenTrips(String selection) throws Throwable {
        Assert.assertEquals(siteName.getText(), Properties.getVariable("selectedSite"), "Current site " +
                "name is not displayed");
        Assert.assertTrue(openTrips.waitFor(5).isDisplayed(), "Open trips is not displayed");
        if (selection.equalsIgnoreCase("without")) {
            Assert.assertFalse(mapPin.waitFor(5).isDisplayed(), "Map view is displayed");
        } else if (selection.equalsIgnoreCase("with")) {
            btnMap.tap();
            Assert.assertTrue(mapPin.waitFor(10).isDisplayed(), "Map view is not displayed");
        } else
            Assert.fail("Please use (with | without) option only");
        MobileDevice.getScreenshot(true);
    }

    @And("^I am on arriveConsole home screen$")
    public void iAmOnArriveConsoleHomeScreen() throws Throwable {
        btnHome.tap();
        btnChangeSite.waitFor(2).tap();
        MobileDevice.getScreenshot(true);
    }

    @And("I generate trips if not present")
    public void iAddTrips() throws Throwable {
        int openTripsCount = 0;
        commonSteps.acceptNotificationAlert();
        welcomeScreen.iConfirmThatCurrentSiteIsSelected();
        Steps.tapButton("VIEW TRIPS");
        openTrips.waitFor(10);
        openTripsCount = openTrips.getCount();
        if (openTripsCount < 1) {
            commonSteps.launchApplicationClean("ARRIVE Console Tester", "first");
            arriveTester.iStartSampleTrip(2);
        } else if (openTripsCount < 2) {
            commonSteps.launchApplicationClean("ARRIVE Console Tester", "first");
            arriveTester.iStartSampleTrip(1);
        }
    }
}
