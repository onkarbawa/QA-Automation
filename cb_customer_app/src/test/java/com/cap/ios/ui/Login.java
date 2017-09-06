package com.cap.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by bawa.onkar
 */

public class Login {

    UIElement accountName = UIElement.byXpath("//XCUIElementTypeImage[@name='LogoIcon']/following-sibling::XCUIElementTypeOther[1]/XCUIElementTypeTextField");
    UIElement userName = UIElement.byXpath("//XCUIElementTypeImage[@name='LogoIcon']/following-sibling::XCUIElementTypeOther[2]/XCUIElementTypeTextField");
    UIElement password = UIElement.byXpath("//XCUIElementTypeImage[@name='LogoIcon']/following-sibling::XCUIElementTypeOther[3]/XCUIElementTypeSecureTextField");
    UIElement lblLoginErrorMsg = UIElement.byXpath("//XCUIElementTypeButton[@name='Login']/following-sibling::XCUIElementTypeStaticText");

    @And("^I enter \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" for login$")
    public void iEnterAnd(String account, String user, String pass) throws Throwable {
        accountName.sendKeys(account);
        userName.sendKeys(user);
        password.sendKeys(pass, true);
    }

    @Given("^I accept CAP notifications alerts$")
    public void acceptNotificationAlert() throws Throwable {
        for (int i = 0; i < 5; i++) {
            try {
                try {
                    new UIElement(By.name("Allow")).tap();
                } catch (Exception e) {
                    new UIElement(By.name("OK")).tap();
                }
            } catch (Exception e) {
            }
        }

    }

    @And("^I should see this \"([^\"]*)\" on the screen$")
    public void iShouldSeeErrorMsg(String expectedErrorMsg) throws Throwable {
        String actualErrorMsg = lblLoginErrorMsg.waitFor(2).getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Got different error message");
    }
}


