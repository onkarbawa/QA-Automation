package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * @author kumar.anil
 *
 */

public class ProductDetails extends AbstractScreen {

	UIElement btnAddtoCart = UIElement.byAccessibilityId("Add To Cart");
	UIElement btnRemove = UIElement.byAccessibilityId("Remove");

	UIElement productImage = UIElement.byXpath("//XCUIElementTypeScrollView/XCUIElementTypeScrollView/XCUIElementTypeImage");
	UIElement productName = UIElement.byXpath("//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeStaticText[1]");
	UIElement productDescription = UIElement.byXpath("//XCUIElementTypeTextView[contains(@value,'Description')]");
	UIElement productOverview = UIElement.byXpath("//XCUIElementTypeTextView[contains(@value,'Overview')]");
	UIElement productSKU = UIElement.byXpath("//XCUIElementTypeTextView[contains(@value,'sku')]");

	public ProductDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getProductName() throws Throwable {
		return productName.getText();
	}

	@Given("^I add displayed product to cart$")
	public void addToCart() throws Throwable {
		try {
			btnAddtoCart.tap();
		}catch (Exception e){}
		btnRemove.waitFor(10);
	}

	@Given("^I removed displayed product from cart$")
	public void removeFromCart() throws Throwable {
		btnRemove.tap();
		btnAddtoCart.waitFor(10);
	}

	@Then("^I should see product details as below$")
	public void iShouldSeeProductDetailsAsBelow() throws Throwable {
		Assert.assertTrue(productImage.waitFor(15).isDisplayed(),"Product Image is not displayed");
		Assert.assertTrue(productName.isDisplayed(),"Product name and price is not displayed");
		Assert.assertTrue(productDescription.isDisplayed(),"Product description is not displayed");
		Assert.assertTrue(productSKU.isDisplayed(), "Product sku is not displayed");
		productOverview.scrollTo();
		Assert.assertTrue(productOverview.isDisplayed(),"Product overview is not displayed");
	}
}