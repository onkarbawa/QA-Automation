package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

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



    @Given("^I add displayed product to cart$")
    public void addToCart() throws Throwable {
        try {
            btnAddtoCart.tap();
            btnAddtoCart.waitFor(5);
        }catch (Exception e){
            btnAdd.waitFor(10);
        }
    }

    @Given("^I removed displayed product from cart$")
    public void removeFromCart() throws Throwable {
        btnRemove.tap();
        btnAddtoCart.waitFor(10);
    }

    @Then("^I should see product details as below$")
    public void iShouldSeeProductDetailsAsBelow() throws Throwable {
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
        Properties.setVariable("productName",productDetailsScreen.getProductName());
        productDetailsScreen.addToCart();
        snackBarKeepShopping.isDisplayed();
        AndroidDevice.goBack();
    }
}
