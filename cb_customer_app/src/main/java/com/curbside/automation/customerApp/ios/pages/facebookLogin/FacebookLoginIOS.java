package com.curbside.automation.customerApp.ios.pages.facebookLogin;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by bawa.onkar on 6/30/17.
 */
public class FacebookLoginIOS extends Page {

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Log In with the Facebook App']")
    public WebElement loginWithFacebookApp;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Open']")
    public WebElement tapOpen;

    @FindBy(xpath = "//XCUIElementTypeTextField[@name='username-field']")
    public WebElement enterFacebookEmail;

    @FindBy(xpath = "//XCUIElementTypeSecureTextField[@name='password-field']")
    public WebElement enterPassword;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='login-button']")
    public WebElement loginButton;

    public FacebookLoginIOS(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void isFacebookAppDisplayed(){
        utilities.waitForElement(loginWithFacebookApp,200);
    }
}
