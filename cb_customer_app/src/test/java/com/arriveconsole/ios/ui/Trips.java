package com.arriveconsole.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
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
}
