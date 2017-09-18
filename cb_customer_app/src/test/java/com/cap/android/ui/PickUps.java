package com.cap.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.android.ui.CreditCard;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 05/09/17.
 */
public class PickUps {

    UIElement searchField = UIElement.byId("com.curbside.nCap:id/search_src_text");
    UIElement lblOrderId;
    UIElement lblOrderCaption;

    /**
     * Getting the value of firstName and LastName from Credit card information
     * {@link CreditCard#iAddedCreditCardInformations()}
     */
    @And("^I search by customer name to sort the orders$")
    public void iSearchCustomer() throws Throwable {

        Properties.setVariable("firstNameCredit","John");
        Properties.setVariable("lastNameCredit","Miller");
        String fullName = Properties.getVariable("firstNameCredit") + " " + Properties.getVariable("lastNameCredit");
        searchField.waitFor(2).sendKeys(fullName);
        AndroidDevice.pressEnter();
    }

    @And("^I search for '(.*)' order id under Pickups tab$")
    public void iSearchPickupsOrder(String orderIdAlias) throws Throwable {
        lblOrderId = UIElement.byXpath("//android.widget.TextView[contains(@text,'"+Properties.getVariable(orderIdAlias)+"')]");
        lblOrderId.waitFor(2).swipeUpSlow();

        Assert.assertTrue(lblOrderId.isDisplayed(),orderIdAlias +" order is not present");
    }

    @And("^I validate '(.*)' order marked as '(.*)'$")
    public void iValidateOrderCaption(String orderIdAlias, String expectedOrderCaption) throws Throwable {
        Reporter.addStepLog("OrderID in Curbside : " + Properties.getVariable(orderIdAlias));

        lblOrderId= UIElement.byXpath("//android.widget.TextView[contains(@text,'"+Properties.getVariable(orderIdAlias)+"')]");
        lblOrderId.swipeUpSlow();

        Assert.assertTrue(lblOrderId.isDisplayed(), "Order is not present under PickUps tab");

        lblOrderCaption = UIElement.byXpath("//android.widget.TextView[contains(@text,'"+Properties.getVariable(orderIdAlias)+"')]" +
                "/../android.widget.TextView[@index ='4']");
        lblOrderCaption.swipeUpSlow();
        Assert.assertTrue(lblOrderCaption.isDisplayed(), "Order status is not present");

        Assert.assertEquals(lblOrderCaption.getText(), expectedOrderCaption, orderIdAlias +" order status is not same as "+expectedOrderCaption );
    }
}
