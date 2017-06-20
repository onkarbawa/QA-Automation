package com.curbside.automation.customerApp.ios.applicationLaunch;

import com.curbside.automation.customerApp.common.CustomerBaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class ApplicationLaunch extends CustomerBaseTest {

    private AppiumDriver driver;
    By allow = By.name(ApplicationLaunchUIMap.ALLOW_BUUTON);

    /**
     * Gets a AppiumDriver to initialize
     *
     * @param driver
     */
    public ApplicationLaunch(AppiumDriver driver) {
        this.driver = driver;
    }


    public WebElement getTapButton(){
        return driver.findElement(allow);
    }
}
