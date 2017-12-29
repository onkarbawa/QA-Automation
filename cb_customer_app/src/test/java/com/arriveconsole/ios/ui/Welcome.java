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
    UIElement stagingVariable = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Curbside ARRIVE']/" +
            "following-sibling::XCUIElementTypeButton");

    @And("^I confirm that current site is selected$")
    public void iConfirmThatCurrentSiteIsSelected() throws Throwable {
        Properties.setVariable("selectedSite", currentSite.getText());
        Assert.assertTrue(currentSite.isDisplayed(), "Current site is not selected");
        MobileDevice.getScreenshot(true);
    }

    @Then("^I confirm that after site switched move back to the same site$")
    public void iConfirmThatAfterSiteSwitchedMoveBackToTheSameSite() throws Throwable {
        Assert.assertEquals(currentSite.getText(), Properties.getVariable("selectedSite"), "Same site " +
                "name is not displayed");
        MobileDevice.getScreenshot(true);
    }

    @And("^I switch application to debug mode$")
    public void iSwitchApplicationToDebugMode() throws Throwable {
        for (int i = 0; i < 4; i++) {
            stagingVariable.tapCenter();
        }
        stagingVariable.longPress(10);
    }

    @And("^I checked selected site is shown on bottom of the screen$")
    public void iCheckSelectedSiteIsShownOnBottomOfTheScreen() throws Throwable {
        Assert.assertTrue(stagingVariable.getText().contains(Properties.getVariable("randomSite")),
                "Selected site is not displayed in bottom of the screen");
    }
}