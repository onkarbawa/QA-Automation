package com.cap.ios.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

/**
 * Created by bawa.onkar
 */

public class Login {

    UIElement accountName = UIElement.byXpath("//XCUIElementTypeImage[@name='LogoIcon']/following-sibling::XCUIElementTypeOther[1]/XCUIElementTypeTextField");
    UIElement userName = UIElement.byXpath("//XCUIElementTypeImage[@name='LogoIcon']/following-sibling::XCUIElementTypeOther[2]/XCUIElementTypeTextField");
    UIElement password = UIElement.byXpath("//XCUIElementTypeImage[@name='LogoIcon']/following-sibling::XCUIElementTypeOther[3]/XCUIElementTypeSecureTextField");


    @And("^I enter '(.*)','(.*)' and '(.*)'$")
    public void iEnterAnd(String account,String user,String pass) throws Throwable {
       accountName.sendKeys(account);
       userName.sendKeys(user);
       password.sendKeys(pass);
       Steps.tapButton("Login");
    }
}
