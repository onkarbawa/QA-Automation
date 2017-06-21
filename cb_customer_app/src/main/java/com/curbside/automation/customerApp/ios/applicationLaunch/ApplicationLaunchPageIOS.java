package com.curbside.automation.customerApp.ios.applicationLaunch;

import com.curbside.automation.common.utilities.Utilities;
import com.curbside.automation.customerApp.common.CustomerBaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class ApplicationLaunchPageIOS extends CustomerBaseTest {

    private AppiumDriver driver;
    private Utilities util ;

    By allow = By.name(ApplicationLaunchUIMap.ALLOW_BUUTON);
    By getStartedButton = By.name(ApplicationLaunchUIMap.GET_STARTED_BUTTON);
    By currentLocationPage = By.name(ApplicationLaunchUIMap.CURRENT_LOCATION_PAGE);
    By okWithMe = By.xpath(ApplicationLaunchUIMap.OK_WITH_ME);
    By allowToAccessCurrentLocation = By.name(ApplicationLaunchUIMap.ALLOW_BUTTON_TO_ACCESS_LOCATION);
    By privacyButton = By.xpath(ApplicationLaunchUIMap.PRIVACY_BUTTON);
    By locationServicesButton = By.name(ApplicationLaunchUIMap.LOCATION_SERVICES_BUTTON);

    /**
     * Gets a AppiumDriver to initialize
     *
     * @param driver
     */
    public ApplicationLaunchPageIOS(AppiumDriver driver) {
        this.driver = driver;
        util = new Utilities(this.driver);
    }

    /**
     * Gets Allow button
     * @return
     */
    public WebElement getTapAllow(){
        return driver.findElement(allow);
    }

    /**
     * Swipe screen
     * @param direction
     * @param noOfTimes
     */
    public void doSwipe(String direction, int noOfTimes){
        for(int i = 0;i < noOfTimes;i++) {
            util.swipe(direction);
        }
    }

    /**
     * Gets started button
     * @return
     */
    public WebElement getTapGetStarted(){
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
    public WebElement getTapOnOkWithMe(){
        return driver.findElement(okWithMe);
    }

    /**
     * Gets Access current location
     * @return
     */
    public WebElement getTapOnAllowLocation(){
        return driver.findElement(allowToAccessCurrentLocation);
    }

    /**
     * Gets Privacy button
     * @return
     */
    public WebElement getTapOnPrivacy(){
        return driver.findElement(privacyButton);
    }

    public WebElement getTapOnLocationService(){
        return driver.findElement(locationServicesButton);
    }


}
