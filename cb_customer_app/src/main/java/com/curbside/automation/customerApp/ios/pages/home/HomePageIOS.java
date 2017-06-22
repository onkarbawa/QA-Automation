package com.curbside.automation.customerApp.ios.pages.home;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class HomePageIOS extends Page {

    By myAccount = By.xpath(HomePageUIMapIOS.MY_ACCOUNT);

    /**
     * Gets a AppiumDriver to initialize
     * @param driver
     */
    public HomePageIOS(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Gets Account Button
     * @return
     */
    public WebElement getAccount(){
        return driver.findElement(myAccount);
    }
}
