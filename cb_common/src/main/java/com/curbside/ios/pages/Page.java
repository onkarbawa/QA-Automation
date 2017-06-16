package com.curbside.ios.pages;

import com.curbside.ios.common.Utilities;
import io.appium.java_client.AppiumDriver;

public class Page {

    private Utilities utilities;
    protected AppiumDriver driver;

    public Page(AppiumDriver driver){
        this.driver = driver;
    }

}
