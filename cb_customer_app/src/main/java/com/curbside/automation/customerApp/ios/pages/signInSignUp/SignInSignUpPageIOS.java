package com.curbside.automation.customerApp.ios.pages.signInSignUp;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class SignInSignUpPageIOS extends Page {
    By signInWithMail = By.xpath(SignInSignUpPageUIMapIOS.SIGN_IN_WITH_MAIL);

    /**
     * Gets a AppiumDriver to initialize
     * @param driver
     */
    public SignInSignUpPageIOS(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Gets SignIn with mail Button
     * @return
     */
    public WebElement getSignWithMail(){
        return driver.findElement(signInWithMail);
    }
}
