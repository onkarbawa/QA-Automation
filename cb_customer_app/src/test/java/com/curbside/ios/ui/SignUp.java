package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by bawa.onkar on 7/3/17.
 */
public class SignUp {

    String randomMail = "test" + System.currentTimeMillis() + "@example.com";

    static UIElement email = new UIElement(By.xpath("//XCUIElementTypeTextField[1]"));
    static UIElement password = new UIElement(By.xpath("//XCUIElementTypeSecureTextField[1]"));
    static UIElement phoneNumber= new UIElement(By.xpath("//XCUIElementTypeTextField[2]"));

    @And("^I enter '(.*)' and '(.*)' and '(.*)'$")
    public void iEnterFor(String emailText, String passwordText, String phoneNumberText) throws Throwable {

    }

    @And("^I enter credentials for signUp$")
    public void iEnterCredentials() throws Throwable {
        email.enterText(randomMail);
        password.enterText("fusic@123");
        phoneNumber.enterText("12345678901");
    }
}
