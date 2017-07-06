package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;

/**
 * @author kumar.anil
 * This is page that appears when you launch application for first time
 */
public class Welcome {

    @And("^I have selected test environment$")
    public void iHaveSelectedTestEnvironment() throws Throwable {
        new UIElement(By.name("Allow")).tap();
        new UIElement(By.name("Skip Intro")).tap();
        new UIElement(By.name("Search")).tap();
        new UIElement(By.xpath("//XCUIElementTypeSearchField")).sendKeys(" _#csndc#env#s");
        new UIElement(By.name("Search")).tap();
    }

}