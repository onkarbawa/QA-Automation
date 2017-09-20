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

    @And("^I search for '(.*)' order id under (?:Pickups|Cancelled Pickups) tab$")
    public void iSearchPickupsOrder(String orderIdAlias) throws Throwable {
        lblOrderId = UIElement.byXpath("//android.widget.TextView[contains(@text,'" + Properties.getVariable(orderIdAlias) + "')]");
        lblOrderId.waitFor(2).swipeUpSlow();

        Assert.assertTrue(lblOrderId.isDisplayed(), orderIdAlias + " order is not present");
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

    @And("^I tap on (\\d+) item Issue button$")
    public void iTapNthIssueButton(int nthItemIssueButton) throws Throwable {
        UIElement btnIssueNth = UIElement.byXpath("//android.widget.RelativeLayout[@index='" + nthItemIssueButton + "']/android.widget.Button[@text='Issue']");
        btnIssueNth.swipeUpSlow();
        btnIssueNth.tap();
    }

    @And("^I mark '(.*)' (?:item|items) not available$")
    public void iMarkItemsOutOfStock(String selectedOption) throws Throwable {

        String totalItems = lblItemsToPick.getText();
        System.out.println("noOfItem--" + totalItems);
        int totalNoOfItems = Integer.parseInt(totalItems.substring(totalItems.indexOf("(") + 1, totalItems.lastIndexOf(")")));
        Reporter.addStepLog("Pick the "+totalItems+" items below");

        if (selectedOption.equalsIgnoreCase("all")) {
            for (int i = 0; i < totalNoOfItems; i++) {
                iTapNthIssueButton(i);
                issue.iTapItemNotAvail();
                Steps.tapButton("Done");
            }
        } else if (Character.isDigit(selectedOption.charAt(0))) {
            int noOfItems = Integer.parseInt(selectedOption);

            if (!(noOfItems <= totalNoOfItems))
                Assert.fail("Total items available in the order is  "+totalItems+" and your are using step for "+noOfItems+" items");

            for (int i = 0; i < noOfItems; i++) {
                iTapNthIssueButton(i);
                issue.iTapItemNotAvail();
                Steps.tapButton("Done");
            }
        }


    }
}
