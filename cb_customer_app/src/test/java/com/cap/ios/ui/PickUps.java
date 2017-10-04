package com.cap.ios.ui;


import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar
 */
public class PickUps extends AbstractScreen{

    UIElement alertMessage = UIElement.byName("Needs customer attention");
    UIElement pickUpQty = UIElement.byXpath("//XCUIElementTypeStaticText[@name='QTY :']/following-sibling::XCUIElementTypeStaticText[1]");
    UIElement transferCompleteAlert = UIElement.byName("Transfer complete.");

    @Then("^I should see '(.*)' orderId in PickUp tab with '(.*)'$")
    public void iShouldSeeOrderIdInPickUpTabWith(String orderAlias,String message) throws Throwable {
        footerTabsScreen.tapPickUp();
        String orderID = Properties.getVariable(orderAlias);
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]").scrollTo().tap();
//        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'17ADM2MC')]").scrollTo().tap();
        Assert.assertEquals(alertMessage.getText(),message,"Attention message is not shown");
    }

    @Then("^I should see total Order quantity '(.*)' and updated order quantity '(.*)'$")
    public void iShouldSeeTotalOrderQuantityAndUpdatedOrderQuantity(String totalQuantity, String updatedQuantity) throws Throwable {
       String totalQty = pickUpQty.getText().split("\\s")[1];
       String updatedQty = pickUpQty.getText().split("\\s")[3];
       Assert.assertEquals(totalQty,totalQuantity,"Total quantity does not match with orignal total quantity");
       Assert.assertEquals(updatedQty,updatedQuantity,"Quantity does not match with updated quantity");
    }

    @Then("^I should see alert '(.*)'$")
    public void iShouldSeeMessage(String message) throws Throwable {
        Assert.assertEquals(transferCompleteAlert.getText(),message,"Transfer Complete message is not pop-up");
        Steps.tapButton("OK");
    }

    @Then("^I confirm '(.*)' orderID is not present under Pickups tab$")
    public void iConfirmOrderIDIsNotPresent(String orderAlias) throws Throwable {
        String orderID = Properties.getVariable(orderAlias);
        UIElement orderNumber = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]");
        for (int i = 1;i < 7;i++) {
            if (orderNumber.isDisplayed()) {
                break;
            }
            else {
                    MobileDevice.swipe(180,550,180,50);
                }
        }
        Assert.assertFalse(orderNumber.isDisplayed(),"Order is not in the pickUp list");
    }

    @And("^I search by customer name to sort the orders$")
    public void iSearchCustomer() throws Throwable {
        String fullName = "Test" + " " + "Data";
        UIElement.byName("Search by customer name").sendKeys(fullName,false);
        Steps.tapButton("Search");

    }
}
