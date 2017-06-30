package com.curbside.automation.customerApp.ios.pages.applicationLaunch;

import com.curbside.automation.common.pages.Page;
import com.curbside.automation.common.utilities.SwipeOptions;
import com.curbside.automation.uifactory.AppleDevice;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.customerApp.common.CustomerBaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class ApplicationLaunchPageIOS extends Page {

    @FindBy(name = "Allow")
    public WebElement allow;

    @FindBy(name = "Get Started")
    public WebElement getStartedButton;

    @FindBy(name = "Current Location")
    public WebElement currentLocation;

    @FindBy(xpath = "//XCUIElementTypeOther[2]/XCUIElementTypeButton[@name='OK with me']")
    public WebElement okWithMe;

    @FindBy(name = "Allow")
    public WebElement allowToAccessCurrentLocation;

    @FindBy(xpath = "//XCUIElementTypeButton[@label='Skip Intro']")
    public WebElement skipInroButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@label='HelpIconWhite']")
    public WebElement helpIcon;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Settings']")
    public WebElement curbsideSettings;

    /**
     * Gets a AppiumDriver to initialize
     *
     * @param driver
     */
    public ApplicationLaunchPageIOS(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    /**
     * Swipe screen
     * @param direction
     * @param noOfTimes
     */
    public void doSwipe(int noOfTimes) throws Exception {
        for(int i = 0;i < noOfTimes;i++) {
            utilities.swipeOptions(SwipeOptions.Left);
        }
    }

    public void tapOnHelpIcon(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        helpIcon.click();
    }
}
