package com.cap.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar
 */
public class PickUps extends AbstractScreen{

    UIElement attentionMessage = UIElement.byName("Needs customer attention");

    @Then("^I should see particular order in Task tab with '(.*)'$")
    public void iShouldSeeParticularOrderInTaskTabWith(String message) throws Throwable {
        footerTabsScreen.tapPickUp();
        String orderID = Properties.getVariable("orderID");
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]").scrollTo().tap();
        Assert.assertEquals(attentionMessage.getText(),message,"Attention message is not shown");
    }
}
