package com.curbside.automation.customerApp.android.pages.applicationLaunch;


import com.curbside.automation.common.pages.Page;
import com.curbside.automation.common.utilities.SwipeOptions;
import com.curbside.automation.uifactory.AndroidDevice;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by hitesh.grover on 16/06/17.
 */
public class ApplicationLaunchPageAndroid extends Page {


    public final By locationOkButton = By.id(ApplicationLaunchUIMap.LOCATION_OK_BUTTON);
    public final By skipIntro = By.id(ApplicationLaunchUIMap.SKIP_INTRO);
    public final By allowButton = By.id(ApplicationLaunchUIMap.LOCATION_ALLOW_BUTTON);
    public final By getStarted = By.id(ApplicationLaunchUIMap.GET_STARTED);

    public ApplicationLaunchPageAndroid(AppiumDriver driver) {
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
     * Scroll the page to left for n number of times.
     *@param numberOfTimes
     */
    public void scrollIntroPage(int numberOfTimes) {

        for (int i = 0; i <= numberOfTimes; ++i) {
            try {
                AndroidDevice.swipeLeft();
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void goToHomePage(){
        this.getSkipIntro().click();
        this.getLocationOkButton().click();
        this.getAllowButton().click();
        if(utilities.isElementPresent(allowButton)){
            this.getAllowButton().click();
        }
    }
}
