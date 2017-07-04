package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * @author kumar.anil
 * My Account- After clicking My Account tab on footer strip
 */

public class MyAccount {

    static UIElement email= new UIElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]"));
    static UIElement phoneNumber= new UIElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]"));

    @Then("^I saw email on MyAccount page$")
    public void iSawEmailOnMyAccountPage() throws Throwable {
        Assert.assertEquals(email.getText(), new Login().enteredEmail,
                "Entered email is not matched with Account Page");
    }

    @Then("^I saw new email on MyAccount page$")
    public void iSawNewEmailOnMyAccountPage() throws Throwable {
        Assert.assertEquals(email.getText(), new SignUp().randomMail,
                "Entered email is not matched with Account Page");
    }
}