package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 12/07/17.
 */
public class LoyaltyCard extends AbstractScreen {

    UIElement loyaltyCardText = UIElement.byId("com.curbside.nCurbside:id/edit_card_number");
    UIElement saveButton = UIElement.byId("com.curbside.nCurbside:id/action_save");
    UIElement firstLoyaltyCardNumber = UIElement.byId("com.curbside.nCurbside:id/last_4_view");
    UIElement btnAddNewCard = UIElement.byId("com.curbside.nCurbside:id/button_add_card");
    UIElement lblNoLoyaltyCards = UIElement.byId("com.curbside.nCurbside:id/text_empty_message");


    @And("^I add an ExtraCare Card numbered '(.*)'$")
    public void iAddAnExtraCareCardNumbered(String cardNumber) throws Throwable {
        Properties.setVariable("extraCareCardNumber", cardNumber);
        loyaltyCardText.sendKeys(cardNumber);
        saveButton.tap();
        saveButton.waitForNot(10);
        MobileDevice.getScreenshot(true);
    }

    @And("^I should see ExtraCare Card info on Loyalty Cards screen$")
    public void iShouldSeeExtraCareCardInfoOnLoyaltyCardsScreen() throws Throwable {
        String displayedCardInfo = firstLoyaltyCardNumber.getText();
        String last4Chars = StringUtils.right(Properties.getVariable("extraCareCardNumber"), 4);
        Assert.assertEquals(displayedCardInfo, "•••• " + last4Chars + "");
    }

    @Then("^I should see empty Loyalty Cards screen$")
    public void iShouldSeeEmptyLoyaltyCardScreen() throws Throwable {
        lblNoLoyaltyCards.waitFor(15);
        Assert.assertTrue(lblNoLoyaltyCards.isDisplayed(), "Not able to remove the Loyalty card");
    }
}
