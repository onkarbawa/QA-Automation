package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;

/**
 * Created by bawa.onkar on 07/07/17.
 */
public class CreditCard extends AbstractScreen {

	public String cardExpiryValue;

	UIElement firstName = new UIElement(By.xpath("//XCUIElementTypeCell[1]/XCUIElementTypeTextField"));
	UIElement lastName = new UIElement(By.xpath("//XCUIElementTypeCell[2]/XCUIElementTypeTextField"));
	UIElement cardNumber = new UIElement(By.xpath("//XCUIElementTypeCell[3]/XCUIElementTypeTextField"));
	UIElement expiryDate = new UIElement(By.xpath("//XCUIElementTypeCell[4]/XCUIElementTypeTextField"));
	UIElement securityCode = new UIElement(By.xpath("//XCUIElementTypeCell[5]/XCUIElementTypeTextField"));

	UIElement next = new UIElement(By.name("Next"));

	UIElement newAddress = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='New Address']"));

	UIElement cardAdd1 = new UIElement(By.xpath("//XCUIElementTypeCell[1]/XCUIElementTypeTextField"));
	UIElement cardAdd2 = new UIElement(By.xpath("//XCUIElementTypeCell[2]/XCUIElementTypeTextField"));
	UIElement cardCity = new UIElement(By.xpath("//XCUIElementTypeCell[3]/XCUIElementTypeTextField"));
	UIElement cardState = new UIElement(By.xpath("//XCUIElementTypeCell[4]/XCUIElementTypeTextField"));
	UIElement cardZip = new UIElement(By.xpath("//XCUIElementTypeCell[5]/XCUIElementTypeTextField"));

	UIElement save = new UIElement(By.name("Save"));

	public CreditCard() {
		// TODO Auto-generated constructor stub
	}

	@And("^I add credit card information as '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)'$")
	public void iAddCreditCardInformationAs(String firstNameText, String lastNameText, String cardNumberText,
			String cardExpiry, String cardCvv, String add1, String add2, String city, String state, String zip)
			throws Throwable {
		Properties.setVariable("cardExpiryDate", cardExpiry);
		Properties.setVariable("creditCardNumber", cardNumberText);

		firstName.sendKeys(firstNameText,false);
		lastName.sendKeys(lastNameText,false);
		cardNumber.sendKeys(cardNumberText,false);
		expiryDate.sendKeys(cardExpiry,false);
		securityCode.sendKeys(cardCvv,false);
		MobileDevice.getScreenshot(true);
		next.tap();
		newAddress.tap();
		cardAdd1.sendKeys(add1,false);
		cardAdd2.sendKeys(add2,false);
		cardCity.sendKeys(city,false);
		cardState.tap();
		cardState.tap();
		cardZip.sendKeys(zip,false);
		MobileDevice.getScreenshot(true);
		save.tap();
		
		save.waitForNot(20);
		MobileDevice.getScreenshot(true);
	}

	@And("^I add credit card information$")
	public void iAddCreditCardInformation() throws Throwable {
		myAccountScreen.btnPaymentInfo.waitFor(10).tap();
		//paymentInfoScreen.iTapOnAddANewCard();
		Steps.tapButton("Add New Card");
		firstName.sendKeys("Test",false);
		lastName.sendKeys("Data",false);
		cardNumber.sendKeys("6011111111111117",false);
		expiryDate.sendKeys("1120",false);
		securityCode.sendKeys("345",false);
		MobileDevice.getScreenshot(true);
		next.tap();

		newAddress.tap();
		cardAdd1.sendKeys("TestData",false);
		cardAdd2.sendKeys("TestData",false);
		cardCity.sendKeys("Boston",false);
		cardState.tap();
		cardState.tap();
		cardZip.sendKeys("56635",false);
		MobileDevice.getScreenshot(true);
		save.tap();

		save.waitForNot(20);
		MobileDevice.getScreenshot(true);
	}
}
