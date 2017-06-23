package com.curbside.automation.android.testClasses;

import com.curbside.automation.customerApp.common.CustomerBaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover on 23/06/17.
 */
public class Shop extends CustomerBaseTest{

    /**
     *
     * @author hitesh.grover
     */
    @Test(groups = {"all", "stores", "shopping", "TCS-C"})
    public void storeList(){
        this.getApplicationLaunchPageAndroid().goToHomePage();
        this.getHomePageAndroid().getCurrentLocationButton().click();
        this.getLocationPageAndroid().getCurrentLocationTextField().sendKeys("Boston");
        this.getUtilities().hitEnter();

        Assert.assertTrue(this.getUtilities().isElementPresent(this.getHomePageAndroid().nearByStores));
    }


    }
