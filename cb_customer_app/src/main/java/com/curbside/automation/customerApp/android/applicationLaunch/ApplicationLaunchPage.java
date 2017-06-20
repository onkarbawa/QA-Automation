package com.curbside.automation.customerApp.android.applicationLaunch;


import com.curbside.automation.common.utilities.Utilities;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by hitesh.grover on 16/06/17.
 */
public class ApplicationLaunchPage {

    private AppiumDriver driver;
    private Utilities util ;

    By locationOkButton = By.id(ApplicationLaunchUIMap.LOCATION_OK_BUTTON);
    By skipIntro = By.id(ApplicationLaunchUIMap.SKIP_INTRO);
    By allowButton = By.id(ApplicationLaunchUIMap.LOCATION_ALLOW_BUTTON);
    By getStarted = By.id(ApplicationLaunchUIMap.GET_STARTED);
    By searchButton = By.id(ApplicationLaunchUIMap.SEARCH_BUTTON);

    public ApplicationLaunchPage(AppiumDriver driver) {
        this.driver = driver;
        util = new Utilities(this.driver);
    }


    public WebElement getLocationOkButton(){
        return driver.findElement(locationOkButton);
    }

    public WebElement getSkipIntro(){
        return driver.findElement(skipIntro);
    }

    public WebElement getAllowButton(){
        return driver.findElement(allowButton);
    }

    public WebElement getStartedButton(){
        return driver.findElement(getStarted);
    }

    /**
     * Gets the Search button from Home page
     * @return
     */
    public WebElement getSearchButton(){
        return driver.findElement(searchButton);
    }

    /**
     * Scroll the page n number of times.
     * @param numberOfTimes
     */
    public void scrollIntroPage( int numberOfTimes){

        for (int i=0; i<numberOfTimes; ++i){
            try {
                util.swipeHorizontal(0.9,0.01,0.5,1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
