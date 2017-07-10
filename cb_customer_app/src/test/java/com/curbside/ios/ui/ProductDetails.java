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

	public ProductDetails() {
		// TODO Auto-generated constructor stub
	}

	@Given("^I add displayed product to cart$")
	public void addToCart() throws Throwable {
		try{
			btnAddtoCart.tap();
		}catch (Exception e){}

		btnRemove.waitFor(10);
	}

	@Given("^I removed displayed product from cart$")
	public void removeFromCart() throws Throwable {
		btnRemove.tap();
		btnAddtoCart.waitFor(10);
	}
}