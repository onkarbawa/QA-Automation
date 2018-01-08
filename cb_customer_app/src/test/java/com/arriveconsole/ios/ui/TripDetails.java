package com.arriveconsole.ios.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar.
 */
public class TripDetails extends AbstractScreen{
    UIElement lblDistance = UIElement.byName("DISTANCE");
    UIElement lblETA = UIElement.byName("ETA");
    UIElement btnCompleteTrip = UIElement.byName("checkWhite");
    UIElement txtCompleteTrip = UIElement.byName("Complete Trip");
    UIElement btnCancelTrip = UIElement.byName("cancelWhite");
    UIElement txtCancelTrip = UIElement.byName("Cancel Trip");
    UIElement alertCancel = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Trip?')]/following-sibling::" +
            "XCUIElementTypeStaticText");

    @Then("^I saw open trip details$")
    public void iSawOpenTripDetails() throws Throwable {
        Assert.assertTrue(lblDistance.waitFor(10).isDisplayed() && lblETA.isDisplayed() &&
                btnCompleteTrip.isDisplayed() && txtCompleteTrip.isDisplayed() && btnCancelTrip.isDisplayed() &&
                txtCancelTrip.isDisplayed(),"All Trip details are not shown on screen");
        Steps.tapButton("Trips");
    }

    @And("^I tap on (.*) trip button$")
    public void iTapOnCancelTripButton(String actionBtn) throws Throwable {
        if (actionBtn.equalsIgnoreCase("cancel")) {
            btnCancelTrip.tap();
        }
        else {
            btnCompleteTrip.tap();
        }
    }

    @Then("^I saw (.*) alert message$")
    public void iSawCancelAlertMessage(String actionMsg) throws Throwable {
        if (actionMsg.equalsIgnoreCase("cancel")) {
            Assert.assertEquals(alertCancel.getText(), "Cancel the trip if it was not finished successfully. It will" +
                    " be removed from the list of open trips.", "Alert message is not displayed");
        }else {

        }
    }
}
