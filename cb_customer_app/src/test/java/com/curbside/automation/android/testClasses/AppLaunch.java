package com.curbside.automation.android.testClasses;

import com.curbside.automation.customerApp.common.CustomerBaseTest;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover on 16/06/17.
 */
public class AppLaunch extends CustomerBaseTest {


    @Test
    public void launchApp(){

        try {
            this.getApplicationLaunchPage().getErrorOkButton().click();

        } catch (Exception e){
            System.out.print("In catch");
            this.getApplicationLaunchPage().getSkipIntro().click();
        }

    }

    @Test
    public void gotoHomePage(){
        this.getApplicationLaunchPage().gotoHomepage();
    }
}

