package com.curbside.automation.customerApp.ios.pages.settingsPage;

import com.curbside.automation.common.pages.Page;
import com.curbside.automation.common.utilities.SwipeOptions;
import gherkin.lexer.Th;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by bawa.onkar on 21/06/17.
 */
public class SettingsIOS extends Page {

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Privacy']")
    public WebElement privacyButton;

    @FindBy(name = "Location Services")
    public WebElement locationServicesButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Curbside']")
    public WebElement curbsideApp;

    @FindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeOther[4]")
    public WebElement alwaysenabled;

    public SettingsIOS(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void getPrivacy()  {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        privacyButton.click();
    }
    public void getLocation(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        locationServicesButton.click();
    }
    public void getCurbsideApp(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        curbsideApp.click();
    }
    public void isAlwaysenabled(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        alwaysenabled.isDisplayed();
    }
    public void doScroll(){
                utilities.swipeOptions(SwipeOptions.Up);

    }
}
