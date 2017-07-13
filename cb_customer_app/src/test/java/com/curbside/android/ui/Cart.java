package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

/**
 * @author hitesh.grover
 *
 */

public class Cart extends AbstractScreen {

    UIElement firstRetailer = UIElement.byXpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[@index=‘0’]");
    UIElement loyaltyCardNumber = UIElement.byId("com.curbside.nCurbside:id/text_loyalty_last_4_view");
    UIElement productName = UIElement.byId("com.curbside.nCurbside:id/product_name");



    @And("^I should see loyalty card info on cart screen$")
    public void iShouldSeeLoyaltyCardInfoOnCartScreen() throws Throwable {

        try {
            firstRetailer.waitFor(10).tap();
        }catch (Exception e){

        }
        String displayedCardInfo = loyaltyCardNumber.getText();
        String last4Chars = StringUtils.right(Properties.getVariable("extraCareCardNumber"), 4);
        Assert.assertEquals(displayedCardInfo, "••••" + last4Chars + "");
    }

    @Then("^I saw added product in cart$")
    public void iSawAddedProductInCart() throws Throwable {
        footerTabsScreen.tapCart();
        Assert.assertEquals(productName.getText(), Properties.getVariable("productName"),"Added product not shown in the cart");
    }
}
