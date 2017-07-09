package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 07/07/17.
 */
public class LoyalityCard extends AbstractScreen {

	UIElement cardNumberTextBox = new UIElement(By.xpath("//XCUIElementTypeTextField"));
	UIElement save = new UIElement(By.name("Save"));
	UIElement lblCreditCardNumber = new UIElement(By.xpath("//XCUIElementTypeStaticText[contains(@name, 'DISCO ')]"));
	UIElement lblLoyalityCard = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'ExtraCare Card ')]");
	UIElement paymentInfoTitle = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Add ExtraCare Card']"));

	public LoyalityCard() {
		// TODO Auto-generated constructor stub
	}

	@And("^I add an ExtraCare Card numbered '(.*)'$")
	public void iAddAnExtraCareCardNumbered(String cardNumber) throws Throwable {
		Properties.setVariable("extraCareCardNumber", cardNumber);
		Steps.tapButton("ExtraCare Card");
		cardNumberTextBox.sendKeys(cardNumber);
		save.tap();
		save.waitForNot(10);
	}

	public String getCardNumber() {
		String cardNumber = Properties.getVariable("loyalityCardNumber");
		return "ExtraCare Card (..." + cardNumber.substring(8, 12) + ")";
	}

	public String getUICardNumber() throws Throwable {
		return new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='ExtraCare Card " + getCardNumber() + "']"))
				.getText();

	}

	@And("^I should see ExtraCare Card info on Loyalty Cards screen$")
	public void iShouldSeeExtraCareCardInfoOnLoyaltyCardsScreen() throws Throwable {
		String displayedCardInfo = lblLoyalityCard.getText();
		String last4Chars = StringUtils.right(Properties.getVariable("extraCareCardNumber"), 4);
		Assert.assertEquals(displayedCardInfo, "ExtraCare Card (..." + last4Chars + ")");
	}
}
