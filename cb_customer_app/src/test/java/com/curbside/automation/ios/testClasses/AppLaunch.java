package com.curbside.automation.ios.testClasses;

import com.curbside.automation.customerApp.common.CustomerBaseTest;
import com.curbside.automation.customerApp.ios.pages.settingsPage.SettingsIOS;
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
      this.getiOSApplicationLaunch().getAllow().click();
      this.getiOSApplicationLaunch().doSwipe(2);
      this.getiOSApplicationLaunch().getStarted().click();
      this.getiOSApplicationLaunch().getOkWithMe().click();
      this.getiOSApplicationLaunch().getAllowLocation().click();
        Assert.assertEquals(this.getiOSApplicationLaunch().getCurrentLocation().getText(),"Current Location",
                "The pointer is not landing on current location page");
      getIOSSettingApp();
//      this.getiOSApplicationLaunch().getPrivacy().click();
//      this.getiOSApplicationLaunch().getLocationService().click();
        //TODO
    }
    @Test
    public void set(){
        getIOSSettingApp();
        new SettingsIOS(driver).doScroll();

            new SettingsIOS(driver).getPrivacy();

    }
}
