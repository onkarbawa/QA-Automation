package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

/**
 * Created by bawa.onkar on 7/3/17.
 */
public class Login {

    String enteredEmail;
    static UIElement email = new UIElement(By.xpath("//XCUIElementTypeTextField[1]"));
    static UIElement password = new UIElement(By.xpath("//XCUIElementTypeSecureTextField[1]"));


    @And("^I enter '(.*)' and '(.*)'$")
    public void iEnterEmailAndPassword(String emailText, String passwordText) throws Throwable {
        enteredEmail = emailText;
        email.enterText(emailText);
        password.enterText(passwordText);
    }
}
