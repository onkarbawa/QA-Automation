package com.cap.ios.ui;


import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
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

    @Then("^I search in pickUp tab and should not see '(.*)' OrderID$")
    public void iSearchInPickUpTabAndShouldNotSeeOrderID(String orderAlias) throws Throwable {
        String orderID = Properties.getVariable(orderAlias);
        UIElement orderNumber = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]");
        try {
            for (int i = 1;i < 10;i++) {
                if (orderNumber.isDisplayed()) {
                    Assert.assertFalse(orderNumber.isDisplayed(),"Order is still in the pickUp list");
                    break;
                }
                else {
                    if (orderNumber.isDisplayed()){
                        Assert.assertFalse(orderNumber.isDisplayed(),"Order is still in the pickUp list");
                        break;
                    }
                    MobileDevice.swipe(180,50,180,550);
                }
            }
        }catch (Exception e){
            Assert.assertFalse(!(orderNumber.isDisplayed()),"Order is not in the pickUp list");
        }

        try {
            for (int i = 1;i < 10;i++) {
                if (orderNumber.isDisplayed()) {
                    Assert.assertFalse(orderNumber.isDisplayed(),"Order is still in the pickUp list");
                    break;
                }
                else {
                    if (orderNumber.isDisplayed()){
                        Assert.assertFalse(orderNumber.isDisplayed(),"Order is still in the pickUp list");
                        break;
                    }
                    MobileDevice.swipe(180,550,180,50);
                }
            }
        }catch (Exception e){
            Assert.assertFalse(!(orderNumber.isDisplayed()),"Order is not in the pickUp list");
        }
    }
}
