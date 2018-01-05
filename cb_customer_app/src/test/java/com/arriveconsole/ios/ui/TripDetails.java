package com.arriveconsole.ios.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
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

    @Then("^I saw open trip details$")
    public void iSawOpenTripDetails() throws Throwable {
        Assert.assertTrue(lblDistance.waitFor(10).isDisplayed() && lblETA.isDisplayed() &&
                btnCompleteTrip.isDisplayed() && txtCompleteTrip.isDisplayed() && btnCancelTrip.isDisplayed() &&
                txtCancelTrip.isDisplayed(),"All Trip details are not shown on screen");
        Steps.tapButton("Trips");
    }
}
