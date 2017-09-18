package com.cap.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import gherkin.lexer.Th;
import org.testng.Assert;

/**
 * Created by bawa.onkar
 */
public class MoreTab extends AbstractScreen {

    UIElement btnCancelledPickUp = UIElement.byName("Cancelled Pickups");
    UIElement alertMessage = UIElement.byName("Pickup is Cancelled");

    @Then("^I should see '(.*)' orderId in Cancelled pickups with '(.*)'$")
    public void iShouldSeeParticularOrderInCancelledPickups(String orderAlias,String message) throws Throwable {
       footerTabsScreen.tapMore();
       btnCancelledPickUp.tap();
       String orderID = Properties.getVariable(orderAlias);
       UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]").scrollTo().tap();
       Assert.assertEquals(alertMessage.waitFor(10).getText(),message,"PickUp is not Cancelled");
    }
}
