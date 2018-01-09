package com.arriveconsole.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.Helpers;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by hitesh.java on 08/01/18.
 */
public class ArriveTester extends AbstractScreen {

    UIElement siteDropDown = UIElement.byId("android:id/text1");
    UIElement trackingIdentifier = UIElement.byId("com.curbside.pietracker:id/etTrackingIdentifier");
    UIElement btnRegisterTrackingID = UIElement.byId("com.curbside.pietracker:id/bRegisterTrackingId");
    UIElement btnStartTrip = UIElement.byId("com.curbside.pietracker:id/bStartTrip");

    @And("^I generate (\\d+) sample trip(?:s)$")
    public void iStartSampleTrip(int noOfTrips) throws Throwable {
        commonSteps.acceptNotificationAlert();
        iSelectSite(Properties.getVariable("selectedSite"));
        Reporter.addStepLog("Sample trip started on " + Properties.getVariable("selectedSite") + " site");
        for (int i = 0; i < noOfTrips; i++) {
            trackingIdentifier.sendKeys("Android " + Helpers.getRandomFirstName());
            btnRegisterTrackingID.tap();
            btnStartTrip.tap();
        }
    }

    public void iSelectSite(String siteName) throws Throwable {
        if (siteDropDown.waitFor(4).getText().equalsIgnoreCase(siteName))
            return;
        siteDropDown.tap();
        UIElement selectSite = UIElement.byXpath("//android.widget.CheckedTextView[@text='" + siteName + "']");
        selectSite.waitFor(2).tap();
    }

}
