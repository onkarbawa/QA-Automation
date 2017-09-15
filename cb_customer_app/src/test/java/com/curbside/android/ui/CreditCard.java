package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.Helpers;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
/**
 * Created by hitesh.grover on 03/07/17.
 */
public class CreditCard extends AbstractScreen {

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
        Properties.setVariable("creditCardNumber", cardNumber);
        cardNumberTextField.sendKeys(cardNumber);
        cardExpMonthTextField.sendKeys(cardExpiry);
        cardCCVTextField.sendKeys(ccv);
        firstNameTextField.sendKeys(firstName);
        lastNameTextField.sendKeys(lastName);
        cardStreetAddressTextField.sendKeys(cardStreetAddress);
        cardAptOrSuiteTextField.sendKeys(cardAptOrSuite);
        cardCityTextField.sendKeys(cardCity);
        cardStateDropDown.tap();
        Thread.sleep(1000);
        UIElement cardStateListSelect = UIElement.byXpath("//android.widget.FrameLayout/android.widget.ListView//android.widget.CheckedTextView[@text='" + cardState + "']");
        cardStateListSelect.tap();
        cardZipTextField.sendKeys(cardZip);
        saveButton.tap();
    }

    @Given("^I add credit card information$")
    public void iAddedCreditCardInformations() throws Throwable {
        String firstName = Helpers.getRandomFirstName();
        String lastName = Helpers.getRandomLastName();
        Properties.setVariable("firstNameCredit", firstName);
        Properties.setVariable("lastNameCredit", lastName);

        footerTabsScreen.tapMyAccount();
        accountScreen.ensureAccountPage();
        accountScreen.iTapOnPaymentInfoButtonOnAccountPage();
        paymentInfo.iTapOnAddNewCardButtonOnPayementInfoPage();
        iAddCreditCardInformationAs("4012000077777777","1218","123",firstName,
                lastName,"1 Infinite Loop","","Cupertino",
                "California","95014");
        paymentInfo.iShouldSeeTheCardAddedToPaymentInfo();
    }
}
