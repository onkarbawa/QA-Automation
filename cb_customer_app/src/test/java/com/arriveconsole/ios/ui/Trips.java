package com.arriveconsole.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
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

    @Then("^I saw site header name and current open trips (.*) map$")
    public void iSawSiteHeaderNameAndCurrentOpenTrips(String selection) throws Throwable {
        Assert.assertEquals(siteName.getText(), Properties.getVariable("selectedSite"), "Current site " +
                "name is not displayed");
        Assert.assertTrue(openTrips.waitFor(5).isDisplayed(), "Open trips is not displayed");
        if (selection.equalsIgnoreCase("without")) {
            Assert.assertFalse(mapPin.isDisplayed(), "Map view is displayed");
        }
    }
}
