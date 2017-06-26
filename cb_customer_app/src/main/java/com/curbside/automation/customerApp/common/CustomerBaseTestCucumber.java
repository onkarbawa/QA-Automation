package com.curbside.automation.customerApp.common;

import com.curbside.automation.common.utilities.Utilities;
import com.curbside.automation.customerApp.android.pages.applicationLaunch.ApplicationLaunchPageAndroid;
import com.curbside.automation.customerApp.android.pages.location.LocationPage;
import com.curbside.automation.customerApp.android.pages.shop.HomePage;
import com.curbside.automation.customerApp.ios.pages.applicationLaunch.ApplicationLaunchPageIOS;
import com.curbside.automation.customerApp.ios.pages.settingsPage.SettingsIOS;
import io.appium.java_client.AppiumDriver;

/**
 * Created by hitesh.grover on 23/06/17.
 */
public class CustomerBaseTestCucumber {
    private AppiumDriver driver;
    ApplicationLaunchPageAndroid applicationLaunchPageAndroid;
   // Utilities utilities;
    ApplicationLaunchPageIOS iOSApplicationLaunchPageIOS;
    HomePage homePageAndroid;
    LocationPage locationPageAndroid;
    SettingsIOS settingsIOS;

    public CustomerBaseTestCucumber(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Intialization of class variable with driver
     *
     * Gets a Android application initialize
     * @return
     */
    public ApplicationLaunchPageAndroid getApplicationLaunchPageAndroid() {
        if (applicationLaunchPageAndroid == null)
            applicationLaunchPageAndroid = new ApplicationLaunchPageAndroid(this.driver);
        return applicationLaunchPageAndroid;
    }

    /**
     * Gets a iOS application initialize
     *
     * @return
     */
    public ApplicationLaunchPageIOS getiOSApplicationLaunch(){
        if (iOSApplicationLaunchPageIOS == null)
            iOSApplicationLaunchPageIOS = new ApplicationLaunchPageIOS(this.driver);
        return iOSApplicationLaunchPageIOS;
    }

//    public Utilities getUtilities(){
//        if (utilities == null)
//            utilities = new Utilities(this.driver);
//        return utilities;
//    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public HomePage getHomePageAndroid(){
        if (homePageAndroid == null)
            homePageAndroid = new HomePage(this.driver);
        return homePageAndroid;
    }


    /**
     * Intialization of class variable with driver
     * @return
     */
    public LocationPage getLocationPageAndroid(){
        if (locationPageAndroid == null)
            locationPageAndroid = new LocationPage(this.driver);
        return locationPageAndroid;
    }
    public SettingsIOS getIosSettings(){
        if (settingsIOS == null)
            settingsIOS = new SettingsIOS(this.driver);
        return settingsIOS;
    }
}
