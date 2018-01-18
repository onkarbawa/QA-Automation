package com.arriveconsole.android.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar.
 */
public class TripDetails extends AbstractScreen {
    UIElement lblDistance = UIElement.byId("com.curbside.arriveconsole:id/tvDistance");
    UIElement lblETA = UIElement.byId("com.curbside.arriveconsole:id/tvETA");
    UIElement btnCompleteTrip = UIElement.byId("com.curbside.arriveconsole:id/bCompleteAll");
    UIElement txtCompleteTrip = UIElement.byUISelector("new UiSelector().text(\"COMPLETE ALL\")");
    UIElement btnCancelTrip = UIElement.byId("com.curbside.arriveconsole:id/bCancelAll");
    UIElement txtCancelTrip = UIElement.byUISelector("new UiSelector().text(\"CANCEL ALL\")");
    UIElement alertPopUp = UIElement.byId("android:id/message");
    UIElement btnBack = UIElement.byXpath("//android.view.ViewGroup[android.widget.ImageView" +
            "[@resource-id='com.curbside.arriveconsole:id/imgMap']]/android.widget.ImageButton");

    @Then("^I saw open trip details$")
    public void iSawOpenTripDetails() throws Throwable {
        String eta = lblETA.waitFor(5).getText();
        String distance = lblDistance.waitFor(5).getText();
        Assert.assertTrue(distance.matches("^[0-9].*$"), "Distance is not visible");
        Assert.assertTrue(eta.contains("AM") || eta.contains("PM"), "ETA is not visible");
        Assert.assertTrue(btnCompleteTrip.isDisplayed(), "Complete trip button is not visible");
        Assert.assertTrue(txtCompleteTrip.isDisplayed(), "Label of Complete trip is not visible");
        Assert.assertTrue(btnCancelTrip.isDisplayed(), "Cancel trip is not visible");
        Assert.assertTrue(txtCancelTrip.isDisplayed(), "Label of Cancel trip is not visible");
        MobileDevice.getScreenshot(true);
        btnBack.tap();
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
