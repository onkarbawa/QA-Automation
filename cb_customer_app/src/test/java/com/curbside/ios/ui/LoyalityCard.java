package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 07/07/17.
 */
public class LoyalityCard {

    UIElement cardNumberTextBox = new UIElement(By.xpath("//XCUIElementTypeTextField"));
    static UIElement save = new UIElement(By.name("Save"));
    UIElement creditCardNumber = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='DISCO (...1117)']"));
    UIElement paymentInfoTitle = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Add ExtraCare Card']"));
    Steps steps = new Steps();

    @And("^I add an ExtraCare Card numbered '(.*)'$")
    public void iAddAnExtraCareCardNumbered(String cardNumber) throws Throwable {
        Properties.setVariable("loyalityCardNumber",cardNumber);
        steps.tapButton("ExtraCare Card");
        cardNumberTextBox.enterText(cardNumber);
        save.tap();
    }

    public String getCardNumber(){
        String cardNumber = Properties.getVariable("loyalityCardNumber");
        return "ExtraCare Card (..."+cardNumber.substring(8,12)+")";
    }

    public String getUICardNumber() throws Throwable {
        return new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='ExtraCare Card "+getCardNumber()+"']")).getText();

    }

    @And("^I should see ExtraCare Card info on Loyalty Cards screen$")
    public void iShouldSeeExtraCareCardInfoOnLoyaltyCardsScreen() throws Throwable {
        paymentInfoTitle.waitFor(7);
        Assert.assertEquals(getUICardNumber(), getCardNumber(),"Enter credit card no not match");
    }
}
