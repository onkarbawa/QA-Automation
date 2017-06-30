package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.DriverFactory;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;

/**
 * Created by hitesh.grover on 27/06/17.
 */
public class Common {

    BaseTest baseTest = new BaseTest();
    CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();

    @And("^I tap on 'Account' icon$")
    public void iClickOnAccountButton() throws Exception {
        Thread.sleep(2500);
        customerBaseTestCucumber.getCommonLocatorsPageAndroid().getAccountTabButton().click();
        customerBaseTestCucumber.getCommonLocatorsPageAndroid().getAccountTabButton().click();
        customerBaseTestCucumber.getCommonLocatorsPageAndroid().getAccountTabButton().click();
    }

    @Then("^I close the Curbside app$")
    public void iReleaseTheDriverSession() throws MalformedURLException {
        DriverFactory.releaseDriver();
    }

    @And("^I go to next text field$")
    public void iGoToNextTextField() throws Exception {
        Thread.sleep(1000);
        customerBaseTestCucumber.getUtilities().hitEnterAndroid();
    }

    @And("^I set the staging environment$")
    public void iSetTheStagingEnvironment() throws Exception {
        customerBaseTestCucumber.getHomePageAndroid().getSearchButton().click();
        Thread.sleep(1000);
        customerBaseTestCucumber.getHomePageAndroid().getProductSearchTextField().sendKeys("_#csndc#ena");
        customerBaseTestCucumber.getUtilities().hitEnterAndroid();
        Thread.sleep(2000);
        customerBaseTestCucumber.getUtilities().goBackAndroid();
        Thread.sleep(1000);
        customerBaseTestCucumber.getDebugPagePage().getApiHostButton().click();
        Thread.sleep(1000);
        customerBaseTestCucumber.getDebugPagePage().getApiHostDialogTextField().clear();
        customerBaseTestCucumber.getDebugPagePage().getApiHostDialogTextField().sendKeys("https://api-s.shopcurbside.com");
        customerBaseTestCucumber.getDebugPagePage().getApiHostDialogOkButton().click();
        Thread.sleep(1000);
        customerBaseTestCucumber.getUtilities().goBackAndroid();
        customerBaseTestCucumber.getDebugPagePage().getBackButton().click();
        AndroidDevice.launchCurbsideActivity();
    }

    @And("^I search and tap on'App permissions' on the screen$")
    public void iSearchAndTapOnAppPermissionsOnTheScreen() throws Exception {
        customerBaseTestCucumber.getSettingsAndroid().getSearchButton().click();
        customerBaseTestCucumber.getSettingsAndroid().getSearchTextField().sendKeys("App Permissions");
        customerBaseTestCucumber.getUtilities().goBackAndroid();
        customerBaseTestCucumber.getSettingsAndroid().getAppPermissions().click();
        Thread.sleep(1000);
        customerBaseTestCucumber.getSettingsAndroid().getAppPermissions().click();


    }

    @Given("^I reinstall the Curbside App$")
    public void iReinstallTheCurbsideApp() throws Exception {
        DriverFactory.releaseDriver();
        DriverFactory.getDriver();
    }
}
