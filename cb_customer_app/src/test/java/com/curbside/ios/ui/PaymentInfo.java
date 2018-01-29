package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;

/**
 * Created by bawa.onkar on 06/07/17.
 */
public class PaymentInfo extends AbstractScreen {

	UIElement paymentInfoTitle = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Payment Info']"));
	UIElement btnAddNewCard = UIElement.byName("Add New Card");
	UIElement creditCardExpiry = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Expires')]");
	UIElement creditCardCell = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[1]");
	UIElement btnDelete = UIElement.byName("Delete");

	public PaymentInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getCardExpiryValue() {
		String cardExpiryDate = Properties.getVariable("cardExpiryDate");
		String value = cardExpiryDate.substring(0, 2) + "/" + cardExpiryDate.substring(2, 4);
		return value;

	}

//	public String getUICreditExpiryValue() throws Throwable {
//		return new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Expires " + getCardExpiryValue() + "']")).getText();
//
//	}

	@And("^I tap on 'Add a new card' buton$")
	public void iTapOnAddANewCard() throws Throwable {
		MobileDevice.tap(162, 354);
	}

	@And("^I should see credit info on payment info screen$")
	public void iShouldSeeCreditInfoOnPaymentInfoScreen() throws Throwable {
		Assert.assertEquals(creditCardExpiry.getText(), "Expires " + getCardExpiryValue(),
				"Credit Card information is not shown in payment info screen");
	}

    @Then("^I removed added '(.*)' from '(.*)' Screen$")
    public void iRemovedAddedFrom(String card, String screen) throws Throwable {
        footerTabsScreen.btnMyAccount.tap();
        if (card.equalsIgnoreCase("Credit Card")){
			myAccountScreen.btnPaymentInfo.tap();
		}else {
			myAccountScreen.btnLoyalityCard.tap();
		}
		int height = creditCardCell.getHeight();
		int width = creditCardCell.getWidth();
		int x = creditCardCell.getX();
		int y = creditCardCell.getY();

		new TouchAction((PerformsTouchActions) DriverFactory.getDriver()).press(x+width, y+(height/2)).
				waitAction(Duration.ofSeconds(3)).moveTo(-width, y+(height/2)).release().perform();
//		MobileDevice.swipe((x+(width/2)),y+(height/2),x,y+(height/2));
		MobileDevice.getScreenshot(true);
		btnDelete.waitFor(10);
		if(btnDelete.isDisplayed()){
			btnDelete.tap();
		}
		btnAddNewCard.waitFor(10);
		Assert.assertTrue(btnAddNewCard.isDisplayed(),"Card is not deleted");
    }
}
