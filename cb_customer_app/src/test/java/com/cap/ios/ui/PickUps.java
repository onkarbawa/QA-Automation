package com.cap.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar
 */
public class PickUps extends AbstractScreen{

    UIElement alertMessage = UIElement.byName("Needs customer attention");

    @Then("^I should see '(.*)' orderId in Task tab with '(.*)'$")
    public void iShouldSeeOrderIdInTaskTabWith(String orderAlias,String message) throws Throwable {
        footerTabsScreen.tapPickUp();
        String orderID = Properties.getVariable(orderAlias);
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]").scrollTo().tap();
        Assert.assertEquals(alertMessage.getText(),message,"Attention message is not shown");
    }
}
