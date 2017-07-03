package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

/**
 * Created by hitesh.grover on 03/07/17.
 */
public class PaymentInfo {

    static UIElement addNewCard = new UIElement(By.id("com.curbside.nCurbside:id/button_add_card"));


    @And("^I tap on Add New Card button on Payment info page$")
    public void iTapOnAddNewCardButtonOnPayementInfoPage() throws Throwable {
        addNewCard.tap();
    }


    @Then("^I should see the card added to Payment info$")
    public void iShouldSeeTheCardAddedToPaymentInfo() throws Throwable {
        Thread.sleep(15000);

    }
}
