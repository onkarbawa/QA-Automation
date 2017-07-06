package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;

import cucumber.api.java.en.Given;

/**
 * @author kumar.anil
 *
 */

public class Cart {
	static UIElement placeOrder= UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'Place Order ')]");
	
	@Given("I attempt to place an order")
	public void tapPlaceOrder() throws Throwable
	{
		placeOrder.tap();
	}
}
