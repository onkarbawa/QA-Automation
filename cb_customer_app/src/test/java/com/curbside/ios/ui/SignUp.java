package com.curbside.ios.ui;

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

    static UIElement phoneNumber= new UIElement(By.xpath("//XCUIElementTypeTextField[2]"));

    @And("^I enter phone number$")
    public void iEnterPhoneNumber() throws Throwable {
       phoneNumber.enterText("12345678901");
    }
}
