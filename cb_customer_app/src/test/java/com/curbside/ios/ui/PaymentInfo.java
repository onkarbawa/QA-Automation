package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 06/07/17.
 */
public class PaymentInfo extends AbstractScreen {

    UIElement paymentInfoTitle = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Payment Info']"));
    UIElement btnAddNewCard = UIElement.byName("Add New Card");
    UIElement creditCardExpiry = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Expires')]");
    UIElement creditCardCell = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[1]");
    UIElement btnDelete = UIElement.byName("Delete");
    UIElement btnEdit = UIElement.byName("Edit");
    UIElement btnRemove = UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'Delete')]");

    public PaymentInfo() {
        // TODO Auto-generated constructor stub
    }

    public String getCardExpiryValue() {
        String cardExpiryDate = Properties.getVariable("cardExpiryDate");
        String value = cardExpiryDate.substring(0, 2) + "/" + cardExpiryDate.substring(2, 4);
        return value;

    }

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
        if (card.equalsIgnoreCase("Credit Card")) {
            myAccountScreen.btnPaymentInfo.tap();
        } else {
            myAccountScreen.btnLoyalityCard.tap();
        }

        btnEdit.waitFor(4).tap();
        btnRemove.waitFor(5).tap();
        MobileDevice.getScreenshot(true);
        btnDelete.waitFor(10);
        if (btnDelete.isDisplayed()) {
            btnDelete.tap();
        }
        btnAddNewCard.waitFor(10);
        Assert.assertTrue(btnAddNewCard.isDisplayed(), "Card is not deleted");
    }
}
