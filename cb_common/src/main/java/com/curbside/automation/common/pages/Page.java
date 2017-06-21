package com.curbside.automation.common.pages;

import com.curbside.automation.common.utilities.Utilities;
import io.appium.java_client.AppiumDriver;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class Page {

    public Utilities utilities;
    public AppiumDriver driver;

    public Page(AppiumDriver driver){
        this.driver = driver;
        this.utilities = new Utilities(driver);
    }
}
