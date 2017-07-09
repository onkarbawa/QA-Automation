package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;

import cucumber.api.java.en.Given;

/**
 * @author kumar.anil
 *
 */

public class ProductDetails extends AbstractScreen {

	UIElement btnAddtoCart = UIElement.byAccessibilityId("Add To Cart");
	UIElement btnRemove = UIElement.byAccessibilityId("Remove");

	@Given("^I add displayed product to cart$")
	public void addToCart() throws Throwable {
		btnAddtoCart.tap();
		btnRemove.waitFor(10);
	}

	@Given("^I removed displayed product from cart$")
	public void removeFromCart() throws Throwable {
		btnRemove.tap();
		btnAddtoCart.waitFor(10);
	}
}