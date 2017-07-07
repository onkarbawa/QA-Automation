package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.utilities.Helpers;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by bawa.onkar on 7/3/17.
 */
public class SignUp {

    static String randomMail = "test" + System.currentTimeMillis() + "@example.com";
    String phoneNumberText = "12345678901";

    static UIElement txtEmail = new UIElement(By.xpath("//XCUIElementTypeTextField[1]"));
    static UIElement txtPassword = new UIElement(By.xpath("//XCUIElementTypeSecureTextField[1]"));
    static UIElement txtPhoneNumber= new UIElement(By.xpath("//XCUIElementTypeTextField[2]"));
    static UIElement btnCreateAccount= UIElement.byAccessibilityId("Create Account");

    @And("^I signup for a new account$")
    public void iSignupForANewAccount() throws Throwable {
    	String emailId= Helpers.getRandomEmailId();
    	String password= "fusic@123";
    	
    	Properties.setVariable("signupEmail", emailId);
    	Properties.setVariable("signupPassword", password);
    	Properties.setVariable("signupPhoneNumber", phoneNumberText);
        
    	txtEmail.sendKeys(randomMail);
        txtPassword.sendKeys(password);
        txtPhoneNumber.sendKeys(phoneNumberText);
        btnCreateAccount.tap();
    }
}
