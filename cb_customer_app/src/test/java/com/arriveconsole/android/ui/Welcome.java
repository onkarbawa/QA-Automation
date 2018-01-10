package com.arriveconsole.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 08/01/18.
 */
public class Welcome {

    UIElement lblCurrentSite = UIElement.byId("com.curbside.arriveconsole:id/tvSiteId");

    @And("^I confirm that current site is selected$")
    public void iConfirmThatCurrentSiteIsSelected() throws Throwable {
        Properties.setVariable("selectedSite", lblCurrentSite.waitFor(10).getText());
        Reporter.addStepLog("Default site on Arrive app is " + Properties.getVariable("selectedSite"));
        Assert.assertTrue(lblCurrentSite.isDisplayed(), "Current site is not selected");
        MobileDevice.getScreenshot(true);
    }

    @Then("^I confirm that after site switched move back to the same site$")
    public void iConfirmThatAfterSiteSwitchedMoveBackToTheSameSite() throws Throwable {
        Assert.assertEquals(lblCurrentSite.waitFor(10).getText(), Properties.getVariable("selectedSite"),
                "Same site name is not displayed");
        MobileDevice.getScreenshot(true);
    }
}
