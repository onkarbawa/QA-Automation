package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
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

public class Cart extends AbstractScreen {
	UIElement placeOrder = UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'Place Order ')]");
	UIElement creditCardCell = UIElement.byXpath("//XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeButton[2]");
	UIElement extracareCardCell = UIElement.byXpath("//XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeButton[3]");
	UIElement deleteItem = UIElement.byName("Delete");
	UIElement productItem = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'item')]]/following-sibling::XCUIElementTypeCell");
	UIElement productaQuantityButton = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'item')]]/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeButton");

	UIElement popUpHeading = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Please turn on the following']");
	UIElement settings = UIElement.byName("Settings");
	UIElement checkoutTitle = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Your order has been placed.']");

	UIElement selectedStores = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell");
	UIElement lastProduct = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Estimated Total')]]/preceding-sibling::XCUIElementTypeCell[1]");
	UIElement selectedProducts = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Estimated Total')]]/preceding-sibling::XCUIElementTypeCell");

	UIElement totalItems = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Estimated Total')]/preceding-sibling::XCUIElementTypeStaticText[contains(@name,'Items')]");
	UIElement itemsTotalPrice = UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Estimated Total')]]//XCUIElementTypeStaticText[2]");

	public String getAddedProductUI() throws Throwable {
		return UIElement.byXpath("//XCUIElementTypeStaticText[@name='" + Properties.getVariable("productName") + "']").getText();
	}


	public Cart() {
		// TODO Auto-generated constructor stub
	}

	@Given("I attempt to place an order")
	public void placeOrder() throws Throwable {
		placeOrder.tap();
		placeOrder.waitForNot(5);
	}

	@And("^I should see credit info on cart screen$")
	public void iShouldSeeCreditInfoOnCartScreen() throws Throwable {
		creditCardCell.waitFor(10).tap();
		paymentInfoScreen.iShouldSeeCreditInfoOnPaymentInfoScreen();
		footerTabsScreen.tapMyAccount();
	}

	@And("^I should see loyalty card info on cart screen$")
	public void iShouldSeeLoyaltyCardInfoOnCartScreen() throws Throwable {
		extracareCardCell.tap();
		loyalityCardScreen.iShouldSeeExtraCareCardInfoOnLoyaltyCardsScreen();
	}

	@Then("^I saw added product in cart$")
	public void iSawAddedProductInCart() throws Throwable {
		footerTabsScreen.tapCart();
		Assert.assertEquals(getAddedProductUI(), Properties.getVariable("productName"),"Added product not shown in the cart");
	}

    @Given("^My cart is empty$")
    public void myCartIsEmpty() throws Throwable {
		footerTabsScreen.btnCart.waitFor(10).tap();
		footerTabsScreen.tapCart();
		if (selectedStores.isDisplayed()) {
			int totalStores = selectedStores.getCount();
			for (int i = 0; i < totalStores; i++) {
				selectedStores.tap();


			} try {
					if (selectedProducts.isDisplayed()) {
						int totalSelectedProducts = selectedProducts.getCount();
						if (totalSelectedProducts > 4) {
							for (int j = 0; j < totalSelectedProducts - 4; j++) {
								lastProduct.tap();
								int totalItems = productItem.getCount();
								for (int k = 0; k < totalItems - 3; k++) {
									productaQuantityButton.tap();
									UIElement.byName("Remove").tap();
									UIElement.byName("Remove").waitForNot(8);
								}

							}
						}
					 else {
						lastProduct.tap();
						int totalItems = productItem.getCount();
						for (int k = 0; k < totalItems - 3; k++) {
							productaQuantityButton.tap();
							UIElement.byName("Remove").tap();
							UIElement.byName("Remove").waitForNot(8);
						}
					}
				}

				}
			catch (Exception e) {
				if (productItem.isDisplayed()) {
					int totalItems = productItem.getCount();
					for (int j = 0; j < totalItems - 3; j++) {
						productaQuantityButton.tap();
						UIElement.byName("Remove").tap();
						UIElement.byName("Remove").waitForNot(8);
					}
				}

			}
		}
	}

	@Then("^I should see checkout not allowed$")
	public void iShouldSeeCheckoutNotAllowed() throws Throwable {
		Assert.assertEquals(popUpHeading.getText(),"Please turn on the following");
		settings.tap();
	}

	@Then("^I should see checkout screen$")
	public void iShouldSeeCheckoutScreen() throws Throwable {
		Assert.assertEquals(checkoutTitle.getText(),"Your order has been placed.");
	}

	@Then("^I should see the (\\d+) items in the cart$")
	public void iShouldSeeTheItemsInTheCart(int noOfItems) throws Throwable {
		try {
			lastProduct.tap();
		}catch (Exception e){}
		Assert.assertTrue(totalItems.getText().contains(String.valueOf(noOfItems)), "Item count is not same");
	}

	public double calculateItemPrice() throws Throwable {
		int totalItems = productItem.getCount();
        double totalPrice = 0;
		for (int k = 0; k < totalItems-3; k++) {
			String singleItemPrice = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'item')]]/following-sibling::XCUIElementTypeCell[" + (k+1) + "]//XCUIElementTypeStaticText[3]").getText();
			Double itemPrice = Double.parseDouble(singleItemPrice.split("₹")[1].substring(1));
            totalPrice = totalPrice + itemPrice;
		}
		return totalPrice;
	}

	@Then("^I should see added product total amount$")
	public void iShouldSeeAddedProductTotalAmount() throws Throwable {
		Double totalPrice = Double.parseDouble(itemsTotalPrice.getText().split("₹")[1].substring(1));
		Assert.assertEquals(totalPrice, calculateItemPrice(),"Total amount of the items in the store is not same");
	}
}
