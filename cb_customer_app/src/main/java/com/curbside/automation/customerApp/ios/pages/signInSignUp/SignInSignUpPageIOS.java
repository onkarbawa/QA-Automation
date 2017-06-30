package com.curbside.automation.customerApp.ios.pages.signInSignUp;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class SignInSignUpPageIOS extends Page {

    @FindBy(xpath = "//XCUIElementTypeButton[@label='Create An Account']")
    public WebElement createAnAccount;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Sign In with Email']")
    public WebElement signWithMail;

    @FindBy(name = "Sign In with Facebook")
    public WebElement signInWithFacebook;

    /**
     * Gets a AppiumDriver to initialize
     * @param driver
     */
    public SignInSignUpPageIOS(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

}
