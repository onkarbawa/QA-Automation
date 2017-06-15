package com.curbside.ios.pages;

import com.curbside.ios.common.Utilities;
import io.appium.java_client.AppiumDriver;

public class Page {

    protected AppiumDriver driver;
    private Utilities utilities;

    /**
     * Gets the instance of the Page
     *
     * @param driver
     */
    public Page(AppiumDriver driver){
        this.driver = driver;
        utilities = new Utilities(driver);
    }
}
