package com.curbside.automation.customerApp.common;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.android.applicationLaunch.ApplicationLaunch;

/**
 * Created by hitesh.grover on 16/06/17.
 */
public class CustomerBaseTest extends BaseTest {

    ApplicationLaunch applicationLaunch;

    public ApplicationLaunch getApplicationLaunch() {
        if (applicationLaunch == null)
            applicationLaunch = new ApplicationLaunch(this.driver);
        return applicationLaunch;
    }

}
