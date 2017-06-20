package com.curbside.automation.android.testClasses;

import com.curbside.automation.customerApp.common.CustomerBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover on 16/06/17.
 */
public class AppLaunch extends CustomerBaseTest {


    @Test(groups = {"all", "applicationLaunch", "c114937"})
    public void launchApp(){
        this.getApplicationLaunchPage().getSkipIntro().click();
        this.getApplicationLaunchPage().getLocationOkButton().click();

    }

    @Test(groups = {"all", "applicationLaunch", "c114936"})
    public void gotoHomePage(){
        this.getApplicationLaunchPage().getSkipIntro().isDisplayed();
        this.getApplicationLaunchPage().scrollIntroPage(2);
        this.getApplicationLaunchPage().getStartedButton().click();
        this.getApplicationLaunchPage().getLocationOkButton().click();
        while(this.getApplicationLaunchPage().getAllowButton().isDisplayed()){
            this.getApplicationLaunchPage().getAllowButton().click();
        }
      //  Assert.assertTrue(this.getApplicationLaunchPage().getLocationOkButton().isDisplayed()/*this.getUtilitiesPage().isElementPresent(this.getApplicationLaunchPage().*/),"Home page is not visible yet");

    }
}

