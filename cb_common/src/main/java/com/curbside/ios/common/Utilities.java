package com.curbside.ios.common;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;

public class Utilities {

    private final static Logger logger = Logger.getLogger(Utilities.class);
    private AppiumDriver driver;

    /**
     * Gets the AppiumDriver
     *
     * @return
     */
    public AppiumDriver getDriver(){
        return driver;
    }
    /**
     * Gets the instance of Utilities
     *
     * @param driver
     */
    public Utilities(AppiumDriver driver){
        this.driver = driver;
    }
}
