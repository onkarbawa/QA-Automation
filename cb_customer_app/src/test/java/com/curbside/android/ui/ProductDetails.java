package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.Given;

/**
 * Created by hitesh.grover on 12/07/17.
 */
public class ProductDetails extends AbstractScreen {

    UIElement btnAddtoCart = UIElement.byId("com.curbside.nCurbside:id/button_add");
    UIElement snackBarKeepShopping = UIElement.byId("com.curbside.nCurbside:id/snackbar_action");
    UIElement btnRemove = UIElement.byId("com.curbside.nCurbside:id/button_reduce_item");
    UIElement btnAdd = UIElement.byId("com.curbside.nCurbside:id/button_add_item");

    @Given("^I add displayed product to cart$")
    public void addToCart() throws Throwable {
        try {
            btnAddtoCart.tap();
        }catch (Exception e){
            btnAdd.waitFor(10);
        }
    }

    @Given("^I removed displayed product from cart$")
    public void removeFromCart() throws Throwable {
        btnRemove.tap();
        btnAddtoCart.waitFor(10);
    }
}
