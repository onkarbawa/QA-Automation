package com.curbside.automation.android.testClasses;

import com.curbside.automation.customerApp.ios.applicationLaunch.ApplicationLaunch;
import org.testng.annotations.Test;

/**
 * Created by Hitesh grover on 16/06/17.
 */
public class AppLaunch {

    ApplicationLaunch a = new ApplicationLaunch(this.driver);

    @Test
    public void launchApp(){
        a.getTapButton().click();
    }
}
