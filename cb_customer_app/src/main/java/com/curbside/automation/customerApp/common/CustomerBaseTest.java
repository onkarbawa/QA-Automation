package com.curbside.automation.customerApp.common;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.android.applicationLaunch.ApplicationLaunchPage;

/**
 * Created by hitesh.grover on 16/06/17.
 */
public class CustomerBaseTest extends BaseTest {

    ApplicationLaunchPage applicationLaunchPage;

    public ApplicationLaunchPage getApplicationLaunchPage() {
        if (applicationLaunchPage == null)
            applicationLaunchPage = new ApplicationLaunchPage(this.driver);
        return applicationLaunchPage;
    }

}
