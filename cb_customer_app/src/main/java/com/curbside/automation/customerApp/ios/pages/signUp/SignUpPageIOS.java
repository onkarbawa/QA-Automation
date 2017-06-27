package com.curbside.automation.customerApp.ios.pages.signUp;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class SignUpPageIOS extends Page {

    @FindBy(xpath = "//XCUIElementTypeTextField[1]")
    public WebElement email;

    @FindBy(xpath = "//XCUIElementTypeSecureTextField[1]")
    public WebElement password;

    @FindBy(xpath = "//XCUIElementTypeTextField[2]")
    public WebElement phoneNumber;

    @FindBy(xpath = "//XCUIElementTypeButton[@label='Create Account']")
    public WebElement createAccount;

    public SignUpPageIOS(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getEmail(){
        String randomEmail= utilities.getRandomEmail();
        return randomEmail;
    }
}
