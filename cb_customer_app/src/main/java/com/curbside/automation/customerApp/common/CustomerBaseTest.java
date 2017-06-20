package com.curbside.automation.customerApp.common;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.android.applicationLaunch.ApplicationLaunchPage;
import com.curbside.automation.customerApp.ios.applicationLaunch.ApplicationLaunch;

/**
 * Created by hitesh.grover on 16/06/17.
 */
public class CustomerBaseTest extends BaseTest {

    /**
     * Reference variables of Pages(Classes)
     */
    ApplicationLaunchPage applicationLaunchPage;
    ApplicationLaunch iOSApplicationLaunch;

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
    public ApplicationLaunch getiOSApplicationLaunch(){
        if (iOSApplicationLaunch == null)
            iOSApplicationLaunch = new ApplicationLaunch(this.driver);
        return iOSApplicationLaunch;
    }

}
