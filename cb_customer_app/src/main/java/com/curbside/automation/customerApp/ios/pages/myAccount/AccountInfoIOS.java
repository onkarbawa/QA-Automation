package com.curbside.automation.customerApp.ios.pages.myAccount;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class AccountInfoIOS extends Page {

    //Upper Section of My Account

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Create one now']")
    public WebElement createOneNow;

    @FindBy(xpath = "//XCUIElementTypeButton[@label='Sign In']")
    public WebElement signIn;

    //Lower Section of My Account

    @FindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]")
    public WebElement userName;

    @FindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]")
    public WebElement email;

    @FindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]")
    public WebElement phoneNumber;

    /**
     * Gets a AppiumDriver to initialize
     * @param driver
     */
    public AccountInfoIOS(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

   public void isPhoneNoDisplayed(){
        utilities.waitForElement(phoneNumber,200);
   }
}
