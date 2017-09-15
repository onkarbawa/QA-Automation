package com.cap.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

/**
 * Created by bawa.onkar
 */
public class Issue {

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
}
