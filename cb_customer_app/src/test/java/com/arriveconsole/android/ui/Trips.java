package com.arriveconsole.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
    UIElement alertMessage = UIElement.byId("android:id/message");


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
        openTripsCount = openTrips.waitFor(10).getCount();
        if (openTripsCount >= 2) {
            return;
        } else if (openTripsCount < 1) {
            commonSteps.launchApplicationClean("ARRIVE Console Tester", "first");
            arriveTester.iStartSampleTrip(2);
        } else if (openTripsCount < 2) {
            commonSteps.launchApplicationClean("ARRIVE Console Tester", "first");
            arriveTester.iStartSampleTrip(1);
        }
    }

    @When("^I tap on home button$")
    public void iTapOnHomeButton() throws Throwable {
        btnHome.tap();
    }

    @Then("^I saw alert message$")
    public void iSawAlertMessage() throws Throwable {
        Assert.assertEquals(alertMessage.waitFor(4).getText(), "Tracking for the current site will stop, and the app will " +
                "no longer receive updates until you select another site.", "Alert message is not displayed");
        MobileDevice.getScreenshot(true);
    }

    @And("^I tap on change site button$")
    public void iTapOnChangeSiteButton() throws Throwable {
        btnChangeSite.waitFor(2).tap();
    }

    @And("^I select site which have open trips$")
    public void iSelectSiteWhichHaveOpenTrips() throws Throwable {
        welcomeScreen.lblCurrentSite.waitFor(50);
        Steps.tapButton("VIEW TRIPS");
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
                Steps.tapButton("CHOOSE A DIFFERENT SITE");
                siteSelectionScreen.iSelectADifferentSiteFromList();
                if (i > 4){
                    break;
                }
            }
            i++;
        }
    }

    @When("^I tap on open trip$")
    public void iTapOnOpenTrip() throws Throwable {
        Properties.setVariable("firstOpenTrip", openTrips.getText());
        openTrips.tap();
    }

    @Then("^I saw cancelled trip removed from the list$")
    public void iSawCancelledTripRemovedFromTheList() throws Throwable {
        siteName.waitFor(10);
        UIElement.byXpath("//android.widget.TextView[@text='" +
                Properties.getVariable("firstOpenTrip") + "']").waitForNot(8);
        Assert.assertFalse(UIElement.byXpath("//android.widget.TextView[@text='" +
                Properties.getVariable("firstOpenTrip") + "']").waitFor(2).isDisplayed(), "Cancelled Trip is not " +
                "removed from the list");
    }
}
