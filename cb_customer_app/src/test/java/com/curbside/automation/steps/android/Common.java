package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.DriverFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

/**
 * Created by hitesh.grover on 27/06/17.
 */
public class Common {

    BaseTest baseTest = new BaseTest();
    CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();


    @And("^I set the staging environment$")
    public void iSetTheStagingEnvironment() throws Throwable {
        customerBaseTestCucumber.getHomePageAndroid().getSearchButton().click();
        Thread.sleep(1000);
        customerBaseTestCucumber.getHomePageAndroid().getProductSearchTextField().sendKeys("_#csndc#ena");
        AndroidDevice.hitEnter();
        Thread.sleep(2000);
        AndroidDevice.goBack();
        Thread.sleep(1000);
        customerBaseTestCucumber.getDebugPagePage().getApiHostButton().click();
        Thread.sleep(1000);
        customerBaseTestCucumber.getDebugPagePage().getApiHostDialogTextField().clear();
        customerBaseTestCucumber.getDebugPagePage().getApiHostDialogTextField().sendKeys("https://api-s.shopcurbside.com");
        customerBaseTestCucumber.getDebugPagePage().getApiHostDialogOkButton().click();
        Thread.sleep(1000);
        AndroidDevice.goBack();
        customerBaseTestCucumber.getDebugPagePage().getBackButton().click();
        AndroidDevice.launchCurbsideActivity();
    }

    @And("^I search and tap on'App permissions' on the screen$")
    public void iSearchAndTapOnAppPermissionsOnTheScreen() throws Throwable {
        customerBaseTestCucumber.getSettingsAndroid().getSearchButton().click();
        customerBaseTestCucumber.getSettingsAndroid().getSearchTextField().sendKeys("App Permissions");
        AndroidDevice.goBack();
        customerBaseTestCucumber.getSettingsAndroid().getAppPermissions().click();
        Thread.sleep(1000);
        customerBaseTestCucumber.getSettingsAndroid().getAppPermissions().click();


    }

    @Given("^I reinstall the Curbside App$")
    public void iReinstallTheCurbsideApp() throws Throwable {
        DriverFactory.releaseDriver();
        DriverFactory.getDriver();
    }
}
