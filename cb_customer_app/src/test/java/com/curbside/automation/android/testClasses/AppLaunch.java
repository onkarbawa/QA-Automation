package com.curbside.automation.android.testClasses;

import com.curbside.automation.customerApp.android.applicationLaunch.ApplicationLaunchPage;
import com.curbside.automation.customerApp.android.applicationLaunch.ApplicationLaunchUIMap;
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
    public void launchApp(){
        this.getApplicationLaunchPage().getSkipIntro().click();
        this.getApplicationLaunchPage().getLocationOkButton().click();

    }

    /**
     *
     * @author hitesh.grover
     */
    @Test(groups = {"all", "applicationLaunch", "TCS-C114936"})
    public void gotoHomePage(){
        this.getApplicationLaunchPage().getSkipIntro().isDisplayed();
        this.getApplicationLaunchPage().scrollIntroPage(2);
        this.getApplicationLaunchPage().getStartedButton().click();
        this.getApplicationLaunchPage().getLocationOkButton().click();
        this.getApplicationLaunchPage().getAllowButton().click();
        if(this.getUtilities().isElementPresent(ApplicationLaunchPage.allowButton)) {
            this.getApplicationLaunchPage().getAllowButton().click();
        }
        Assert.assertTrue(this.getUtilities().isElementPresent(ApplicationLaunchPage.currentLocation),"Home page is not visible yet");

    }
}

