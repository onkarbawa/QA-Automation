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
public class Welcome extends AbstractScreen {
    UIElement currentSite = UIElement.byXpath("//XCUIElementTypeImage[@name='arriveLogo']/following-sibling::" +
            "XCUIElementTypeOther/XCUIElementTypeStaticText[1]");

    @And("^I confirm that current site is selected$")
    public void iConfirmThatCurrentSiteIsSelected() throws Throwable {
        Properties.setVariable("selectedSite", currentSite.getText());
        Assert.assertTrue(currentSite.isDisplayed(), "Current site is not selected");
        MobileDevice.getScreenshot(true);
    }

    @Then("^I confirm that after site switched move back to the same site$")
    public void iConfirmThatAfterSiteSwitchedMoveBackToTheSameSite() throws Throwable {
        Assert.assertEquals(currentSite.getText(),Properties.getVariable("selectedSite"), "Same site " +
                "name is not displayed");
        MobileDevice.getScreenshot(true);
    }
}
