package com.curbside.ios.ui;

import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.SwipeDirection;
import com.curbside.automation.uifactory.UIElement;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
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
	UIElement productLocationAndPrice = UIElement.byXpath("//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeStaticText[2]");
	UIElement productDescription = UIElement.byXpath("//XCUIElementTypeTextView[contains(@value,'Description')]");
	UIElement productOverview = UIElement.byXpath("//XCUIElementTypeTextView[contains(@value,'Overview')]");
	UIElement productSKU = UIElement.byXpath("//XCUIElementTypeTextView[contains(@value,'sku')]");

	UIElement incrementProduct = UIElement.byName("Increment");
	UIElement btnBack = UIElement.byName("Back");

	//Product Quantity Over
	UIElement quantityMessage = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Sorry']");
	UIElement quantityMessageText = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Sorry']/following-sibling::XCUIElementTypeStaticText");

	public ProductDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getProductName() throws Throwable {
		return productName.getText();
	}
	
	public String getProductPrice() throws Throwable {
		String itemPrice;
		int lengthOfArray = productLocationAndPrice.getText().split("\\$").length;
		if (lengthOfArray>1){
			 itemPrice = productLocationAndPrice.getText().split("\\$")[lengthOfArray-1];
		}else {
			 itemPrice = productLocationAndPrice.getText().split("\\$")[lengthOfArray];
		}
		System.out.println(itemPrice);
		return itemPrice;
	}

	@Given("^I add displayed product to cart$")
	public void addToCart() throws Throwable {
		if (!btnAddtoCart.isDisplayed()){
			btnAddtoCart.scrollTo(SwipeDirection.DOWN);
			//btnAddtoCart.scrollTo();
		}
		btnAddtoCart.tap();

		if (quantityMessage.waitFor(4).isDisplayed()){
			System.out.println(quantityMessageText.getText());
			Steps.tapButton("OK");
			btnAddtoCart.waitFor(5).tap();
		}
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
		Assert.assertTrue(productSKU.scrollTo(SwipeDirection.UP).isDisplayed(), "Product sku is not displayed");
		productOverview.scrollTo(SwipeDirection.UP);
		Assert.assertTrue(productOverview.isDisplayed(),"Product overview is not displayed");
	}

	@And("^I add (\\d+) quantity of the product$")
	public void iAddQuantityOfIt(int noOfTimes) throws Throwable {
		addToCart();
		if (noOfTimes > 1){
			for(int i = 0; i < noOfTimes-1; i++){
				incrementProduct.tap();
			}
		}
		btnBack.tap();
	}
}