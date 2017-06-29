package com.curbside.automation.customerApp.ios.pages.login;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class LoginPageIOS extends Page {

    @FindBy(xpath = "//XCUIElementTypeTextField[@value='Email']")
    public WebElement email;

    @FindBy(xpath = "//XCUIElementTypeSecureTextField[@value='Password']")
    public WebElement password;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Sign In']")
    public WebElement signInButton;

    /**
     * Gets a AppiumDriver to initialize
     * @param driver
     */
    public LoginPageIOS(AppiumDriver driver) {
        super(driver);
    }

}
