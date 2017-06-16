package com.curbside.customerApp.ios.applicationLaunch;

import com.curbside.ios.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class ApplicationLaunch extends Page {

    By allow = By.name(ApplicationLaunchUIMap.ALLOW_BUUTON);

    public ApplicationLaunch(AppiumDriver driver) {
        super(driver);
    }


    public WebElement getTapButton(){
        return driver.findElement(allow);
    }
}
