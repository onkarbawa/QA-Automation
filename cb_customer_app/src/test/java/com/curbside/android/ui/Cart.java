package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

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
    UIElement lblStoreTotalItemQnty = UIElement.byId("com.curbside.nCurbside:id/item_number");
    UIElement lblSubStoreTotalItemQnty = UIElement.byId("com.curbside.nCurbside:id/item_count_view");

    UIElement firstRetailerIcon = UIElement.byId("com.curbside.nCurbside:id/image_store_icon");
    UIElement btnContinueShopping = UIElement.byId("com.curbside.nCurbside:id/button_continue_shopping");
    UIElement textItemsAndStores = UIElement.byId("com.curbside.nCurbside:id/text_item_count");
    UIElement estimatedPickUpTime = UIElement.byId("com.curbside.nCurbside:id/text_estimated_pickup_time");
    UIElement loyaltyCardName = UIElement.byId("com.curbside.nCurbside:id/text_loyalty_name");
    UIElement paymentCardName = UIElement.byId("com.curbside.nCurbside:id/text_card_name");

    UIElement addNewAddressButton = UIElement.byId("com.curbside.nCurbside:id/button_add_new_address");
    UIElement addressSearchField = UIElement.byId("com.google.android.gms:id/edit_text");
    UIElement addressSuggestions = UIElement.byXpath("//*[@resource-id='com.google.android.gms:id/list']/android.widget.RelativeLayout");
    UIElement savedAddress = UIElement.byId("com.curbside.nCurbside:id/list_item_pickup");
    UIElement verifyAddressPopup = UIElement.byUISelector("new UiSelector().text(\"Verifying Address \")");
    UIElement addPromoCodeLink = UIElement.byId("com.curbside.nCurbside:id/button_promo");
    UIElement promoCodeField = UIElement.byId("com.curbside.nCurbside:id/edit_promo_code");
    UIElement applyButton = UIElement.byId("com.curbside.nCurbside:id/text_save");



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
     */
    @Given("^My cart is empty$")
    public void myCartIsEmpty() throws Throwable {
        footerTabsScreen.tapCart();
        firstRetailerIcon.waitFor(5).isDisplayed();
        footerTabsScreen.tapCart();
        // If there is nothing in the cart execution will not go in.
        if (!btnContinueShopping.isDisplayed()){
            // default value
            int totalItemsInStoreCart = 1;
            while(totalItemsInStoreCart > 0){
                totalItemsInStoreCart = lblStoreTotalItemQnty.getCount();

                /**
                 * Clicking Stores eg. CVS or Westfield
                 */
                if(totalItemsInStoreCart != 0) {
                    firstRetailerIcon.tap();
                }
                paymentCardName.waitFor(10);

                // if there is no sub store then also execution will go in
                int itemsInSubStore = 1;
                while(itemsInSubStore > 0){
                    itemsInSubStore = lblSubStoreTotalItemQnty.getCount();
                    /**
                     * Clicking Sub Stores eg. Lush or GNC
                     */
                    if(itemsInSubStore != 0)
                    {
                        firstRetailerIcon.tap();
                    }
                    int itemsInCart= 1;
                    while(itemsInCart > 0){
                        btnCartItemQnty.waitFor(20);
                        if (loyaltyCardName.isDisplayed() && paymentCardName.isDisplayed() && !btnCartItemQnty.isDisplayed()) {
                            btnCartItemQnty.swipeUpSlow();
                        }
                        itemsInCart = btnCartItemQnty.getCount();
                        /**
                         * Clicking item in the Store
                         */
                        if(itemsInCart != 0) {
                            btnCartItemQnty.tap();
                            btnRemove.waitFor(2).tap();
                            Thread.sleep(2000);
                        }
                        // updating items dynamically and set it in while loop
                        itemsInCart = btnCartItemQnty.getCount();
                        System.out.print("-----------total items in the list----------"+totalItemsInStoreCart);
                    }
                    // updating subStore value dynamically and set it in while loop
                    itemsInSubStore = lblSubStoreTotalItemQnty.getCount();
                    System.out.print("-----------total SubStores in the list----------"+totalItemsInStoreCart);
                }
                // updating Store value dynamically and set it in while loop
                totalItemsInStoreCart = lblStoreTotalItemQnty.getCount();
                System.out.print("-----------total Stores in the list----------"+totalItemsInStoreCart);
            }
        }

    }

    @And("^I select the delivery address as, street:\"([^\"]*)\", city:\"([^\"]*)\",state:\"([^\"]*)\"$")
    public void iSelectTheDeliveryAddressAsStreetCityState(String street, String city, String state) throws Throwable {
        if (savedAddress.waitFor(5).isDisplayed()) {
            savedAddress.tap();
        } else {
            addNewAddressButton.tap();
            addressSearchField.waitFor(5).sendKeys(street, true);
            addressSuggestions.waitFor(5);
            List<WebElement> list = addressSuggestions.getElements();
            for (WebElement element : list) {
                String streetName = element.findElement(By.id("com.google.android.gms:id/place_autocomplete_prediction_primary_text"))
                  .getText();
                if (streetName.contains(street)) {
                    String cityState =  element.findElement(By.id("com.google.android.gms:id/place_autocomplete_prediction_secondary_text"))
                      .getText();
                    if (cityState.contains(city)) {
                       if (cityState.contains(state)) {
                           element.click();
                           break;
                       }
                    }
                }
            }
        }
        verifyAddressPopup.waitFor(5);
        verifyAddressPopup.waitForNot(20);
    }

    @And("^I tap on Enter promo code link$")
    public void iTapOnEnterPromoCodeLink() throws Throwable {
        if (!addPromoCodeLink.waitFor(2).isDisplayed()) {
            AndroidDevice.swipeUp();
        }
        addPromoCodeLink.tap();
    }

    @When("^I apply promo code \"([^\"]*)\" of type \"([^\"]*)\"$")
    public void iApplyPromoCodeOfType(String promoCode, String discountType) throws Throwable {
        Properties.setVariable("discountType", discountType);
        promoCodeField.waitFor(5).sendKeys(promoCode);
        applyButton.tap();
    }

    @Then("^I should see promo code is applied$")
    public void iShouldSeePromoCodeIsApplied() throws Throwable {
        // TODO - waiting for promocodes
    }
}
