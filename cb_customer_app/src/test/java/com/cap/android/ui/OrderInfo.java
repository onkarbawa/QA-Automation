package com.cap.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by hitesh.grover on 13/09/17.
 */
public class OrderInfo extends AbstractScreenCap {

    UIElement lblSKU = UIElement.byId("com.curbside.nCap:id/tvSKUHeader");


    @And("^I click on a product from the order list$")
    public void iClickOnProduct() throws Throwable {
        lblSKU.waitFor(2).tap();
    }
}
