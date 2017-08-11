package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

import java.util.*;

/**
 * Created by hitesh.grover on 12/07/17.
 */
public class ProductDetails extends AbstractScreen {

    UIElement btnAddtoCart = UIElement.byId("com.curbside.nCurbside:id/button_add");
    UIElement snackBarKeepShopping = UIElement.byId("com.curbside.nCurbside:id/snackbar_action");
    UIElement btnRemove = UIElement.byId("com.curbside.nCurbside:id/button_reduce_item");
    UIElement btnAdd = UIElement.byId("com.curbside.nCurbside:id/button_add_item");
    UIElement productImage = UIElement.byId("com.curbside.nCurbside:id/image");
    UIElement productName = UIElement.byId("com.curbside.nCurbside:id/text_name_view");
    UIElement productDescription = UIElement.byId("com.curbside.nCurbside:id/description_view");
    UIElement productSKU = UIElement.byId("com.curbside.nCurbside:id/sku_id");
    UIElement productOverview = UIElement.byId("com.curbside.nCurbside:id/overview_view");
    UIElement productQnty = UIElement.byId("com.curbside.nCurbside:id/quantity_view");
    UIElement productPrice = UIElement.byId("com.curbside.nCurbside:id/text_status_view");
    UIElement errorProductNotAvail = UIElement.byId("android:id/button3");


    static ThreadLocal<java.util.Map<String,String>> addedProductDetails = new ThreadLocal<>();

    @Given("^I add displayed product to cart$")
    public void addToCart() throws Throwable {

        if(!errorProductNotAvail.isDisplayed()){
            if(addedProductDetails.get() == null)
                addedProductDetails.set(new HashMap<>());

            try {
                btnAddtoCart.waitFor(10);
                btnAddtoCart.tap();
            }catch (Exception e){
                btnAdd.tap();
            }
            if(productPrice.isDisplayed())
                addedProductDetails.get().put("amount", productPrice.getText().split("\\$")[1]);
        }else{
            addedProductDetails.get().clear();
            MobileDevice.getScreenshot(true);
            Assert.fail("Got the error message : This product is not available in the store now");
        }
    }

    @Given("^I removed displayed product from cart$")
    public void removeFromCart() throws Throwable {
        btnRemove.tap();
        btnAddtoCart.waitFor(10);
    }

    @Then("^I should see product details as below$")
    public void iShouldSeeProductDetailsAsBelow() throws Throwable {
        if(!productName.waitFor(3).isDisplayed())
            productName.swipeUpSlow();
        Properties.setVariable("productName",productName.getText());
        productDescription.swipeUpSlow();
        Assert.assertTrue(productDescription.isDisplayed(),"Product description is not displayed");
        Assert.assertTrue(productSKU.isDisplayed(), "Product sku is not displayed");
        productOverview.swipeUpSlow();
        Assert.assertTrue(productOverview.isDisplayed(),"Product overview is not displayed");
    }

    public String getProductName() throws Throwable {
        return productName.getText();
    }

    @And("^I add product in cart$")
    public void iAddProductInCart() throws Throwable {

        productDetailsScreen.addToCart();
        snackBarKeepShopping.isDisplayed();
        AndroidDevice.goBack();
    }

    @And("^I add (\\d+) quantity of the product$")
    public void iAddQuantityOfIt(int noOfTimes) throws Throwable {

        for(int i = 0; i < noOfTimes; i++){
            addToCart();
            Thread.sleep(1000);
        }
        AndroidDevice.goBack();
        Double subAmount = Double.parseDouble(addedProductDetails.get().get("amount"))*noOfTimes;
        if(addedProductDetails.get().containsKey("totalAmount")){
            Double totalAmount = subAmount + Double.parseDouble(addedProductDetails.get().get("totalAmount"));
            addedProductDetails.get().put("totalAmount",String.valueOf(totalAmount));
        }else{
            addedProductDetails.get().put("totalAmount", String.valueOf(subAmount));
        }
    }
}
