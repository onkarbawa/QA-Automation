package com.namshi.iOS.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

public class Setting {

    UIElement btnWiFi = UIElement.byName("Wi-Fi");
    UIElement currentWiFi = UIElement.byXpath("//XCUIElementTypeCell[@name='Wi-Fi']/following-sibling::" +
            "XCUIElementTypeCell[1]/XCUIElementTypeButton");
    UIElement btnManualProxy = UIElement.byName("Manual");
    UIElement txtServer = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Server']");
    UIElement txtPort = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Port']");
    UIElement btnDelete = UIElement.byName("delete");
    UIElement btnReturn = UIElement.byName("Return");
    UIElement txtUrlbar = UIElement.byXpath("//XCUIElementTypeOther[@name='URL']");
    UIElement menu = UIElement.byAccessibilityId("Toggle navigation");
    UIElement linkPartner = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Partners']");

    @And("^I add manual proxy setting$")
    public void iAddManualProxySetting() throws Throwable {
        btnWiFi.tap();
        currentWiFi.waitFor(20).tap();
        btnManualProxy.scrollTo().tap();
        txtServer.waitFor(10).scrollTo().tap();
        //txtServer.sendKeys("",false);
        for (int i = 0; i < 17; i++){
            btnDelete.tap();
        }
        txtServer.waitFor(10).scrollTo().setText("192.168.110.251",false);
        btnReturn.tap();
        for (int i = 0; i < 5; i++){
            btnDelete.tap();
        }
        txtPort.sendKeys("8080",false);
        btnWiFi.tap();
    }

    @And("^I enter (.*) URL$")
    public void iEnterURL(String urlName) throws Throwable {
        Thread.sleep(3000);
        txtUrlbar.sendKeys(urlName,false);
        UIElement.byName("Go").tap();
    }

    @And("^I search (.*)$")
    public void iSearch(String searchText) throws Throwable {
        Thread.sleep(3000);
        menu.tapCenter();
        Thread.sleep(3000);
        linkPartner.tapCenter();
        MobileDevice.getScreenshot(true);
    }
}
