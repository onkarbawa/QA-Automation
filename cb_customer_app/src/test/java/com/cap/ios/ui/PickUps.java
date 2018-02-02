package com.cap.ios.ui;


import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar
 */
public class PickUps extends AbstractScreen{

//    UIElement alertMessage = UIElement.byName("Needs customer attention");
    UIElement alertMessage = UIElement.byXpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText");
    UIElement pickUpQty = UIElement.byXpath("//XCUIElementTypeStaticText[@name='QTY :']/following-sibling::XCUIElementTypeStaticText[1]");
    UIElement transferCompleteAlert = UIElement.byName("Transfer complete.");
    UIElement searchByCustomerName = UIElement.byName("Search by customer name");

    @Then("^I should see '(.*)' orderId in PickUp tab with message '(.*)'$")
    public void iShouldSeeOrderIdInPickUpTabWith(String orderIdAlias, String message) throws Throwable {
        footerTabsScreen.btnPickUp.waitFor(15).tap();
        iSearchCustomerNameToSortOrder();
        if (Properties.getVariable(orderIdAlias) == null)
            Assert.fail("Not able to place the order from Curbside app");
        String orderID = Properties.getVariable(orderIdAlias);
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + orderID + "')]").scrollTo().tap();
        MobileDevice.getScreenshot(true);
        Assert.assertEquals(alertMessage.getText(), message, "Attention message is not shown");
        MobileDevice.getScreenshot(true);
    }

    @Then("^I should see total Order quantity '(.*)' and updated order quantity '(.*)'$")
    public void iShouldSeeTotalOrderQuantityAndUpdatedOrderQuantity(String totalQuantity, String updatedQuantity) throws Throwable {
       String totalQty = pickUpQty.getText().split("\\s")[1];
       String updatedQty = pickUpQty.getText().split("\\s")[3];
       Assert.assertEquals(totalQty,totalQuantity,"Total quantity does not match with orignal total quantity");
       Assert.assertEquals(updatedQty,updatedQuantity,"Quantity does not match with updated quantity");
       pickUpQty.waitForNot(10);
    }

    @Then("^I should see alert '(.*)'$")
    public void iShouldSeeMessage(String message) throws Throwable {
        Assert.assertEquals(transferCompleteAlert.getText(),message,"Transfer Complete message is not pop-up");
        MobileDevice.acceptAlert();
        //Steps.tapButton("OK");
    }

    public void scrollToElement(UIElement orderNumber) throws Throwable {
        int totalTasks = UIElement.byXpath("//XCUIElementTypeTable/XCUIElementTypeCell").getCount();
        UIElement lastTask = UIElement.byXpath("//XCUIElementTypeTable/XCUIElementTypeCell[" + String.valueOf(totalTasks - 1) + "]");

        for (int i = 1; i < totalTasks; i++) {
            if (lastTask.isDisplayed() || orderNumber.isDisplayed()) {
                break;
            } else {
                MobileDevice.swipe(180, 550, 180, 50);
            }
        }
    }

    @Then("^I confirm '(.*)' orderID is not present under (.*) tab$")
    public void iConfirmOrderIDIsNotPresent(String orderIdAlias, String footerTabName) throws Throwable {
        if (Properties.getVariable(orderIdAlias) == null)
            Assert.fail("Not able to place the order from Curbside app");

        Reporter.addStepLog("OrderID in Curbside : " + Properties.getVariable(orderIdAlias));

        String orderID = Properties.getVariable(orderIdAlias);
        UIElement orderNumber = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + orderID + "')]");
        for (int i = 1; i < 7; i++) {
            if (orderNumber.isDisplayed()) {
                break;
            } else {
                MobileDevice.swipe(180, 550, 180, 50);
            }
        }
        Assert.assertFalse(orderNumber.isDisplayed(), "Order is in the" + footerTabName + "list");
    }

    @And("^I search by customer name to sort the orders$")
    public void iSearchCustomerNameToSortOrder() throws Throwable {
        String fullName = Properties.getVariable("fNCredit") + " " + Properties.getVariable("lNCredit");
        Reporter.addStepLog("Customer name : " + fullName);
        searchByCustomerName.waitFor(15).sendKeys(fullName,false);
        Steps.tapButton("Search");

    }

    @Then("^I tap on '(.*)' icon and search for '(.*)' OrderID and verify that '(.*)' is present$")
    public void iTapOnIconAndSearchForOrderIDAndVerifyThatHazmatSymbolIsPresent(String tabIcon, String orderAlias, String symbol) throws Throwable {
        footerTabsScreen.iTapOnIconInBottomMenuForCap(tabIcon);
        String orderID = Properties.getVariable(orderAlias);
        UIElement iDSymbol = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+orderID+"')]" +
                "/following-sibling::XCUIElementTypeStaticText[1]");
        Assert.assertEquals(iDSymbol.waitFor(3).getText(),"","");

        iDSymbol.tap();
    }
}
