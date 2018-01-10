package com.arriveconsole.ios.ui;

import com.curbside.automation.uifactory.UIElement;

/**
 * Created by bawa.onkar.
 */
public class ArriveConsoleTester extends AbstractScreen {
    UIElement txtBxName = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Name :']/following-sibling::" +
            "XCUIElementTypeTextField[1]");
    UIElement txtBxTracking = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Tracking ID :']/following-sibling::" +
            "XCUIElementTypeTextField[1]");
    UIElement txtBxToken = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Token :']/following-sibling::" +
            "XCUIElementTypeTextField[1]");
    UIElement txtBxSiteId = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Site ID :']/following-sibling::" +
            "XCUIElementTypeTextField[1]");

}
