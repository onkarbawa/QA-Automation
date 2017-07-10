package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 06/07/17.
 */
public class PaymentInfo extends AbstractScreen {

	UIElement paymentInfoTitle = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Payment Info']"));

	public PaymentInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getCardExpiryValue() {
		String cardExpiryDate = Properties.getVariable("cardExpiryDate");
		String value = cardExpiryDate.substring(0, 2) + "/" + cardExpiryDate.substring(2, 4);
		return value;

	}

	public String getUICreditExpiryValue() throws Throwable {
		return new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Expires " + getCardExpiryValue() + "']"))
				.getText();

	}

	@And("^I tap on 'Add a new card'$")
	public void iTapOnAddANewCard() throws Throwable {
		MobileDevice.tap(162, 354);
	}

	@And("^I should see credit info on payment info screen$")
	public void iShouldSeeCreditInfoOnPaymentInfoScreen() throws Throwable {
		paymentInfoTitle.waitFor(7);
		Assert.assertEquals(getUICreditExpiryValue(), "Expires " + getCardExpiryValue(),
				"Credit Card information is not shown in payment info screen");
	}
}
