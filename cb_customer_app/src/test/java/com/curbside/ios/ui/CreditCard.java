package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
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

		firstName.clearText();
		firstName.sendKeys(firstNameText);
		lastName.clearText();
		lastName.sendKeys(lastNameText);
		cardNumber.sendKeys(cardNumberText);
		expiryDate.sendKeys(cardExpiry);
		securityCode.sendKeys(cardCvv);
		next.tap();
		newAddress.tap();
		cardAdd1.sendKeys(add1);
		cardAdd2.sendKeys(add2);
		cardCity.sendKeys(city);
		cardState.tap();
		cardState.tap();
		cardZip.sendKeys(zip);
		save.tap();
	}
}