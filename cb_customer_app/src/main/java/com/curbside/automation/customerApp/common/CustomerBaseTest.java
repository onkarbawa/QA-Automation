package com.curbside.automation.customerApp.common;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.common.utilities.Utilities;
import com.curbside.automation.customerApp.android.pages.applicationLaunch.ApplicationLaunchPageAndroid;
import com.curbside.automation.customerApp.android.pages.location.LocationPage;
import com.curbside.automation.customerApp.android.pages.shop.HomePage;

/**
 * Created by hitesh.grover on 16/06/17.
 */
public class CustomerBaseTest extends BaseTest {

    /**
     * Reference variables of Pages(Classes)
     */
    ApplicationLaunchPageAndroid applicationLaunchPageAndroid;
    Utilities utilities;
    HomePage homePageAndroid;
    LocationPage locationPageAndroid;

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

    public Utilities getUtilities(){
        if (utilities == null)
            utilities = new Utilities(this.driver);
        return utilities;
    }

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
}
