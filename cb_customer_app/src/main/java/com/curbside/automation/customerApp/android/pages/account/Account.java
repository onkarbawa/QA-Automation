package com.curbside.automation.customerApp.android.pages.account;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by hitesh.grover on 27/06/17.
 */
public class Account extends Page{

    @FindBy(id = "com.curbside.nCurbside:id/button_sign_up")
    public WebElement signUp;


    public Account(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public WebElement getSignUpButton(){
        return signUp;
    }
}
