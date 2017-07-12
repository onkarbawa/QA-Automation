package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 12/07/17.
 */
public class LoyaltyCard extends AbstractScreen {

    static UIElement loyaltyCardText = UIElement.byId("com.curbside.nCurbside:id/edit_card_number");
    static UIElement saveButton = UIElement.byId("com.curbside.nCurbside:id/action_save");
    static UIElement firstLoyaltyCardNumber = UIElement.byId("com.curbside.nCurbside:id/last_4_view");

    @And("^I add an ExtraCare Card numbered '(.*)'$")
    public void iAddAnExtraCareCardNumbered(String cardNumber) throws Throwable {
        Properties.setVariable("extraCareCardNumber", cardNumber);
        Steps.tapButton("ExtraCare Card");
        loyaltyCardText.sendKeys(cardNumber);
        saveButton.tap();
        saveButton.waitForNot(10);
    }

    @And("^I should see ExtraCare Card info on Loyalty Cards screen$")
    public void iShouldSeeExtraCareCardInfoOnLoyaltyCardsScreen() throws Throwable {
        String displayedCardInfo = firstLoyaltyCardNumber.getText();
        String last4Chars = StringUtils.right(Properties.getVariable("extraCareCardNumber"), 4);
        Assert.assertEquals(displayedCardInfo, "ExtraCare Card (..." + last4Chars + ")");
    }

}
