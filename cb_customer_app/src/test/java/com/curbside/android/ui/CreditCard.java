package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
/**
 * Created by hitesh.grover on 03/07/17.
 */
public class CreditCard {

    UIElement cardNumberTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_card_number"));
    UIElement cardExpMonthTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_expiration"));
    UIElement cardCCVTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_ccv"));
    UIElement firstNameTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_first_name"));
    UIElement lastNameTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_last_name"));
    UIElement cardStreetAddressTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_street"));
    UIElement cardAptOrSuiteTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_apt"));
    UIElement cardCityTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_city"));
    UIElement cardStateDropDown = new UIElement(By.id("com.curbside.nCurbside:id/edit_state"));
    UIElement cardZipTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_zip"));
    UIElement saveButton =  UIElement.byId("com.curbside.nCurbside:id/action_save");


    @And("^I add credit card information as '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)', '(.*)','(.*)', '(.*)', '(.*)'$")
    public void iAddCreditCardInformationAs(String cardNumber, String cardExpiry, String ccv, String firstName,
                                            String lastName, String cardStreetAddress, String cardAptOrSuite, String cardCity,
                                            String cardState, String cardZip) throws Throwable {
        cardNumberTextField.setText(cardNumber);
        cardExpMonthTextField.setText(cardExpiry);
        cardCCVTextField.setText(ccv);
        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);
        cardStreetAddressTextField.setText(cardStreetAddress);
        cardAptOrSuiteTextField.setText(cardAptOrSuite);
        cardCityTextField.setText(cardCity);
        cardStateDropDown.tap();
        Thread.sleep(1000);
        UIElement cardStateListSelect = UIElement.byXpath("//android.widget.FrameLayout/android.widget.ListView//android.widget.CheckedTextView[@text='" + cardState + "']");
        cardStateListSelect.tap();
        cardZipTextField.setText(cardZip);
        saveButton.tap();
    }

}
