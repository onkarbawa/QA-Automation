package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.*;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.text.DecimalFormat;

/**
 * @author kumar.anil
 *
 */

public class Cart extends AbstractScreen {
	UIElement placeOrder = UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'Place Order ')]");
	UIElement creditCardCell = UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeButton[contains(@name,'Place Order')]]/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeButton[2]");
	UIElement extracareCardCell = UIElement.byXpath("//XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeButton[3]");
	UIElement deleteItem = UIElement.byName("Delete");
	UIElement productItem = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'item')]]/following-sibling::XCUIElementTypeCell");
	UIElement productaQuantityButton = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'item')]]/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeButton");

	UIElement popUpHeading = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Please Turn on the Following in Settings']");
	UIElement settings = UIElement.byName("Settings");
	UIElement checkoutTitle = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Your order has been placed.']");

	UIElement selectedStores = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell");
	UIElement lastProduct = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Estimated Total')]]/preceding-sibling::XCUIElementTypeCell[1]");
	UIElement substores = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Estimated Total')]]/preceding-sibling::XCUIElementTypeCell");

	UIElement totalItems = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Estimated Total')]/preceding-sibling::XCUIElementTypeStaticText[contains(@name,'Items')]");

	UIElement itemsTotalPrice = UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Estimated Total')]]//XCUIElementTypeStaticText[2]");
	UIElement estimatedTax = UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Estimated Total')]]//XCUIElementTypeStaticText[4]");
	UIElement estimatedTotal = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Estimated Total')]/following-sibling::XCUIElementTypeStaticText[1]");

	UIElement promoCode = UIElement.byXpath("//XCUIElementTypeOther[XCUIElementTypeStaticText[@name='Enter Promo Code']]//XCUIElementTypeCollectionView//XCUIElementTypeTextField");
	UIElement promoCodeDiscount = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Discount')]/following-sibling::XCUIElementTypeStaticText[1]");
	UIElement curbsidePickUp = UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeButton[contains(@name,'Place Order')]]/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeButton[1]");
	UIElement deliveryCharge = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Delivery')]/following-sibling::XCUIElementTypeStaticText[1]");

	UIElement storeAddress = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[1]//XCUIElementTypeStaticText[1]");

	UIElement promoCodeAlert = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Promo Code']");
	UIElement promoCodeMessage = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Promo Code']/following-sibling::XCUIElementTypeStaticText");

	public String getAddedProductUI() throws Throwable {
		return UIElement.byXpath("//XCUIElementTypeStaticText[@name='" + Properties.getVariable("productName") + "']").getText();
	}

	DecimalFormat df = new DecimalFormat("#.##");

	public Cart() {
		// TODO Auto-generated constructor stub
	}

	@Given("I attempt to place an order")
	public void placeOrder() throws Throwable {
		//footerTabsScreen.btnCart.waitFor(3).tap();
		if (placeOrder.isDisplayed()){
			placeOrder.tap();
			MobileDevice.getScreenshot(true);
			MobileDevice.getSource(true);
		}
		else {
			placeOrder.scrollTo(SwipeDirection.UP);
			placeOrder.tap();
			MobileDevice.getScreenshot(true);
			MobileDevice.getSource(true);
		}
		placeOrder.waitForNot(10);
	}

	@And("^I should see credit info on cart screen$")
	public void iShouldSeeCreditInfoOnCartScreen() throws Throwable {
		creditCardCell.waitFor(10).tap();
		paymentInfoScreen.iShouldSeeCreditInfoOnPaymentInfoScreen();
		footerTabsScreen.tapMyAccount();
	}

	@And("^I should see loyalty card info on cart screen$")
	public void iShouldSeeLoyaltyCardInfoOnCartScreen() throws Throwable {
//		extracareCardCell.waitFor(5).tap();
//
//		Assert.assertTrue(UIElement.byName("Loyalty Cards").isDisplayed());
		//loyalityCardScreen.iShouldSeeExtraCareCardInfoOnLoyaltyCardsScreen();
	}

	@Then("^I saw added product in cart$")
	public void iSawAddedProductInCart() throws Throwable {
		footerTabsScreen.tapCart();
		Assert.assertEquals(getAddedProductUI(), Properties.getVariable("productName"),"Added product not shown in the cart");
	}

    @Given("^My cart is empty$")
    public void myCartIsEmpty() throws Throwable {
		footerTabsScreen.btnCart.waitFor(15).tap();
		footerTabsScreen.tapCart();
		if (selectedStores.waitFor(4).isDisplayed()) {
			MobileDevice.getScreenshot(true);
			MobileDevice.getSource(true);
			int totalStores = selectedStores.getCount();
			for (int i = 0; i < totalStores; i++) {
				selectedStores.tap();
				MobileDevice.getScreenshot(true);
				MobileDevice.getSource(true);
				if (creditCardCell.isDisplayed()) {
					int totalItems = productItem.getCount();
					if (totalItems > 3) {
						for (int j = 0; j < totalItems - 3; j++) {
							productaQuantityButton.scrollTo(SwipeDirection.UP).tap();
							UIElement.byName("Remove").tap();
							UIElement.byName("Remove").waitForNot(8);
							MobileDevice.getScreenshot(true);
							MobileDevice.getSource(true);
						}
					} else {
						for (int j = 0; j < totalItems - 2; j++) {
							productaQuantityButton.scrollTo(SwipeDirection.UP).tap();
							UIElement.byName("Remove").tap();
							UIElement.byName("Remove").waitForNot(8);
							MobileDevice.getScreenshot(true);
							MobileDevice.getSource(true);
						}
					}
				}
				else {
					int totalSelectedSubstores = substores.getCount();
					if (totalSelectedSubstores > 4) {
						MobileDevice.getScreenshot(true);
						MobileDevice.getSource(true);
						String address = storeAddress.getText();
						for (int j = 0; j < totalSelectedSubstores - 4; j++) {
							Properties.setVariable("storeaddress",address);
							lastProduct.tap();
							int totalItems = productItem.getCount();
							for (int k = 0; k < totalItems - 3; k++) {
								productaQuantityButton.scrollTo(SwipeDirection.UP).tap();
								UIElement.byName("Remove").tap();
								UIElement.byName("Remove").waitForNot(8);
							}
							if (j< totalSelectedSubstores - 5) {
								String storeAddressCart = Properties.getVariable("storeaddress");
								UIElement.byXpath("//XCUIElementTypeCell//XCUIElementTypeStaticText[contains(@name,'"+storeAddressCart+"')]").waitFor(3).tap();
							}

						}
					}
					else {
						lastProduct.tap();
						int totalItems = productItem.getCount();
						for (int k = 0; k < totalItems - 3; k++) {
							productaQuantityButton.scrollTo(SwipeDirection.UP).tap();
							UIElement.byName("Remove").tap();
							UIElement.byName("Remove").waitForNot(8);
						}
					}
				}
			}
		}
	}

	@Then("^I should see checkout not allowed$")
	public void iShouldSeeCheckoutNotAllowed() throws Throwable {
		Assert.assertEquals(popUpHeading.getText(),"Please Turn on the Following in Settings");
		MobileDevice.getScreenshot(true);
		settings.tap();
	//	UIElement.byName("Notifications").waitFor(5).tap();
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

	public double calculateProductTotalItemPrice() throws Throwable {
		int totalItems = productItem.getCount();
        double totalPrice = 0;
		for (int k = 0; k < totalItems-3; k++) {
			String singleItemPrice = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'item')]]/following-sibling::XCUIElementTypeCell[" + (k+1) + "]//XCUIElementTypeStaticText[3]").getText();
			Double itemPrice = Double.parseDouble(singleItemPrice.split("\\$")[1]);
            totalPrice = totalPrice + itemPrice;
		}
		return Double.valueOf(df.format(totalPrice));
	}

	public void calculateOrignalItemPrice() throws Throwable {
		int totalItems = productItem.getCount();
		String singleItemPrice;
		//for (int k = 1; k < totalItems-4; k++)
		for (int k = totalItems-4; k > 1; k--){
			singleItemPrice = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'item')]]/following-sibling::XCUIElementTypeCell[" + (k+1) + "]//XCUIElementTypeStaticText[2]").getText();
			if(singleItemPrice.equalsIgnoreCase("SAVE"))
			{
				singleItemPrice = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'item')]]/following-sibling::XCUIElementTypeCell[" + (k+1) + "]//XCUIElementTypeStaticText[3]").getText();
			}
			Properties.setVariable("cart"+k, singleItemPrice.split("\\$")[1]);
		}
	}

	@Then("^I should see added product total amount$")
	public void iShouldSeeAddedProductTotalAmount() throws Throwable {
		Double totalPrice = Double.parseDouble(itemsTotalPrice.getText().split("\\$")[1]);
		Assert.assertEquals(totalPrice, calculateProductTotalItemPrice(),"Total amount of the items in the store is not same");
	}

    @And("^I apply '(.*)' promo code$")
    public void iApplyPromoCode(String promo) throws Throwable {
		Properties.setVariable("promoCode",promo);
        promoCode.sendKeys(promo,false);
        UIElement.byName("Apply").tap();
    }

	@When("^I verify discount is applied$")
	public void iVerifyDiscountIsApplied() throws Throwable {
		promoCodeDiscount.waitFor(15);
		Double actualDiscount = Double.parseDouble(promoCodeDiscount.getText().split("\\$")[1]);
		Double totalPrice = Double.parseDouble(itemsTotalPrice.getText().split("\\$")[1]);
		Double estimateTax = Double.parseDouble(estimatedTax.getText().split("\\$")[1]);
		Double estimateTotalAmount = Double.parseDouble(estimatedTotal.getText().split("\\$")[1]);

		Assert.assertEquals(Double.valueOf(df.format((totalPrice + estimateTax) - actualDiscount)), estimateTotalAmount,"Discount is not applied");
	//	UIElement.byName("Back").tap();
	}

    @Given("^I tap on '(.*)'$")
    public void iTapOn(String code) throws Throwable {

		if (UIElement.byName(code).isDisplayed()){
			UIElement.byName(code).tap();
			MobileDevice.getSource(true);
		}
		else{
			UIElement.byName(code).scrollTo(SwipeDirection.UP).tap();
			MobileDevice.getSource(true);
		}

		//UIElement.byName(code).scrollTo().tap();
    }

	@Then("^I should see '(.*)' dollar in the cart$")
	public void iShouldSeeDollarInTheCart(String amount) throws Throwable {
		//Assert.assertEquals(Properties.getVariable("product1"),amount,"Pricing value of particular product is not matched");
		String singleItemPrice = UIElement.byXpath("//XCUIElementTypeCell//XCUIElementTypeStaticText[@name='" + Properties.getVariable("productName1") + "']/following-sibling::XCUIElementTypeStaticText[contains(@name,'ea')]").scrollTo(SwipeDirection.UP).getText();
		String addedItemPrice = singleItemPrice.split("\\$")[1].split("\\s")[0];
		Assert.assertEquals(Properties.getVariable("product1"),addedItemPrice,"Pricing value of particular product is not matched");


	}

	@And("^I select Curbside Pickup and delivery option$")
	public void iSelectCurbsidePickupAndDeliveryOption() throws Throwable {
		curbsidePickUp.tap();
		getMyOrderScreen.deliveryBy.tap();
		MobileDevice.getSource(true);
	}

	@Then("^I should see delivery promo code is applied and discount is given as per '(.*)'")
	public void iShouldSeeDeliveryPromoCodeIsApplied(String discountType) throws Throwable {
		itemsTotalPrice.waitFor(25);
		Double expectedDiscount = 0.00;
		Double deliveryCharges = 0.00;
		Double actualDeliveryCharges ;
		if(Properties.getVariable("Delivery Charges") != null) {
			actualDeliveryCharges = Double.parseDouble(Properties.getVariable("Delivery Charges").split("\\$")[1]);
		}
		else
			actualDeliveryCharges = 0.00;

		itemsTotalPrice.scrollTo(SwipeDirection.UP);

		Double totalItemPrice = Double.parseDouble(itemsTotalPrice.getText().split("\\$")[1]);
		Double estimatedTaxPrice = Double.parseDouble(estimatedTax.getText().split("\\$")[1]);
		Double actualEstimatedTotal = Double.parseDouble(estimatedTotal.getText().split("\\$")[1]);
		deliveryCharges = Double.parseDouble(deliveryCharge.getText().split("\\$")[1]);
		Double expectedDeliveryCharges =0.00;

		switch (discountType.toLowerCase()){
			case "dollar":
				expectedDiscount =  5.00;
				expectedDiscount =  Double.valueOf(df.format(actualDeliveryCharges - expectedDiscount)) ;
			//	actualDeliveryCharges = Double.parseDouble(Properties.getVariable("Delivery Charges").split("\\$")[1]);
			//	expectedDiscount =  Double.valueOf(df.format(actualDeliveryCharges * 0.05)) ;
				Assert.assertEquals(expectedDiscount, deliveryCharges,
						"NF_DOLLAR_DS promo code is not applied to the delivery charges");
				break;
			case "deliverypercent":
				expectedDiscount =  Double.valueOf(df.format(actualDeliveryCharges * 0.10)) ;
				expectedDeliveryCharges = Double.valueOf(df.format(actualDeliveryCharges - expectedDiscount));
			//	actualDeliveryCharges = Double.parseDouble(Properties.getVariable("Delivery Charges").split("\\$")[1]);
				expectedDiscount =  Double.valueOf(df.format(actualDeliveryCharges * 0.946)) ;
				Assert.assertEquals(expectedDeliveryCharges, deliveryCharges,
						"NF_PERCENT_DS promo code is not applied to the delivery charges");
				break;
			case "free":
				expectedDiscount = actualDeliveryCharges;
				expectedDeliveryCharges = Double.valueOf(df.format(actualDeliveryCharges - expectedDiscount));
				Assert.assertEquals(expectedDeliveryCharges, deliveryCharges,
						"NF_FREE_DS promo code is not applied to the delivery charges");
				break;
			case "fixed":
//				actualDeliveryCharges = Double.parseDouble(Properties.getVariable("Delivery Charges").split("\\$")[1]);
//				expectedDiscount =  Double.valueOf(df.format(actualDeliveryCharges * 0.003)) ;
				expectedDeliveryCharges = 0.01;
				Assert.assertEquals(expectedDeliveryCharges, deliveryCharges,
						"NF_FIXED_DS promo code is not applied to the delivery charges");
				break;
			case "firsttime":
				break;
			default: Assert.fail(discountType.toUpperCase() + " not a discount type");
				break;
		}



		Double expectedEstimatedTotal = (totalItemPrice + estimatedTaxPrice + deliveryCharges);
		expectedEstimatedTotal = Double.valueOf(df.format(expectedEstimatedTotal));
		Assert.assertEquals(actualEstimatedTotal, expectedEstimatedTotal, "Promo code discount calculation is not correct");
	}

	@Then("^I should see repeat promo code is applied and discount is given as per '(.*)'")
	public void iShouldSeeRepeatPromoCodeIsApplied(String discountType) throws Throwable {
		itemsTotalPrice.waitFor(25);
		DecimalFormat df = new DecimalFormat("#.##");
		Double expectedDiscount = 0.00;
		Double deliveryCharges = 0.00;
		Double actualDeliveryCharges = 0.00;
		Double actualDiscount = 0.00;

		itemsTotalPrice.scrollTo(SwipeDirection.UP);
		actualDiscount = Double.parseDouble(promoCodeDiscount.getText().split("\\$")[1]);
		Double totalItemPrice = Double.parseDouble(itemsTotalPrice.getText().split("\\$")[1]);
		Double estimatedTaxPrice = Double.parseDouble(estimatedTax.getText().split("\\$")[1]);
		Double actualEstimatedTotal = Double.parseDouble(estimatedTotal.getText().split("\\$")[1]);

		if(deliveryCharge.isDisplayed()) {
			deliveryCharges = Double.parseDouble(deliveryCharge.getText().split("\\$")[1]);
		}

		switch (discountType.toLowerCase()){
			case "unlimited":
				expectedDiscount = 10.00;
				Assert.assertEquals(expectedDiscount, actualDiscount,
						"UNLIMITED promo code is not having $10 discount");
				break;
			case "percent":
				expectedDiscount = Double.valueOf(df.format(totalItemPrice * 0.20)) ;
				Assert.assertEquals(expectedDiscount, actualDiscount,
						"Percent promo code is not having exact discount");
				break;
			case "firsttime":
				break;
			default: Assert.fail(discountType.toUpperCase() + " not a discount type");
				break;
		}

		Double expectedEstimatedTotal = (totalItemPrice + estimatedTaxPrice + deliveryCharges) - expectedDiscount;
		expectedEstimatedTotal = Double.valueOf(df.format(expectedEstimatedTotal));
		Assert.assertEquals(actualEstimatedTotal, expectedEstimatedTotal, "Promo code discount calculation is not correct");
	}

	@And("^I store the value of '(.*)'$")
	public void iStoreTheValueOf(String value) throws Throwable {
		deliveryCharge.waitFor(10).scrollTo();

		Properties.setVariable(value, deliveryCharge.getText());
	}

	@And("^I checked threshold value for Promo code$")
	public void iCheckedThresholdValueForPromoCode() throws Throwable {
		while (true){
			if(promoCodeAlert.isDisplayed()){
				String message = promoCodeMessage.getText();

				Double amountToBeAdded = Double.parseDouble(message.split("\\$")[1].split("\\s")[0]);
				amountToBeAdded = Double.valueOf(df.format(amountToBeAdded));
				Steps.tapButton("OK");


				if (amountToBeAdded <= 20.00){
					productaQuantityButton.scrollTo(SwipeDirection.UP).tap();
					productDetailsScreen.incrementProduct.tap();
					MobileDevice.tap(188,42);
				}
				else {
					break;
				}
				
				iTapOn("Enter Promo Code");
				iApplyPromoCode(Properties.getVariable("promoCode"));
			}
			else {
				break;
			}
		}
	}

	@And("^I go to the shop screen to add any product$")
	public void iGoToTheShopScreenToAddAnyProduct() throws Throwable {
		Steps.tapButton("Done");
		footerTabsScreen.tapShop();
	}
}
