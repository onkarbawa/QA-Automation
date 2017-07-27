package com.curbside.android.ui;

import org.openqa.selenium.interactions.touch.TouchActions;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.Helpers;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by hitesh.grover on 11/07/17.
 */
public class SignUp extends AbstractScreen {

    static UIElement emailField = UIElement.byId("com.curbside.nCurbside:id/edit_email_sign_up");
    static UIElement phoneNumberField = UIElement.byId("com.curbside.nCurbside:id/edit_phone_number_sign_up");
    static UIElement passwordField = UIElement.byId("com.curbside.nCurbside:id/edit_password_sign_up");
    static UIElement createAccountButton = UIElement.byId("com.curbside.nCurbside:id/button_create_account_sign_up");

    @And("^I enter fresh details of EmailID, PhoneNumber and Password on Signup screen$")
    public void iEnterEmailPhoneNumberAndPasswordForSignup() throws Throwable {
        String emailId = Helpers.getRandomEmailId();
        String password = "fusic@123";
        String phoneNumberText = "1345678901";

        Properties.setVariable("signupEmail", emailId);
        Properties.setVariable("signupPassword", password);
        Properties.setVariable("signupPhoneNumber", phoneNumberText);
        
        emailField.waitFor(3);
        /*
        new TouchActions(DriverFactory.getDriver())
	        .sendKeys(emailField.getElement(), emailId)
	        .sendKeys(phoneNumberField.getElement(), phoneNumberText)
	        .sendKeys(passwordField.getElement(), password)
	        .build().perform();
        */
        
        emailField.sendKeys(emailId, false);
        phoneNumberField.sendKeys(phoneNumberText, false);
        passwordField.sendKeys(password, false);
        
    }

    @And("^I tap on Create Account button$")
    public void itapOnCreateAccountButton() throws Throwable {
        createAccountButton.waitFor(3);
        createAccountButton.tap();
    }

}
