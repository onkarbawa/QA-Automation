package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 03/07/17.
 */
public class PaymentInfo {

    static UIElement btnAddNewCard = new UIElement(By.id("com.curbside.nCurbside:id/button_add_card"));
    static UIElement firstCard = UIElement.byXpath("//android.widget.ListView/android.widget.RelativeLayout[@index='0']");
    UIElement btnRemoveCardYes = UIElement.byId("com.curbside.nCurbside:id/button_save");
    UIElement lblNoCreditCards = UIElement.byId("com.curbside.nCurbside:id/text_empty_message");


    @And("^I tap on Add New Card button on Payment info page$")
    public void iTapOnAddNewCardButtonOnPayementInfoPage() throws Throwable {
        btnAddNewCard.waitFor(3).tap();
    }

    @Then("^I should see the card added to Payment info$")
    public void iShouldSeeTheCardAddedToPaymentInfo() throws Throwable {
        Assert.assertTrue(firstCard.waitFor(30).isDisplayed(), "Credit card is not added yet");
    }

    @Then("^I should see empty Payment Info screen$")
    public void iShouldSeeEmptyPaymentInfoScreen() throws Throwable {
        btnRemoveCardYes.waitFor(2).tap();
        lblNoCreditCards.waitFor(15);
        Assert.assertTrue(lblNoCreditCards.isDisplayed(), "Not able to remove the Credit card");
    }
}
