package com.curbside.automation.customerApp.common;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.common.utilities.Utilities;
import com.curbside.automation.customerApp.android.pages.applicationLaunch.ApplicationLaunchPage;
import com.curbside.automation.customerApp.android.pages.shop.HomePage;
import com.curbside.automation.customerApp.ios.pages.applicationLaunch.ApplicationLaunchPageIOS;

/**
 * Created by hitesh.grover on 16/06/17.
 */
public class CustomerBaseTest extends BaseTest {

    /**
     * Reference variables of Pages(Classes)
     */
    ApplicationLaunchPage applicationLaunchPage;
    Utilities utilities;
    ApplicationLaunchPageIOS iOSApplicationLaunchPageIOS;
    HomePage homePage;

    /**
     * Intialization of class variable with driver
     *
     * Gets a Android application initialize
     * @return
     */
    public ApplicationLaunchPage getApplicationLaunchPage() {
        if (applicationLaunchPage == null)
            applicationLaunchPage = new ApplicationLaunchPage(this.driver);
        return applicationLaunchPage;
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

    public Utilities getUtilities(){
        if (utilities == null)
            utilities = new Utilities(this.driver);
        return utilities;
    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public HomePage getHomePage(){
        if (homePage == null)
            homePage = new HomePage(this.driver);
        return homePage;
    }

}
