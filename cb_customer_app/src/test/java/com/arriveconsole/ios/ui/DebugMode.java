package com.arriveconsole.ios.ui;


import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.testng.Assert;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by bawa.onkar.
 */
public class DebugMode extends AbstractScreen {

    UIElement totalSites = UIElement.byXpath("//XCUIElementTypeMap/following-sibling::" +
            "XCUIElementTypeOther//XCUIElementTypeOther");
    UIElement currentSelectedSite = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Selected Site :']/" +
            "following-sibling::XCUIElementTypeStaticText");

    @And("^I selected random site from map$")
    public void iSelectedRandomSiteFromMap() throws Throwable {
        int randomNumber = ThreadLocalRandom.current().nextInt(1, totalSites.getCount());
        UIElement randomSite = UIElement.byXpath("//XCUIElementTypeMap/following-sibling::" +
                "XCUIElementTypeOther//XCUIElementTypeOther[" + randomNumber + "]");
        Properties.setVariable("randomSite", randomSite.getText());
        randomSite.tap();
    }

    @And("^I confirmed selected site$")
    public void iConfirmedSelectedSite() throws Throwable {
        Assert.assertEquals(Properties.getVariable("randomSite"), currentSelectedSite.getText(),
                "Selected site not matched");
    }
}
