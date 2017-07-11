package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 03/07/17.
 */
public class PaymentInfo {

    static UIElement addNewCard = new UIElement(By.id("com.curbside.nCurbside:id/button_add_card"));
    static UIElement firstCard = UIElement.byXpath("//android.widget.ListView/android.widget.RelativeLayout[@index='0']");


    @And("^I tap on Add New Card button on Payment info page$")
    public void iTapOnAddNewCardButtonOnPayementInfoPage() throws Throwable {
        addNewCard.tap();
    }


    @Then("^I should see the card added to Payment info$")
    public void iShouldSeeTheCardAddedToPaymentInfo() throws Throwable {
        firstCard.waitFor(20);
        Assert.assertTrue(firstCard.isDisplayed(), "Android : card is not added yet");

    }
}
