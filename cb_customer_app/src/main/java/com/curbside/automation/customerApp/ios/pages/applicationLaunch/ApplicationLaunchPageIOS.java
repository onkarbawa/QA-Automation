package com.curbside.automation.customerApp.ios.pages.applicationLaunch;

import com.curbside.automation.common.pages.Page;
import com.curbside.automation.common.utilities.SwipeOptions;
import com.curbside.automation.customerApp.common.CustomerBaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class ApplicationLaunchPageIOS extends Page {

//    @FindBy(name = "Allow")
//    public WebElement allow;
    CustomerBaseTest customerBaseTest = new CustomerBaseTest();

    @FindBy(xpath = "//XCUIElementTypeButton[@label='Skip Intro']")
    public WebElement skipInroButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@label='HelpIconWhite']")
    public WebElement helpIcon;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='SettingsAndroid']")
    public WebElement curbsideSettings;

    By allow = By.name(ApplicationLaunchUIMapIOS.ALLOW_BUUTON);
    By getStartedButton = By.name(ApplicationLaunchUIMapIOS.GET_STARTED_BUTTON);
    By currentLocationPage = By.name(ApplicationLaunchUIMapIOS.CURRENT_LOCATION_PAGE);
    By okWithMe = By.xpath(ApplicationLaunchUIMapIOS.OK_WITH_ME);
    By allowToAccessCurrentLocation = By.name(ApplicationLaunchUIMapIOS.ALLOW_BUTTON_TO_ACCESS_LOCATION);


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
     * Gets Allow button
     * @return
     */
    public WebElement getAllow(){
        return driver.findElement(allow);
    }

    /**
     * Swipe screen
     * @param direction
     * @param noOfTimes
     */
    public void doSwipe(int noOfTimes){
        for(int i = 0;i < noOfTimes;i++) {
            //utilities.swipe(direction);
            System.out.print("scrolling times ----------------- "+i);
            utilities.swipeOptions(SwipeOptions.Left);
        }
    }

    /**
     * Gets started button
     * @return
     */
    public WebElement getStarted(){
        return driver.findElement(getStartedButton);
    }

    /**
     * Gets current location
     * @return
     */
    public WebElement getCurrentLocation(){
        return driver.findElement(currentLocationPage);
    }

    /**
     * Gets Ok with me button
     * @return
     */
    public WebElement getOkWithMe(){
        return driver.findElement(okWithMe);
    }

    /**
     * Gets Access current location
     * @return
     */
    public WebElement getAllowLocation(){
        return driver.findElement(allowToAccessCurrentLocation);
    }

    public void tapOnHelpIcon() throws InterruptedException {
        Thread.sleep(5000);
        helpIcon.click();
    }


    /**
     * Gets Privacy button
     * @return
     */
//    public WebElement getPrivacy(){
//        return driver.findElement(privacyButton);
//    }
//
//    public WebElement getLocationService(){
//        return driver.findElement(locationButton);
//    }



}
