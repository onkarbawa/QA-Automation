package com.cap.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 09/10/17.
 */
public class CommonCap extends AbstractScreenCap {

    UIElement btnClose = UIElement.byId("com.curbside.nCap:id/tvClose");
    UIElement btnCancelCross = UIElement.byId("com.curbside.nCap:id/imgIssueClose");
    UIElement btnBack = UIElement.byId("com.curbside.nCap:id/imgBack");

    /**
     * Common for Tasks and Pickup screen
     *
     * @param orderIdAlias
     * @param symbol       warning, heart etc
     */
    @And("^I should see '(.*)' order has '(.*)' symbol under (?:Tasks-All|Tasks-Mine|Pickups) tab$")
    public void iVerifySymbols(String orderIdAlias, String symbol) {
        if (symbol.equalsIgnoreCase("hazmat")) {
            UIElement symbolImg = UIElement.byXpath("//android.widget.TextView[contains(@text,'" + Properties.getVariable(orderIdAlias) + "')]" +
                    "/../android.widget.ImageView[@resource-id='com.curbside.nCap:id/imgTaskRestrictions']");
            Assert.assertTrue(symbolImg.isDisplayed(), symbol + " image is not present");
        } else
            Assert.fail("Please enter correct symbol name");
    }

    @And("^I am at CAP home screen$")
    public void iAmAtHome() throws Throwable {
        for (int i = 0; i < 5; i++) {
            if (footerTabsCap.btnTasks.waitFor(1).isDisplayed())
                return;
            btnClose.tapOptional();
            btnBack.tapOptional();
            btnCancelCross.tapOptional();
            Steps.tapButton_optional("Close");
        }
        MobileDevice.getScreenshot(true);
    }
}
