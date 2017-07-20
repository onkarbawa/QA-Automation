package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.Arrays;

/**
 * @author hitesh.grover
 *
 */

public class Cart extends AbstractScreen {
    private final Logger logger = Logger.getLogger(Cart.class);

    UIElement firstRetailer = UIElement.byXpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[@index=‘0’]");
    UIElement loyaltyCardNumber = UIElement.byId("com.curbside.nCurbside:id/text_loyalty_last_4_view");
    UIElement productName = UIElement.byId("com.curbside.nCurbside:id/product_name");
    UIElement lblCartCount = UIElement.byXpath("//android.widget.TextView[@text='Cart']/../../android.widget.TextView");
    UIElement btnCartItemQnty = UIElement.byId("com.curbside.nCurbside:id/btn_cart_item_qty");
    UIElement btnRemove = UIElement.byId("com.curbside.nCurbside:id/button_cart_product_remove");
    UIElement lblItemCountOnMuilpleStoresScreen = UIElement.byId("com.curbside.nCurbside:id/item_number");
    UIElement lblItemCountSubStore = UIElement.byId("com.curbside.nCurbside:id/item_count_view");

    UIElement firstRetailerIcon = UIElement.byId("com.curbside.nCurbside:id/image_store_icon");
    UIElement btnContinueShopping = UIElement.byId("com.curbside.nCurbside:id/button_continue_shopping");
    UIElement lblItemCountStoreOnTop = UIElement.byId("com.curbside.nCurbside:id/text_item_count");
    UIElement estimatedPickUpTime = UIElement.byId("com.curbside.nCurbside:id/text_estimated_pickup_time");
    UIElement loyaltyCardName = UIElement.byId("com.curbside.nCurbside:id/text_loyalty_name");
    UIElement paymentCardName = UIElement.byId("com.curbside.nCurbside:id/text_card_name");
    UIElement lblDeliveryInfo = UIElement.byId("com.curbside.nCurbside:id/delivery_text");
    UIElement lblItemPriceView = UIElement.byId("com.curbside.nCurbside:id/priceView");
    UIElement btnBack = UIElement.byId("com.curbside.nCurbside:id/button_back");
    UIElement btnPlceOrder2 =UIElement.byXpath("//android.widget.FrameLayout//*[@resource-id='com.curbside.nCurbside:id/button_purchase' and index='1']");
    UIElement btnPlcaeOrder4 = UIElement.byXpath("//android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout/../*[resource-id='com.curbside.nCurbside:id/button_purchase'] and index='1'");
    UIElement lblCartTitle = UIElement.byId("com.curbside.nCurbside:id/cart_title");



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
        productName.swipeUpSlow();
        Assert.assertEquals(productName.waitFor(3).getText(), Properties.getVariable("productName"),
          "Added product not shown in the cart");
    }

//    @Given("^My cart is empty$")
//    public void emptyCart() throws Throwable
//    {
//    	footerTabsScreen.tapCart();
//        int itemsInCart= btnCartItemQnty.getCount();
//        System.out.println(itemsInCart);
//        for (int i = 0; i < itemsInCart; i++) {
//            btnCartItemQnty.tap();
//            btnRemove.waitFor(2).tap();
//            Thread.sleep(1000);
//        }
//  }

    /**
     * There scenario for cart goes like this :
     * 1. Cart -> Only CVS store item in the cart and btnCartItemQnty is there on the screen.
     * 2. Cart -> WestField -> subStore list (Lush , GNC) -> Click on SubStore and then btnCartItemQnty is visible.
     * 3. Cart -> Have both the Store items (CVS and Westfield) ->click on CVS -> then remove
     * 3.1 -> Have both the Store items (CVS and Westfield) ->click on Westfield -> Click subStore -> then remove
     *
     * if we are using getCount function it will only capture the selectors that are visible on screen.
     * for example if we have 7 item in the cart and we used getCount so it may show only 4 item in the cart
     * because other 3 items where not visible on the screen
     * Will remove print statements after @Anil review
     */
    @Given("^My cart is empty$")
    public void myCartIsEmpty2() throws Throwable {
        footerTabsScreen.tapCart();
        firstRetailerIcon.waitFor(10);
        footerTabsScreen.tapCart();
        int i=0;
        while(!btnContinueShopping.isDisplayed() && i < 40){
            String[] cartTitleText = lblCartTitle.waitFor(3).getText().toLowerCase().split("\\s+");
            if(Arrays.asList(cartTitleText).contains("carts")){
                firstRetailerIcon.tap();
            }
            if(Arrays.asList(cartTitleText).contains("cart") && lblItemCountSubStore.waitFor(10).isDisplayed()){
                firstRetailerIcon.tap();
            }
            if((Arrays.asList(cartTitleText).contains("order") || Arrays.asList(cartTitleText).contains("cart"))
                    && paymentCardName.waitFor(5).isDisplayed()){

                int itemsInCart = Integer.parseInt(lblItemCountStoreOnTop.getText().split("\\s+")[0]);
                btnCartItemQnty.waitFor(10);
                for(int j =0 ; j < itemsInCart ; j++) {
                    if(j > itemsInCart-2 && btnContinueShopping.isDisplayed())
                        break;
                    btnCartItemQnty.swipeUpSlow();
                    btnCartItemQnty.tap();
                    btnRemove.waitFor(2).tap();
                    Thread.sleep(2000);
                }
            }
        }
    }

    @Then("^I should see the (\\d+) items in the cart$")
    public void iShouldSeeTheItemsInTheCart(int noOfItems) throws Throwable {
        footerTabsScreen.tapCart();
        int itemCount = Integer.parseInt(lblItemCountStoreOnTop.getText().split("\\s+")[0]);
        System.out.println("itemCount-Con"+itemCount);
        Assert.assertEquals(noOfItems , itemCount, "Item count is not same");

    }

    @Then("^I should see '(.*)' dollars as total amount$")
    public void iShouldSee$AsTotalAmount(String totalAmount) throws Throwable {
        String totalPrice = "";
        totalPrice = UIElement.byXpath("//android.widget.FrameLayout[contains(@resource-id,'com.curbside.nCurbside:id/button_purchase')]").waitFor(5).getText().split("\\$")[1];
        Assert.assertEquals(totalPrice, totalAmount, "Total amount of the items in the store is not same");
    }

    @And("^I refresh the sub-store details if displayed$")
    public void iRefreshTheSubstoreDetailsIfDisplayed() throws Throwable {
        if(lblItemCountSubStore.isDisplayed()){
            firstRetailerIcon.tap();
            btnCartItemQnty.waitFor(5).tap();
            btnBack.tap();
        }
    }

}
