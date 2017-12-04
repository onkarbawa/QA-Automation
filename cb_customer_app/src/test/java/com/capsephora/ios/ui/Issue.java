package com.capsephora.ios.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 04/12/17.
 */
public class Issue {
    UIElement updatePrice = UIElement.byName("Price");

    @And("I update the latest '(.*)' amount of the item as compare to original named as '(.*)'")
    public void priceUpdate(String amountDiff, String storedPriceAlias) throws Throwable {
        Reporter.addStepLog("");
        if (Properties.getVariable(storedPriceAlias) == null)
            Assert.fail("Not able to store the price of the product !!");
        Double price = Double.parseDouble(Properties.getVariable(storedPriceAlias));
        Reporter.addStepLog("Actual Price : "+price);
        if (amountDiff.equalsIgnoreCase("higher")) {
            price = price + 1.00 ;
        } else if (amountDiff.equalsIgnoreCase("lower")) {
            price = price - 0.50 ;
        } else {
            Assert.fail("Please enter correct input !! Amount difference is Higher or Lower ?");
        }
        updatePrice.sendKeys(String.valueOf(price));
    }
}
