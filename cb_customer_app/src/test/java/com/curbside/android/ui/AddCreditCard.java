package com.curbside.android.ui;

import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
/**
 * Created by hitesh.grover on 03/07/17.
 */
public class AddCreditCard {

    static UIElement cardNumberTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_card_number"));
    static UIElement expMonthTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_expiration"));
    static UIElement ccvTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_ccv"));
    static UIElement firstNameTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_first_name"));
    static UIElement lastNameTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_last_name"));
    static UIElement streetAddTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_street"));
    static UIElement aptTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_apt"));
    static UIElement cityTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_city"));
    static UIElement state = new UIElement(By.id("com.curbside.nCurbside:id/edit_state"));
    static UIElement stateListSelect =new UIElement(By.xpath("//android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[@index='5']"));
    static UIElement zipTextField = new UIElement(By.id("com.curbside.nCurbside:id/edit_zip"));
    static UIElement saveButton = new UIElement(By.id("com.curbside.nCurbside:id/action_save"));

    @And("^I send '(.*)' in 'card number'$")
    public void iSendInCardNumber(String cardNumber) throws Throwable {
        cardNumberTextField.sendKeys(cardNumber);
        AndroidDevice.hitEnter();
    }

    @And("^I send '(.*)' in 'expired month'$")
    public void iSendInExpiredMonth(String expMonth) throws Throwable {
        expMonthTextField.sendKeys(expMonth);
        AndroidDevice.hitEnter();
    }

    @And("^I send '(.*)' in 'ccv'$")
    public void iSendInCcv(String ccv) throws Throwable {
        ccvTextField.sendKeys(ccv);
        AndroidDevice.hitEnter();
    }

    @And("^I send '(.*)' in 'first name'$")
    public void iSendJohnInFirstName(String firstName) throws Throwable {
        firstNameTextField.sendKeys(firstName);
        AndroidDevice.hitEnter();
    }

    @And("^I send '(.*)' in 'last name'$")
    public void iSendMillerInLastName(String lastName) throws Throwable {
        lastNameTextField.sendKeys(lastName);
        AndroidDevice.hitEnter();
    }

    @And("^I send '(.*)' in 'Street address'$")
    public void iSendInfiniteLoopInStreetAddress(String streetAddress) throws Throwable {
        streetAddTextField.sendKeys(streetAddress);
        AndroidDevice.hitEnter();
        AndroidDevice.hitEnter();
    }

    @And("^I send '(.*)' in 'city'$")
    public void iSendCupertinoInCity(String city) throws Throwable {
        cityTextField.sendKeys(city);
        AndroidDevice.goBack();
    }

    @And("^I select state from the list$")
    public void iSelectStateFromTheList() throws Throwable {
        Thread.sleep(1000);
        state.tap();
        Thread.sleep(1000);
        stateListSelect.tap();
    }

    @And("^I send '(.*)' in 'Zip code'$")
    public void iSendInZipCode(String zip) throws Throwable {
        zipTextField.sendKeys(zip);
    }

    @And("^I tap on Save button on the screen$")
    public void iTapOnSaveButtonOnTheScreen() throws Throwable {
        saveButton.tap();
        Thread.sleep(5000);
    }


}
