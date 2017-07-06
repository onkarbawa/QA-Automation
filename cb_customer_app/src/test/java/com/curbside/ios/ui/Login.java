package com.curbside.ios.ui;

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

    static UIElement email = new UIElement(By.xpath("//XCUIElementTypeTextField[1]"));
    static UIElement password = new UIElement(By.xpath("//XCUIElementTypeSecureTextField[1]"));
    Home home = new Home();
    Steps steps = new Steps();
    Welcome welcome = new Welcome();
    FooterTabs footerTabs = new FooterTabs();
    MyAccount myAccount = new MyAccount();

    @And("^I enter '(.*)' and '(.*)'$")
    public void iEnterEmailAndPassword(String emailText, String passwordText) throws Throwable {
        email.enterText(emailText);
        password.enterText(passwordText);
    }

    @And("^I am signed in using signup information$")
    public void iAmSignedInUsingSignupInformation() throws Throwable {
        steps.acceptLocationAlert();
        welcome.skipIntro.tap();
        home.iHaveSelectedTestEnvironment();
        footerTabs.iTapOnMyAccountIconInBottomMenu("My Account");
        steps.tapButton("Sign In");
        steps.tapButton("Sign In with Email");
        iEnterEmailAndPassword("fusic.test1@gmail.com","fusic@123");
        steps.tapButton("Sign In");
        myAccount.phoneNumber.waitForElement(30);
    }


}
