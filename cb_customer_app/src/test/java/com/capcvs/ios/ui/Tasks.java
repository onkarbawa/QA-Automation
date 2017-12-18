package com.capcvs.ios.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class Tasks {

    UIElement pickUpID = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Pickup ID :']/following-sibling::" +
            "XCUIElementTypeStaticText[2]");

    UIElement handlingInfo = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Pickup ID :']/following-sibling::" +
            "XCUIElementTypeStaticText[3]");

    @Then("^I should see '(.*)' pickUpID and Handling Instructions$")
    public void iShouldSeePickUpIDAndHandlingInstructions(String orderIdAlias) throws Throwable {
        if (Properties.getVariable(orderIdAlias) == null)
            Assert.fail("Not able to place the order from Curbside app");

        Reporter.addStepLog("OrderID in Curbside : " + Properties.getVariable(orderIdAlias));
        Assert.assertEquals(pickUpID.waitFor(5).getText(),orderIdAlias,"Correct OrderID is not displayed under " +
                "product Info");
        Assert.assertTrue(handlingInfo.isDisplayed(),"Handling Information is not displayed under product Info");
    }
}
