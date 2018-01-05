package com.arriveconsole.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * Created by bawa.onkar.
 */
public class Trips extends AbstractScreen {
    UIElement siteName = UIElement.byXpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText");
    UIElement openTrips = UIElement.byXpath("//XCUIElementTypeTable/XCUIElementTypeCell");
    UIElement mapPin = UIElement.byName("Map pin");
    UIElement btnHome = UIElement.byName("ic home");
    UIElement btnChangeSite = UIElement.byName("Change Site");
    UIElement btnMap = UIElement.byName("mapIcon");
    UIElement alertMessage = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Change Site?']/following-sibling::" +
            "XCUIElementTypeStaticText");

    @Then("^I saw site header name and current open trips (.*) map$")
    public void iSawSiteHeaderNameAndCurrentOpenTrips(String selection) throws Throwable {
        Assert.assertEquals(siteName.getText(), Properties.getVariable("selectedSite"), "Current site " +
                "name is not displayed");
        Assert.assertTrue(openTrips.waitFor(5).isDisplayed(), "Open trips is not displayed");
        if (selection.equalsIgnoreCase("without")) {
            Assert.assertFalse(mapPin.isDisplayed(), "Map view is displayed");
        } else {
            btnMap.tap();
            Assert.assertTrue(mapPin.isDisplayed(), "Map view is not displayed");
        }
        MobileDevice.getScreenshot(true);
    }

    @And("^I am on arriveConsole home screen$")
    public void iAmOnArriveConsoleHomeScreen() throws Throwable {
        btnHome.tap();
        btnChangeSite.tap();
        MobileDevice.getScreenshot(true);
    }

    @Then("^I saw alert message$")
    public void iSawAlertMessage() throws Throwable {
        Assert.assertEquals(alertMessage.getText(), "Tracking for the current site will stop, and the app will " +
                "no longer receive updates until you select another site.", "Alert message is not displayed");
        MobileDevice.getScreenshot(true);
    }

    @And("^I select site which have open trips$")
    public void iSelectSiteWhichHaveOpenTrips() throws Throwable {
        Steps.tapButton("View Trips");
        try {
            openTrips.waitFor(7);
        } catch (Exception e) {
        }
        while (true) {
            if (openTrips.getCount() >= 1) {
                break;
            } else {
                iAmOnArriveConsoleHomeScreen();
                welcomeScreen.iConfirmThatCurrentSiteIsSelected();
                Steps.tapButton("Choose a Different Site");
                siteSelection.iSelectADifferentSiteFromList();
                Steps.tapButton("View Trips");
            }
        }
    }

    @When("^I tap on open trip$")
    public void iTapOnOpenTrip() throws Throwable {
        openTrips.tap();
    }
}
