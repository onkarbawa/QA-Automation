package com.curbside.automation.customerApp.ios.pages.home;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class HomePageIOS extends Page {

    @FindBy(xpath = "//XCUIElementTypeButton[@name='My Account']")
    public WebElement myAccount;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@label='Payment Info']")
    public WebElement paymentInfo;

    static final String SHOP = "//XCUIElementTypeButton[@name='Shop']";
    static final String MAP = "//XCUIElementTypeButton[@name='Map']";
    static final String CART = "//XCUIElementTypeButton[@name='Cart']";

    /**
     * Gets a AppiumDriver to initialize
     * @param driver
     */
    public HomePageIOS(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    /**
     * Gets Account Button
     * @return
     */
    public void getAccount(){
        myAccount.click();
    }
}
