package com.curbside.automation.android.testClasses;

import com.curbside.automation.customerApp.common.CustomerBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover on 16/06/17.
 */

public class AppLaunch extends CustomerBaseTest {

    /**
     *
     * @author hitesh.grover
     */
    @Test(groups = {"all", "applicationLaunch", "TCS-C114937"})
    public void launchSkipIntro(){
        this.getApplicationLaunchPageAndroid().getSkipIntro().click();
        this.getApplicationLaunchPageAndroid().getLocationOkButton().click();
        this.getApplicationLaunchPageAndroid().getAllowButton().click();
        if(this.getUtilities().isElementPresent(this.getApplicationLaunchPageAndroid().allowButton)) {
            this.getApplicationLaunchPageAndroid().getAllowButton().click();
        }
        Assert.assertTrue(this.getUtilities().isElementPresent(this.getHomePageAndroid().currentLocationButton),"Android : HomePage is not visible yet");
    }

    /**
     *
     * @author hitesh.grover
     * @throws Throwable 
     */
    @Test(groups = {"all", "applicationLaunch", "TCS-C114936"})
    public void launchWithIntro() throws Throwable{
        this.getApplicationLaunchPageAndroid().getSkipIntro().isDisplayed();
        this.getApplicationLaunchPageAndroid().scrollIntroPage(2);
        this.getApplicationLaunchPageAndroid().getStartedButton().click();
        this.getApplicationLaunchPageAndroid().getLocationOkButton().click();
        this.getApplicationLaunchPageAndroid().getAllowButton().click();
        if(this.getUtilities().isElementPresent(this.getApplicationLaunchPageAndroid().allowButton)) {
           this.getApplicationLaunchPageAndroid().getAllowButton().click();
        }
        Assert.assertTrue(this.getUtilities().isElementPresent(this.getHomePageAndroid().currentLocationButton),"Android : HomePage is not visible yet");
    }
}

