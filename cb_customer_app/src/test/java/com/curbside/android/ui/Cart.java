package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;

/**
 * @author hitesh.grover
 *
 */

public class Cart extends AbstractScreen {
    private final Logger logger = Logger.getLogger(Cart.class);

    UIElement firstRetailer = UIElement.byXpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[@index=‘0’]");
    UIElement loyaltyCardNumber = UIElement.byId("com.curbside.nCurbside:id/text_loyalty_last_4_view");
    UIElement productName = UIElement.byId("com.curbside.nCurbside:id/product_name");
    UIElement firstRetailerIcon = UIElement.byId("com.curbside.nCurbside:id/image_store_icon");
//    UIElement firstStoreTotalPrice = UIElement.byXpath("//android.widget.LinearLayout[@index=‘1’]//*[@resource-id=‘com.curbside.nCurbside:id/price_view’]");
    UIElement btnFirstItemQuantity = UIElement.byId("com.curbside.nCurbside:id/btn_cart_item_qty");
    UIElement btnRemove = UIElement.byId("com.curbside.nCurbside:id/button_cart_product_remove");
    UIElement btnContinueShopping = UIElement.byId("com.curbside.nCurbside:id/button_continue_shopping");
    UIElement textItemsAndStores = UIElement.byId("com.curbside.nCurbside:id/text_item_count");
    UIElement estimatedPickUpTime = UIElement.byId("com.curbside.nCurbside:id/text_estimated_pickup_time");
    UIElement loyaltyCardName = UIElement.byId("com.curbside.nCurbside:id/text_loyalty_name");
    UIElement paymentCardName = UIElement.byId("com.curbside.nCurbside:id/text_card_name");



    @And("^I should see loyalty card info on cart screen$")
    public void iShouldSeeLoyaltyCardInfoOnCartScreen() throws Throwable {

        try {
            firstRetailer.waitFor(10).tap();
        }catch (Exception e){
            logger.debug(e.getMessage());
        }
        String displayedCardInfo = loyaltyCardNumber.getText();
        String last4Chars = StringUtils.right(Properties.getVariable("extraCareCardNumber"), 4);
        Assert.assertEquals(displayedCardInfo, "••••" + last4Chars + "", "Loyalty card info not match/displayed");
    }

    @Then("^I saw added product in cart$")
    public void iSawAddedProductInCart() throws Throwable {
        footerTabsScreen.tapCart();
        Assert.assertEquals(productName.waitFor(3).getText(), Properties.getVariable("productName"),
          "Added product not shown in the cart");
    }

    @Given("^My cart is empty$")
    public void myCartIsEmpty() throws Throwable {
        footerTabsScreen.tapCart();
        firstRetailerIcon.waitFor(20).isDisplayed();
        footerTabsScreen.tapCart();
        if (!btnContinueShopping.isDisplayed()){
            while(!btnContinueShopping.isDisplayed()){
                try{
                    firstRetailerIcon.tap();
                }catch (Exception e){}

                if(!paymentCardName.waitFor(10).isDisplayed() && firstRetailerIcon.waitFor(10).isDisplayed()){
                    System.out.print("--------payment card is not displayes and first store Total price is displayed -------------");
                    firstRetailerIcon.tap();
                }

                if(loyaltyCardName.isDisplayed() && paymentCardName.isDisplayed() && ! btnFirstItemQuantity.isDisplayed()){
                    btnFirstItemQuantity.swipeUpSlow();
                }
                btnFirstItemQuantity.tap();
                btnRemove.waitFor(5).tap();
                footerTabsScreen.tapCart();

            }
        }

    }
}
