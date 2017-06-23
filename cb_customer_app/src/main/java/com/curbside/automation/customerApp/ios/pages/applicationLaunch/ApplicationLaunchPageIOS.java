package com.curbside.automation.customerApp.ios.pages.applicationLaunch;

import com.curbside.automation.common.pages.Page;
import com.curbside.automation.common.utilities.SwipeOptions;
import com.curbside.automation.customerApp.ios.pages.settingsPage.Settings;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class ApplicationLaunchPageIOS extends Page {

    By allow = By.name(ApplicationLaunchUIMapIOS.ALLOW_BUUTON);
    By getStartedButton = By.name(ApplicationLaunchUIMapIOS.GET_STARTED_BUTTON);
    By currentLocationPage = By.name(ApplicationLaunchUIMapIOS.CURRENT_LOCATION_PAGE);
    By okWithMe = By.xpath(ApplicationLaunchUIMapIOS.OK_WITH_ME);
    By allowToAccessCurrentLocation = By.name(ApplicationLaunchUIMapIOS.ALLOW_BUTTON_TO_ACCESS_LOCATION);
    By privacyButton = By.xpath(Settings.PRIVACY_BUTTON);
    By locationServicesButton = By.name(Settings.LOCATION_SERVICES_BUTTON);

    /**
     * Gets a AppiumDriver to initialize
     *
     * @param driver
     */
    public ApplicationLaunchPageIOS(AppiumDriver driver) {
        super(driver);
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

    /**
     * Gets Privacy button
     * @return
     */
    public WebElement getPrivacy(){
        return driver.findElement(privacyButton);
    }

    public WebElement getLocationService(){
        return driver.findElement(locationServicesButton);
    }

    public void doScroll(){
        for (int i=1;i<10;i++){
            if (utilities.isElementPresent(privacyButton)){
                getPrivacy().click();
            }
            break;
        }
    }

}
