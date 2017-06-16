package com.curbside.ios.testClasses;

import com.curbside.customerApp.common.BaseTest;
import com.curbside.customerApp.ios.applicationLaunch.ApplicationLaunch;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.testng.annotations.Test;

/**
 * Created by tft on 15/06/17.
 */
public class AppLaunch extends BaseTest{

    ApplicationLaunch a = new ApplicationLaunch(this.driver);
    /**
     * Gets the instance of the Page
     *
     * @param driver
     */
    /*public AppLaunch(AppiumDriver driver) {
        super(driver);
    }*/


    @Test
    public void launchApp(){
      a.getTapButton().click();
    }
}
