package com.curbside.automation.customerApp.android.applicationLaunch;


import com.curbside.automation.common.pages.Page;
import com.curbside.automation.common.utilities.Utilities;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by hitesh.grover on 16/06/17.
 */
public class ApplicationLaunchPage extends Page {


    public final By locationOkButton = By.id(ApplicationLaunchUIMap.LOCATION_OK_BUTTON);
    public final By skipIntro = By.id(ApplicationLaunchUIMap.SKIP_INTRO);
    public final By allowButton = By.id(ApplicationLaunchUIMap.LOCATION_ALLOW_BUTTON);
    public final By getStarted = By.id(ApplicationLaunchUIMap.GET_STARTED);
    public final By searchButton = By.id(ApplicationLaunchUIMap.SEARCH_BUTTON);
    public final By currentLocation = By.id(ApplicationLaunchUIMap.CURRENT_LOCATION_BUTTON);

    public ApplicationLaunchPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Gets the Location 'OK' button from Intro page
     * @return search button element
     */
    public WebElement getLocationOkButton() {
        return driver.findElement(locationOkButton);
    }

    /**
     * Gets the 'Skip Intro' button from Intro page
     * @return search button element
     */
    public WebElement getSkipIntro() {
        return driver.findElement(skipIntro);
    }

    /**
     * Gets the location 'Allow' button from dialog box
     * @return search button element
     */
    public WebElement getAllowButton() {
        return driver.findElement(allowButton);
    }

    /**
     * Gets the 'Get started' button from Intro page
     * @return search button element
     */
    public WebElement getStartedButton() {
        return driver.findElement(getStarted);
    }

    /**
     * Gets the 'Search' button from HomePageIOS page
     * @return search button element
     */
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }

    /**
     * Gets the 'Current location' button from HomePageIOS page
     * @return current location button element
     */
    public WebElement getCurrentLocation(){
        return driver.findElement(currentLocation);
    }

    /**
     * Scroll the page to left for n number of times.
     *@param numberOfTimes
     */
    public void scrollIntroPage(int numberOfTimes) {

        for (int i = 0; i < numberOfTimes; ++i) {
            try {
                utilities.swipe("LEFT");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
