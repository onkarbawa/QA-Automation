package com.curbside.customerApp.home;

import com.curbside.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class HomePage extends Page {

    By home = By.id(HomePageUIMap.HOME);

    /**
     * Gets the instance of the Page
     *
     * @param driver
     */
    public HomePage(AppiumDriver driver) {
        super(driver);
    }


    public WebElement getHome() {
        return this.driver.findElement(home);
    }
}
