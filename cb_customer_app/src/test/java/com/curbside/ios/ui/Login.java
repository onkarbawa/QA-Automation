package com.curbside.ios.ui;

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

    public String emailText = "fusic.test1@gmail.com";
    public String passwordText = "fusic@123";

    static UIElement email = new UIElement(By.xpath("//XCUIElementTypeTextField[1]"));
    static UIElement password = new UIElement(By.xpath("//XCUIElementTypeSecureTextField[1]"));

    String randomMail = "test" + System.currentTimeMillis() + "@example.com";

    @And("^I enter email and password '(.*)'$")
    public void iEnterEmailAndPassword(String userType) throws Throwable {
        if(userType.equalsIgnoreCase("Old User")){
            email.enterText(emailText);
            password.enterText(passwordText);
        }
        else if (userType.equalsIgnoreCase("New User")) {
            email.enterText(randomMail);
            password.enterText(passwordText);
        }
    }
}
