package com.curbside.automation.ios.testClasses;

import com.curbside.automation.customerApp.common.CustomerBaseTest;
import org.testng.annotations.Test;

/**
 * Created by bawa.onkar on 15/06/17.
 */
public class AppLaunch extends CustomerBaseTest {

    @Test
    public void launchApp() {
      this.getiOSApplicationLaunch().getTapButton().click();
    }
}
