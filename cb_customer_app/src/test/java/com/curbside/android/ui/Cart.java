package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.SwipeDirection;
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

import java.text.DecimalFormat;
import java.util.Arrays;

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
    UIElement lblItemCountOnMuilpleStoresScreen = UIElement.byId("com.curbside.nCurbside:id/item_number");
    UIElement lblItemCountSubStore = UIElement.byId("com.curbside.nCurbside:id/item_count_view");

    UIElement firstRetailerIcon = UIElement.byId("com.curbside.nCurbside:id/image_store_icon");
    UIElement btnContinueShopping = UIElement.byId("com.curbside.nCurbside:id/button_continue_shopping");
    UIElement lblItemCountOnTitle = UIElement.byId("com.curbside.nCurbside:id/text_item_count");
    UIElement estimatedPickUpTime = UIElement.byId("com.curbside.nCurbside:id/text_estimated_pickup_time");
    UIElement loyaltyCardName = UIElement.byId("com.curbside.nCurbside:id/text_loyalty_name");
    UIElement paymentCardName = UIElement.byId("com.curbside.nCurbside:id/text_card_name");
    UIElement lblDeliveryInfo = UIElement.byId("com.curbside.nCurbside:id/delivery_text");
    UIElement lblItemPriceView = UIElement.byId("com.curbside.nCurbside:id/priceView");
    UIElement btnBack = UIElement.byId("com.curbside.nCurbside:id/button_back");
    UIElement btnPlaceOrder =UIElement.byId("com.curbside.nCurbside:id/button_purchase");
    UIElement lblCartTitle = UIElement.byId("com.curbside.nCurbside:id/cart_title");

    UIElement addNewAddressButton = UIElement.byId("com.curbside.nCurbside:id/button_add_new_address");
    UIElement addressSearchField = UIElement.byId("com.google.android.gms:id/edit_text");
    UIElement addressSuggestions = UIElement.byXpath("//*[@resource-id='com.google.android.gms:id/list']/android.widget.RelativeLayout");
    UIElement savedAddress = UIElement.byId("com.curbside.nCurbside:id/list_item_pickup");
    UIElement verifyAddressPopup = UIElement.byUISelector("new UiSelector().text(\"Verifying Address \")");
    UIElement addPromoCodeLink = UIElement.byId("com.curbside.nCurbside:id/button_promo");
    UIElement promoCodeField = UIElement.byId("com.curbside.nCurbside:id/edit_promo_code");
    UIElement applyButton = UIElement.byId("com.curbside.nCurbside:id/text_save");
    UIElement itemsTotalPrice = UIElement.byId("com.curbside.nCurbside:id/text_total_price");
    UIElement promoCodeDiscount = UIElement.byId("com.curbside.nCurbside:id/text_promo_discount");
    UIElement promoCodeSuggestionDialog = UIElement.byId("android:id/button3");
    UIElement estimatedTax = UIElement.byId("com.curbside.nCurbside:id/text_estimated_tax");
    UIElement estimatedTotal = UIElement.byId("com.curbside.nCurbside:id/text_estimated_total");
    UIElement btnCancelPromoPopUp = UIElement.byId("com.curbside.nCurbside:id/text_cancel");
    UIElement deliveryCharge = UIElement.byId("com.curbside.nCurbside:id/price_delivery");
    UIElement btnPlaceOrderUISelector = UIElement.byUISelector("new UiSelector().textStartsWith(\"PLACE ORDER\")");
    UIElement lblOrderPlaced = UIElement.byId("com.curbside.nCurbside:id/order_placed");
    UIElement btnUber = UIElement.byUISelector("new UiSelector().text(\"Delivery by UBER \")");




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
            if(Arrays.asList(cartTitleText).contains("cart") && lblItemCountSubStore.waitFor(5).isDisplayed()){
                firstRetailerIcon.tap();
            }
            if((Arrays.asList(cartTitleText).contains("order") || Arrays.asList(cartTitleText).contains("cart"))
                    && paymentCardName.waitFor(5).isDisplayed()){
                int itemsInCart;
                if(lblItemCountOnTitle.waitFor(3).isDisplayed()){
                    itemsInCart = Integer.parseInt(lblItemCountOnTitle.getText().split("\\s+")[0]);
                }else{
                    itemsInCart =0 ;
                }
                for(int j =0 ; j < itemsInCart ; j++) {
                    if(btnContinueShopping.waitFor(3).isDisplayed())
                        break;
                    btnCartItemQnty.swipeUpSlow();
                    btnCartItemQnty.tap();
                    btnRemove.waitFor(2).tap();
                    Thread.sleep(2000);
                    try{
                        promoCodeSuggestionDialog.waitFor(2).tap();
                    }catch (Exception e){}
                }
            }
        }
    }

    @Then("^I should see the (\\d+) items in the cart$")
    public void iShouldSeeTheItemsInTheCart(int noOfItems) throws Throwable {
        footerTabsScreen.tapCart();
        int itemCount = Integer.parseInt(lblItemCountOnTitle.getText().split("\\s+")[0]);
        Assert.assertEquals(noOfItems , itemCount, "Item count is not same");

    }

    @Then("^I verify the total amount in the cart$")
    public void iShouldSee$AsTotalAmount() throws Throwable {
        itemsTotalPrice.scrollTo(SwipeDirection.UP);
        double calculatedPrice = Double.parseDouble(productDetailsScreen.addedProductDetails.get().get("totalAmount"));
        DecimalFormat df = new DecimalFormat("#.##");
        calculatedPrice = Double.valueOf(df.format(calculatedPrice));
        Assert.assertEquals(itemsTotalPrice.getText().split("\\$")[1],String.valueOf(calculatedPrice),
                "Total amount of the items in the store is not same");
    }

    @And("^I refresh the sub-store details if displayed$")
    public void iRefreshTheSubstoreDetailsIfDisplayed() throws Throwable {
        if(lblItemCountSubStore.isDisplayed()){
            firstRetailerIcon.tap();
            btnCartItemQnty.waitFor(5).tap();
            btnBack.tap();
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
                String streetName = element.findElement(By
                  .id("com.google.android.gms:id/place_autocomplete_prediction_primary_text")).getText();
                if (streetName.contains(street)) {
                    String cityState =  element.findElement(By
                      .id("com.google.android.gms:id/place_autocomplete_prediction_secondary_text")).getText();
                    if (cityState.contains(city) && cityState.contains(state)) {
                        element.click();
                        break;
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
            addPromoCodeLink.scrollTo(SwipeDirection.UP);
        }
        addPromoCodeLink.tap();
    }

    @When("^I apply promo code \"([^\"]*)\"$")
    public void iApplyPromoCodeOfType(String promoCode) throws Throwable {
        promoCodeField.waitFor(5).sendKeys(promoCode);
        applyButton.tap();

        if(btnCancelPromoPopUp.waitFor(8).isDisplayed())
            btnCancelPromoPopUp.tap();
    }

    @Then("^I should see promo code is applied and discount is given as per '(.*)'")
    public void iShouldSeePromoCodeIsApplied(String discountType) throws Throwable {
        DecimalFormat df = new DecimalFormat("#.##");
        Double expectedDiscount = 0.00;
        Double actualDeliveryCharges = 0.00;
        Double latestDeliveryCharges = 0.00;
        Double actualDiscount = 0.00;
        if(Properties.getVariable("Delivery Charges") != null)
            actualDeliveryCharges = Double.parseDouble(Properties.getVariable("Delivery Charges").split("\\$")[1]);

        addPromoCodeLink.scrollTo(SwipeDirection.UP);
        Double totalItemPrice = Double.parseDouble(itemsTotalPrice.getText().split("\\$")[1]);
        Double estimatedTaxPrice = Double.parseDouble(estimatedTax.getText().split("\\$")[1]);
        Double actualEstimatedTotal = Double.parseDouble(estimatedTotal.getText().split("\\$")[1]);

        if(promoCodeDiscount.isDisplayed())
            actualDiscount = Double.parseDouble(promoCodeDiscount.getText().split("\\$")[1]);
        if(deliveryCharge.isDisplayed())
            latestDeliveryCharges = Double.parseDouble(deliveryCharge.getText().split("\\$")[1]);
        Double expectedDeliveryCharges =0.00;

        switch (discountType.toLowerCase()){
            case "unlimited":
                expectedDiscount = 10.00;
                Assert.assertEquals(expectedDiscount, actualDiscount,
                        "UNLIMITED promo code is not having $10 discount");
                break;

            case "dollar-delivery":
                expectedDiscount =  5.00;
                expectedDeliveryCharges = Double.valueOf(df.format(actualDeliveryCharges - expectedDiscount));
                Assert.assertEquals(expectedDeliveryCharges, latestDeliveryCharges,
                        "NF_DOLLAR_DS promo code is not applied to the delivery charges");

                break;

            case "percent-delivery":
                expectedDiscount =  Double.valueOf(df.format(actualDeliveryCharges * 0.10)) ;
                expectedDeliveryCharges = Double.valueOf(df.format(actualDeliveryCharges - expectedDiscount));
                Assert.assertEquals(expectedDeliveryCharges, latestDeliveryCharges,
                        "NF_PERCENT_DS promo code is not applied to the delivery charges");
                break;

            case "free-delivery":
                expectedDiscount = actualDeliveryCharges;
                expectedDeliveryCharges = Double.valueOf(df.format(actualDeliveryCharges - expectedDiscount));
                Assert.assertEquals(expectedDeliveryCharges, latestDeliveryCharges,
                        "NF_FREE_DS promo code is not applied to the delivery charges");

                break;

            case "fixed-delivery":
                expectedDiscount =  Double.valueOf(df.format(actualDeliveryCharges - 0.01));
                expectedDeliveryCharges = 0.01;
                Assert.assertEquals(expectedDeliveryCharges, latestDeliveryCharges,
                        "NF_FIXED_DS promo code is not applied to the delivery charges");

                break;

            default: Assert.fail(discountType.toUpperCase() + " not a discount type");
            break;
        }
        Double expectedEstimatedTotal = (totalItemPrice + estimatedTaxPrice + actualDeliveryCharges) - expectedDiscount;
        expectedEstimatedTotal = Double.valueOf(df.format(expectedEstimatedTotal));
        Assert.assertEquals(actualEstimatedTotal, expectedEstimatedTotal, "Promo code discount calculation is not correct");
    }


    @Then("^The price of the product should be same$")
    public void thePriceOfTheProductShouldBeSame() throws Throwable {
        String productName = productDetailsScreen.addedProductDetails.get().get("productName");
        String xpathExpression= "//android.widget.LinearLayout[android.widget.TextView[contains(@text,'"+productName+"')]]/" +
                "following-sibling::android.widget.LinearLayout/android.widget.TextView";
        UIElement priceView = UIElement.byXpath(xpathExpression);
        priceView.swipeUpSlow();
        String amount = priceView.getText().split("\\$")[1];
        Assert.assertEquals(productDetailsScreen.addedProductDetails.get().get("amount"), amount,
                "product price is different now");
    }

    @And("^I store the value of '(.*)'$")
    public void iStoreTheValueOf(String value) throws Throwable {
        addPromoCodeLink.scrollTo(SwipeDirection.UP);
        if(deliveryCharge.isDisplayed())
            Properties.setVariable(value, deliveryCharge.getText());
    }

    @And("^I tap on Place order button$")
    public void iTapOnPlaceOrderButton() throws Throwable {
        try{
         btnPlaceOrder.waitFor(2).tap();
        }catch (Exception e){}

        try{
            btnPlaceOrderUISelector.tap();
        }catch (Exception e){}
    }

    @Then("^I should see the successful placed order notification on the screen$")
    public void iShouldSeeTheSuccessfulPlacedOrderNotificationOnTheScreen() throws Throwable {
        lblOrderPlaced.waitFor(8);
        Assert.assertTrue(lblOrderPlaced.isDisplayed(),
                "Still on cart screen or Order Placed notification is not visible yet");
    }

    @And("^I remove and add the product again to the cart$")
    public void reAddTheProduct() throws Throwable {
        productName.waitFor(2).tap();
        int itemQnty = Integer.parseInt(productDetailsScreen.productQnty.waitFor(8).getText());
        int i = 0;
        while(!productDetailsScreen.btnAddtoCart.isDisplayed() && i <15)
        {
            try {
                productDetailsScreen.btnRemove.waitFor(5).tap();
            }catch (Exception e){}
            ++i;
        }

        for (i = 0; i < itemQnty; i++) {
            try {
                productDetailsScreen.btnAddtoCart.waitFor(4);
                productDetailsScreen.btnAddtoCart.tap();
            }catch (Exception e){
                productDetailsScreen.btnAdd.tap();
            }
        }

        if(Integer.parseInt(productDetailsScreen.productQnty.waitFor(5).getText()) < 2)
            productDetailsScreen.btnAdd.tap();

        AndroidDevice.goBack();
        Thread.sleep(1000);
    }

    @And("^I tap on Delivery with UBER button$")
    public void iTapOnDeliveryWithUBERButton() throws Throwable {
        btnUber.waitFor(5);
        btnUber.swipeUpSlow();
        btnUber.tap();
    }
}
