package com.curbside.automation.ios.testClasses;

import com.curbside.automation.customerApp.common.CustomerBaseTest;
import org.testng.Assert;
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
    public void launchApp() throws InterruptedException {
      this.getiOSApplicationLaunch().getTapAllow().click();
      this.getiOSApplicationLaunch().doSwipe("Left",2);
      this.getiOSApplicationLaunch().getTapGetStarted().click();
      this.getiOSApplicationLaunch().getTapOnOkWithMe().click();
      this.getiOSApplicationLaunch().getTapOnAllowLocation().click();
        Assert.assertEquals(this.getiOSApplicationLaunch().getCurrentLocation().getText(),"Current Location",
                "The pointer is not landing on current location page");
      getIOSSettingApp();
      this.getiOSApplicationLaunch().getTapOnPrivacy().click();
      this.getiOSApplicationLaunch().getTapOnLocationService().click();

    }
    @Test
    public void set(){
        getIOSSettingApp();
    }
}
