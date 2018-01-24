package com.arriveconsole.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
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
    UIElement firstOpenTrip = UIElement.byXpath("//XCUIElementTypeCell[1]/XCUIElementTypeStaticText[5]");

    @Then("^I saw site header name and current open trips (.*) map$")
    public void iSawSiteHeaderNameAndCurrentOpenTrips(String selection) throws Throwable {
        Steps.tapButton_optional("OK");
        Assert.assertEquals(siteName.getText(), Properties.getVariable("selectedSite"), "Current site " +
                "name is not displayed");
        Assert.assertTrue(openTrips.waitFor(5).isDisplayed(), "Open trips are not displayed");
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
        try {
            btnHome.tap();
            btnChangeSite.tap();
            MobileDevice.getScreenshot(true);
        } catch (Exception e) {

        }
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
        int i = 0;
        while (true) {
            if (openTrips.getCount() >= 1) {
                break;
            } else {
                iAmOnArriveConsoleHomeScreen();
                welcomeScreen.iConfirmThatCurrentSiteIsSelected();
                Steps.tapButton("Choose a Different Site");
                siteSelection.iSelectADifferentSiteFromList();
                Steps.tapButton("View Trips");
                if (i > 4) {
                    break;
                }
            }
            i++;
        }
    }

    @When("^I tap on open trip$")
    public void iTapOnOpenTrip() throws Throwable {
        Steps.tapButton_optional("OK");
        if (UIElement.byName("IN TRANSIT").isDisplayed()) {
            Properties.setVariable("firstOpenTrip", UIElement.byXpath("//XCUIElementTypeCell[1]" +
                    "/XCUIElementTypeStaticText[6]").getText());
        } else {
            Properties.setVariable("firstOpenTrip", UIElement.byXpath("//XCUIElementTypeCell[1]" +
                    "/XCUIElementTypeStaticText[5]").getText());
        }
        firstOpenTrip.tap();
    }

    @Then("^I saw cancelled trip removed from the list$")
    public void iSawCancelledTripRemovedFromTheList() throws Throwable {
        siteName.waitFor(8);
        Assert.assertFalse(UIElement.byXpath("//XCUIElementTypeCell[1]/XCUIElementTypeStaticText[@name='" +
                Properties.getVariable("firstOpenTrip") + "']").isDisplayed(), "Cancelled Trip is not " +
                "removed from the list");
    }
}
