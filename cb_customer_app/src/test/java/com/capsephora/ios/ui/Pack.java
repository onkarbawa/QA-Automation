package com.capsephora.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by bawa.onkar.
 */

public class Pack {
    UIElement packageCount = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Packages:']/" +
            "following-sibling::XCUIElementTypeStaticText");

    @And("^I verify that package quantity (\\d+) meets packages account$")
    public void iVerifyThatPackageQuantityMeetsPackagesAccount(int quantity) throws Throwable {
        int packCount = Integer.valueOf(packageCount.waitFor(8).getText());
        if (packCount != quantity) {
            UIElement.byName("+").tap();
        }
    }
}
