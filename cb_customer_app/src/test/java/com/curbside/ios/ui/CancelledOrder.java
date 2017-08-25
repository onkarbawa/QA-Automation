package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.testng.Assert;


/**
 * Created by bawa.onkar
 */
public class CancelledOrder extends AbstractScreen {

    UIElement cancelOrderTitle = UIElement.byName("Cancelled Order");
    UIElement orderID = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Order ID']/following-sibling::XCUIElementTypeStaticText[6]");

    @Then("^I should see cancel order information under Cancelled Order screen$")
    public void iShouldSeeInformationUnderCancelledOrderScreen() throws Throwable {
        MobileDevice.getSource(true);
        Assert.assertEquals(cancelOrderTitle.getText(),"Cancelled Order","Cancel Order is not in the Cancelled Order Screen");
        Assert.assertEquals(orderID.getText(), Properties.getVariable("orderID"),"Cancel OrderID is not same");
        MobileDevice.getSource(true);
        try {
            Steps.tapButton("Done");
        }catch (Exception e){}
    }
}
