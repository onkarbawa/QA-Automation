package com.curbside.automation.common.pages;

import com.curbside.automation.common.utilities.Utilities;
import io.appium.java_client.AppiumDriver;

public class Page {

    private Utilities utilities;
    protected AppiumDriver driver;

    public Page(AppiumDriver driver){
        this.driver = driver;
        this.utilities = new Utilities(driver);
    }

}
