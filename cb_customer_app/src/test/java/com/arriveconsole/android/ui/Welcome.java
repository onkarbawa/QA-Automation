package com.arriveconsole.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 08/01/18.
 */
public class Welcome extends AbstractScreen {

    UIElement lblCurrentSite = UIElement.byId("com.curbside.arriveconsole:id/tvSiteId");

    @And("^I confirm that current site is selected$")
    public void iConfirmThatCurrentSiteIsSelected() throws Throwable {
        Assert.assertTrue(lblCurrentSite.waitFor(10).isDisplayed(), "There is no default site that is present for this location");
        MobileDevice.getScreenshot(true);
    }

    @Then("^I confirm that after site switched move back to the same site$")
    public void iConfirmThatAfterSiteSwitchedMoveBackToTheSameSite() throws Throwable {
        Assert.assertEquals(lblCurrentSite.waitFor(10).getText(), Properties.getVariable("selectedSite"),
                "Same site name is not displayed");
        MobileDevice.getScreenshot(true);
    }

    public void setDefaultSite() throws Throwable {
        Properties.setVariable("selectedSite", lblCurrentSite.waitFor(10).getText());
        Reporter.addStepLog("Trips will be generated for " + Properties.getVariable("selectedSite") + " site");
    }

    @And("^I am on (.*) site$")
    public void iAmAtSite(String siteName) throws Throwable {
        commonSteps.acceptNotificationAlert();
        if (tripsScreen.siteName.waitFor(10).isDisplayed()) {
            if (tripsScreen.siteName.getText().equalsIgnoreCase(siteName))
                return;
        }
        if (!(lblCurrentSite.isDisplayed()) && tripsScreen.btnHome.isDisplayed()) {
            tripsScreen.iAmOnArriveConsoleHomeScreen();
        }
        Steps.waitForButton("CHOOSE A DIFFERENT SITE");
        Steps.tapButton("CHOOSE A DIFFERENT SITE");
        Steps.tapButton(siteName);
        MobileDevice.getScreenshot(true);
        Assert.assertEquals(tripsScreen.siteName.waitFor(10).getText(), siteName, "Not able to switch site");
    }
}
