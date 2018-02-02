package com.cap.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by bawa.onkar
 */

public class FooterTabs extends AbstractScreen{

    public UIElement btnTask = UIElement.byAccessibilityId("Tasks");
    public UIElement btnPickUp = UIElement.byAccessibilityId("Pickups");
    public UIElement btnPickUpScan = UIElement.byAccessibilityId("Pickup Scan");
    public UIElement btnMyAccount = UIElement.byAccessibilityId("My Account");
    public UIElement btnMore = UIElement.byAccessibilityId("More");

    public FooterTabs() {
        // TODO Auto-generated constructor stub
    }

    @And("^I tap on '(.*)' icon in bottom menu for cap$")
    public void iTapOnIconInBottomMenuForCap(String tabName) throws Throwable {
        UIElement.byAccessibilityId(tabName).tap();
        MobileDevice.getScreenshot(true);
    }

    public void tapMyAccount() throws Throwable {
        btnMyAccount.tap();
    }

    public void tapTask() throws Throwable {
        btnTask.tap();
    }

    public void tapPickUp() throws Throwable {
        btnPickUp.tap();
    }

    public void tapPickUpScan() throws Throwable {
        btnPickUpScan.tap();
    }

    public void tapMore() throws Throwable {
        btnMore.tap();
    }
}
