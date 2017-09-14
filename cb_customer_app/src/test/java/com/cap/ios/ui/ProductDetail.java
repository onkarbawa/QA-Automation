package com.cap.ios.ui;

import com.curbside.automation.uifactory.UIElement;

/**
 * Created by bawa.onkar
 */
public class ProductDetail extends AbstractScreen {

    UIElement firstProduct = UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Items to Pick')]]/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeStaticText[1]");
    UIElement productName = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[2]//XCUIElementTypeStaticText");
    UIElement productRetailerWebsiteLink = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[3]//XCUIElementTypeStaticText");
    UIElement redirectLinkPage = UIElement.byName("Product at Retailer");
    UIElement productSKU = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[5]//XCUIElementTypeStaticText");
    UIElement productDescription = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[7]//XCUIElementTypeTextView");
    UIElement productOverview = UIElement.byXpath("//XCUIElementTypeCell//XCUIElementTypeStaticText[@name='Overview']");
}
