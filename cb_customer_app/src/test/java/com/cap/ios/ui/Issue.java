package com.cap.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.nl.Stel;

/**
 * Created by bawa.onkar
 */
public class Issue extends AbstractScreen{

    UIElement insufficientQuantity = UIElement.byXpath("//XCUIElementTypeOther[3]/XCUIElementTypeTextField");
    UIElement firstIssueBtn = UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Items to Pick')]]/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeButton[@name='Issue']");
    UIElement secondGotItBtn = UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Items to Pick')]]/following-sibling::XCUIElementTypeCell[2]//XCUIElementTypeButton[@name='Got It']");

    @And("^I turn '(.*)' '(.*)'$")
    public void iTurnONItemNotAvailable(String ONorOFF,String button) throws Throwable {
        UIElement toggleButton = UIElement.byClass("XCUIElementTypeSwitch");

        String currentButtonValue = toggleButton.getAttribute("value");
        System.out.println("Current toggle value is " + currentButtonValue);

        currentButtonValue = currentButtonValue.equals("true") ? "ON" : "OFF";

        if(!ONorOFF.equalsIgnoreCase(currentButtonValue))
            toggleButton.tap();

        MobileDevice.getScreenshot(true);
    }

    @And("^I tap on '(.*)' and enter quantity '(.*)'$")
    public void iTapOnInsufficientQuantityAndEnter(String button, String quantity) throws Throwable {
        Steps.tapButton("Issue");
        insufficientQuantity.sendKeys(quantity,false);
        Steps.tapButton("Done");
    }
}
