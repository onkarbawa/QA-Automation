package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import org.openqa.selenium.By;

/**
 * Created by bawa.onkar on 7/3/17.
 */
public class Login {

    static UIElement email = new UIElement(By.className("XCUIElementTypeTextField"));
    static UIElement password = new UIElement(By.className("XCUIElementTypeSecureTextField"));
    
    @And("^I enter '(.*)' and '(.*)'$")
    public void signin(String emailText, String passwordText) throws Throwable {
        email.sendKeys(emailText);
        password.sendKeys(passwordText);
        Steps.tapButton("Sign In");
    }

    @And("^I signin in using signup information$")
    public void iAmSignedInUsingSignupInformation() throws Throwable {
        MyAccount.ensureSignedOut();
        try {
			FooterTabs.tapMyAccount();
			MyAccount.btnSignIn.tap();
		} catch (Exception e) {}

        Steps.tapButton("Sign In with Email");
        signin(Properties.getVariable("signupEmail"), Properties.getVariable("signupPassword"));
        
        MyAccount.btnAccountInfo.waitFor(30);
    }
}
