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

    Login login = new Login();

    @Then("^I saw email on MyAccount page '(.*)'$")
    public void iSawEmailOnMyAccountPage(String userType) throws Throwable {
        phoneNumber.waitForElement(200);
        if(userType.equalsIgnoreCase("Old User")){
            Assert.assertEquals(email.getText(), login.emailText,
                    "Entered email is not matched with Account Page");
        }
        else if (userType.equalsIgnoreCase("New User")) {
            Assert.assertEquals(email.getText(), login.randomMail,
                    "Entered email is not matched with Account Page");
        }
    }
}
