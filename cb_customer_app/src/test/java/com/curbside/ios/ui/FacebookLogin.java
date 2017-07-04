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
public class FacebookLogin {
    static UIElement enterFacebookEmail= new UIElement(By.xpath("//XCUIElementTypeTextField[@name='username-field']"));
    static UIElement enterPassword= new UIElement(By.xpath("//XCUIElementTypeSecureTextField[@name='password-field']"));
    static UIElement loginButton= new UIElement(By.xpath("//XCUIElementTypeButton[@name='login-button']"));

    @And("^I enter email and password$")
    public void iEnterEmailAndPassword() throws Throwable {
       enterFacebookEmail.enterText("jacktest94@gmail.com");
       enterPassword.enterText("tftus@123");
    }
}
