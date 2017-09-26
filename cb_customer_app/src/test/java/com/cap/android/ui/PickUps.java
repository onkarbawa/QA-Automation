package com.cap.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.android.ui.CreditCard;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 05/09/17.
 */
public class PickUps extends AbstractScreenCap {

    UIElement searchField = UIElement.byId("com.curbside.nCap:id/search_src_text");
    UIElement lblItemsToPick = UIElement.byXpath("//android.widget.TextView[contains(@text,'to Pick ')]");
    UIElement lblOrderStatus = UIElement.byId("com.curbside.nCap:id/tvPickupStatus");
    UIElement lblOrderId;
    UIElement lblOrderCaption;

    /**
     * Getting the value of firstName and LastName from Credit card information
     * {@link CreditCard#iAddedCreditCardInformations()}
     */
    @And("^I search by customer name to sort the orders$")
    public void iSearchCustomer() throws Throwable {
        String fullName = Properties.getVariable("firstNameCredit") + " " + Properties.getVariable("lastNameCredit");
        searchField.waitFor(3).sendKeys(fullName);
        AndroidDevice.pressEnter();
    }

    @And("^I search for '(.*)' order id under (?:Pickups|Cancelled Pickups) tab and '(.*)' it$")
    public void iSearchPickupsOrder(String orderIdAlias, String actionPerformed) throws Throwable {
        lblOrderId = UIElement.byXpath("//android.widget.TextView[contains(@text,'" + Properties.getVariable(orderIdAlias) + "')]");
        lblOrderId.waitFor(2).swipeUpSlow();

        Assert.assertTrue(lblOrderId.isDisplayed(), orderIdAlias + " order is not present");

        if (actionPerformed.equalsIgnoreCase("tap"))
            lblOrderId.tap();
        else if (!(actionPerformed.equalsIgnoreCase("confirm")))
            Assert.fail("Code cannot perform this action");
    }

    @And("^I validate '(.*)' order marked as '(.*)'$")
    public void iValidateOrderCaption(String orderIdAlias, String expectedOrderCaption) throws Throwable {
        Reporter.addStepLog("OrderID in Curbside : " + Properties.getVariable(orderIdAlias));

        lblOrderId = UIElement.byXpath("//android.widget.TextView[contains(@text,'" + Properties.getVariable(orderIdAlias) + "')]");
        lblOrderId.swipeUpSlow();

        Assert.assertTrue(lblOrderId.isDisplayed(), "Order is not present under PickUps tab");

        lblOrderCaption = UIElement.byXpath("//android.widget.TextView[contains(@text,'" + Properties.getVariable(orderIdAlias) + "')]" +
                "/../android.widget.TextView[@index ='4']");
        lblOrderCaption.swipeUpSlow();
        Assert.assertTrue(lblOrderCaption.isDisplayed(), "Order status is not present");

        Assert.assertEquals(lblOrderCaption.getText(), expectedOrderCaption, orderIdAlias + " order status is not same as " + expectedOrderCaption);
    }

    @And("^I tap on (\\d+)(?:st|nd|rd|th) item Issue button$")
    public void iTapNthIssueButton(int nthItemIssueButton) throws Throwable {
        UIElement btnIssueNth = UIElement.byXpath("//android.widget.RelativeLayout[@index='" + (nthItemIssueButton - 1) + "']/android.widget.Button[@text='Issue']");
        btnIssueNth.swipeUpSlow();
        btnIssueNth.tap();
    }

    @And("^I mark '(.*)' (?:item|items) not available$")
    public void iMarkItemsOutOfStock(String selectedOption) throws Throwable {

        String totalItems = lblItemsToPick.getText();
        int totalNoOfItems = Integer.parseInt(totalItems.substring(totalItems.indexOf("(") + 1, totalItems.lastIndexOf(")")));
        Reporter.addStepLog("Pick the "+totalItems+" items below");

        if (selectedOption.equalsIgnoreCase("all")) {
            for (int i = 1; i <= totalNoOfItems; i++) {
                iTapNthIssueButton(i);
                issue.iTapItemNotAvail();
                Steps.tapButton("Done");
            }
        } else if (Character.isDigit(selectedOption.charAt(0))) {
            int noOfItems = Integer.parseInt(selectedOption);

            if (!(noOfItems <= totalNoOfItems))
                Assert.fail("Total items available in the order is  "+totalItems+" and your are using step for "+noOfItems+" items");

            for (int i = 1; i <= noOfItems; i++) {
                iTapNthIssueButton(i);
                issue.iTapItemNotAvail();
                Steps.tapButton("Done");
            }
        }

    }

    @And("^I tap on (\\d+)(?:st|nd|rd|th) item Got It button$")
    public void iTapNthGotItButton(int nthGotItButton) throws Throwable {
        UIElement btnGotItNth = UIElement.byXpath("//android.widget.RelativeLayout[@index='" + (nthGotItButton - 1) + "']/android.widget.Button[@text='Got It']");
        btnGotItNth.swipeUpSlow();
        btnGotItNth.tap();
        try {
            btnGotItNth.tap();
        } catch (Exception e) {
        }
        MobileDevice.getScreenshot(true);
    }

    @And("^The order status should be '(.*)'$")
    public void iConfirmStatus(String expectedOrderStatus) throws Throwable {
        lblOrderStatus.waitFor(2);
        Assert.assertEquals(lblOrderStatus.getText(), expectedOrderStatus, "The order status is not same");
    }
}
