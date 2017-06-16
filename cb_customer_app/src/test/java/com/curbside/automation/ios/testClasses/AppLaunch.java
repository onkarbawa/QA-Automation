package com.curbside.automation.ios.testClasses;

import com.curbside.automation.customerApp.common.BaseTest;
import com.curbside.automation.customerApp.ios.applicationLaunch.ApplicationLaunch;
import org.testng.annotations.Test;

/**
 * Created by tft on 15/06/17.
 */
public class AppLaunch extends BaseTest {

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
