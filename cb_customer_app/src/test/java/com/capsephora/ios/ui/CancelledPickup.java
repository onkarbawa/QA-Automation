package com.capsephora.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by hitesh.grover on 07/12/17.
 */
public class CancelledPickup {

    UIElement btnViewOriginalReceipt = UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'View original purchase receipt')]");

    @And("I tap on View Original receipt button")
    public void setViewOriginalReceipt() throws Throwable {
        btnViewOriginalReceipt.waitFor(2).tap();
    }
}
