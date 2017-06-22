package com.curbside.automation.customerApp.ios.pages.myAccount;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class MyAccountPageIOS extends Page {

    By signIn = By.xpath(MyAccountPageUIMapIOS.SIGN_IN);
    By signUp = By.xpath(MyAccountPageUIMapIOS.SIGN_UP_BUTTON);

    /**
     * Gets a AppiumDriver to initialize
     * @param driver
     */
    public MyAccountPageIOS(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Gets sign_in button
     * @return
     */
    public WebElement getSignIn(){
        return driver.findElement(signIn);
    }

    /**
     * Gets sign_up button
     */
    public WebElement getSignUp(){
        return driver.findElement(signUp);
    }
}
