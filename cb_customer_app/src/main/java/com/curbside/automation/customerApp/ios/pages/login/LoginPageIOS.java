package com.curbside.automation.customerApp.ios.pages.login;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class LoginPageIOS extends Page {

    By email = By.xpath(LoginPageUIMapIOS.EMAIL);
    By password = By.xpath(LoginPageUIMapIOS.PASSWORD);
    By signInButton = By.xpath(LoginPageUIMapIOS.SIGN_IN_BUTTON);


    /**
     * Gets a AppiumDriver to initialize
     * @param driver
     */
    public LoginPageIOS(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Gets email field
     * @return
     */
    public WebElement getEmail(){
        return driver.findElement(email);
    }

    /**
     * Gets password field
     * @return
     */
    public WebElement getPassword(){
        return driver.findElement(password);
    }

    /**
     * Gets sign in field
     * @return
     */
    public WebElement getSignInButton(){
        return driver.findElement(signInButton);
    }

}
