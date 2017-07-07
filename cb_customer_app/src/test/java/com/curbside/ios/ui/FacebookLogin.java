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
    static UIElement facebookapp = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Log In with the Facebook App']"));
    static UIElement signWithFacebook = new UIElement(By.xpath("//XCUIElementTypeOther/XCUIElementTypeButton[4]']"));
    static UIElement enterFacebookEmail= new UIElement(By.xpath("//XCUIElementTypeTextField[@name='username-field']"));
    static UIElement enterPassword= new UIElement(By.xpath("//XCUIElementTypeSecureTextField[@name='password-field']"));
    static UIElement loginButton= new UIElement(By.xpath("//XCUIElementTypeButton[@name='login-button']"));

    @And("^I enter email and password$")
    public void iEnterEmailAndPassword() throws Throwable {
       enterFacebookEmail.enterText("jacktest94@gmail.com");
       enterPassword.enterText("tftus@123");
    }

    @And("^I enter '(.*)' and '(.*)' for facebook$")
    public void iEnterAndForFacebook(String emailText, String passwordText) throws Throwable {
        enterFacebookEmail.enterText(emailText);
        enterPassword.enterText(passwordText);
    }

    @And("^I tap on 'Log In with the Facebook App'$")
    public void iTapOnLogInWithTheFacebookApp() throws Throwable {
        facebookapp.waitFor(25);
        facebookapp.tap();
    }

    @And("^I tap on 'Sign In with Facebook'$")
    public void iTapOnSignInWithFacebook() throws Throwable {
        signWithFacebook.tap();
    }
}
