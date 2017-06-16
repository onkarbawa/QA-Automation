package com.curbside.automation.android.testClasses;

import com.curbside.automation.customerApp.android.applicationLaunch.ApplicationLaunch;
import com.curbside.automation.customerApp.common.CustomerBaseTest;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover on 16/06/17.
 */
public class AppLaunch extends CustomerBaseTest {


    @Test
    public void launchApp(){

        try {
            this.getApplicationLaunch().getErrorOkButton().click();

        } catch (Exception e){
            System.out.print("In catch");
            this.getApplicationLaunch().getSkipIntro().click();
        }

    }
//
//    public ApplicationLaunch getApplicationPage() {
//        return new ApplicationLaunch(this.driver);
//    }
}
