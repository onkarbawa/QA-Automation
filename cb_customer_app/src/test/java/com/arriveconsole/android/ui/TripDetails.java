package com.arriveconsole.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar.
 */
public class TripDetails extends AbstractScreen {
    UIElement lblDistance = UIElement.byId("com.curbside.arriveconsole:id/tvDistanceHeader");
    UIElement lblETA = UIElement.byId("com.curbside.arriveconsole:id/tvETAHeader");
    UIElement btnCompleteTrip = UIElement.byId("com.curbside.arriveconsole:id/bCompleteAll");
    UIElement txtCompleteTrip = UIElement.byUISelector("new UiSelector().text(\"COMPLETE ALL\")");
    UIElement btnCancelTrip = UIElement.byId("com.curbside.arriveconsole:id/bCancelAll");
    UIElement txtCancelTrip = UIElement.byUISelector("new UiSelector().text(\"CANCEL ALL\")");
    UIElement alertPopUp = UIElement.byId("android:id/message");

    @Then("^I saw open trip details$")
    public void iSawOpenTripDetails() throws Throwable {
        Assert.assertTrue(lblDistance.waitFor(10).isDisplayed() && lblETA.isDisplayed() &&
                btnCompleteTrip.isDisplayed() && txtCompleteTrip.isDisplayed() && btnCancelTrip.isDisplayed() &&
                txtCancelTrip.isDisplayed(),"All Trip details are not shown on screen");
        commonSteps.iTapOnBackButton();
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
            Assert.assertEquals(alertPopUp.getText(), "Cancel the trip if it was not finished successfully. " +
                    "It will be removed from the list of open trips.", "Alert message is not displayed");
        }else {
            Assert.assertEquals(alertPopUp.getText(), "Complete the trip if the customer was met successfully." +
                    " The trip will be removed from the list of open trips.", "Alert message is not displayed");
        }
    }
}
