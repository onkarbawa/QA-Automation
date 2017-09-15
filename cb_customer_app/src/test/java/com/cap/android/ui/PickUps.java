package com.cap.android.ui;

import com.curbside.android.ui.CreditCard;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 05/09/17.
 */
public class PickUps {

    UIElement searchField = UIElement.byId("com.curbside.nCap:id/search_src_text");

    /**
     * Getting the value of firstName and LastName from Credit card information
     * {@link CreditCard#iAddedCreditCardInformations()}
     */
    @And("^I search by customer name to sort the orders$")
    public void iSearchCustomer() throws Throwable {

        String fullName = Properties.getVariable("firstNameCredit") + " " + Properties.getVariable("lastNameCredit");
        System.out.println("fullName--"+ fullName);
        searchField.waitFor(2).sendKeys(fullName);
        AndroidDevice.pressEnter();
    }

    @And("^I search for '(.*)' order id under Pickups tab$")
    public void iSearchPickupsOrder(String orderIdAlias) throws Throwable {
        UIElement lblOrderId = UIElement.byXpath("//android.widget.TextView[contains(@text,'"+Properties.getVariable(orderIdAlias)+"')]");
        lblOrderId.waitFor(2).swipeUpSlow();

        Assert.assertTrue(lblOrderId.isDisplayed(),orderIdAlias +" order is not present");
    }

    @And("^I validate '(.*)' order marked as '(.*)'$")
    public void iValidateOrderCaption(String orderIdAlias, String orderCaption) throws Throwable {
        UIElement lblOrderCaption = UIElement.byXpath("//android.widget.LinearLayout[android.widget.TextView[contains(@text,'"+Properties.getVariable(orderIdAlias)+"')]" +
                "/following-sibling::android.widget.TextView[contains(@text,'"+Properties.getVariable(orderCaption)+"')]");
        lblOrderCaption.waitFor(2).swipeUpSlow();

        Assert.assertTrue(lblOrderCaption.isDisplayed(),orderIdAlias +" order is not present with caption "+orderCaption );
    }
}
