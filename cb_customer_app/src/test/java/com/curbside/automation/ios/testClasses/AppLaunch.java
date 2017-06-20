package com.curbside.automation.ios.testClasses;

import com.curbside.automation.customerApp.common.CustomerBaseTest;
import org.testng.annotations.Test;

/**
 * Created by bawa.onkar on 15/06/17.
 */
public class AppLaunch extends CustomerBaseTest {

    /**
     * Verify checkmark is set 'always'
     * @author bawa.onkar
     */
    @Test(description = "TCS-C114937")
    public void launchApp() {
      this.getiOSApplicationLaunch().getTapButton().click();
      this.getiOSApplicationLaunch().doSwipe("Left",500,1,2);
    }
}
