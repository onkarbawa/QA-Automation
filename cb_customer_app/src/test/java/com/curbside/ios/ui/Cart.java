package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
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
	UIElement productItem = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'item')]]/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]");
	UIElement productaAddButton = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'item')]]/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeButton");

	UIElement popUpHeading = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Please turn on the following']");
	UIElement settings = UIElement.byName("Settings");
	UIElement checkoutTitle = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Your order has been placed.']");

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
       footerTabsScreen.tapCart();
		while (true){
			if (productItem.isDisplayed()) {
				productaAddButton.tap();
				UIElement.byName("Remove").tap();
				UIElement.byName("Remove").waitForNot(8);
			}
			else {
				break;
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

}
