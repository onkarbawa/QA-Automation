package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import com.curbside.automation.uifactory.DriverFactory;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

/**
 * Created by hitesh.grover on 27/06/17.
 */
public class Common {

    BaseTest baseTest = new BaseTest();
    CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();

    @And("^I click on 'Account' icon$")
    public void iClickOnAccountButton() throws Exception {
        Thread.sleep(1000);
        customerBaseTestCucumber.getCommonLocatorsPageAndroid().getAccountTabButton().click();
    }

    @Then("^I close the Curbside app$")
    public void iReleaseTheDriverSession() throws MalformedURLException {
        DriverFactory.releaseDriver();
    }

    @And("^I go to next text field$")
    public void iGoToNextTextField() throws Exception {
        customerBaseTestCucumber.getUtilities().hitEnter();
    }

    @And("^I set the staging environment$")
    public void iSetTheStagingEnvironment() throws Exception {
        customerBaseTestCucumber.getHomePageAndroid().getSearchButton().click();
        Thread.sleep(1000);
        customerBaseTestCucumber.getHomePageAndroid().getSearchButton().sendKeys("_#csndc#ena");
        Thread.sleep(2000);
        customerBaseTestCucumber.getDebugPagePage().getBackButton().click();
        Thread.sleep(1000);
        customerBaseTestCucumber.getDebugPagePage().getApiHostButton().click();
        customerBaseTestCucumber.getDebugPagePage().getApiHostDialogTextField().clear();
        customerBaseTestCucumber.getDebugPagePage().getApiHostDialogTextField().sendKeys("https://api-s.shopcurbside.com");
        customerBaseTestCucumber.getDebugPagePage().getApiHostDialogOkButton().click();
        Thread.sleep(1000);
        customerBaseTestCucumber.getDebugPagePage().getBackButton().click();
//        AndroidDriver<WebElement>>DriverFactory.getDriver().startActivity("com.curbside.nCurbside", "com.curbside.nCurbside.app.help.SplashScreenActivity");
//        (AndroidDriver<WebElement>) DriverFactory.getDriver().startActivity("com.curbside.nCurbside", "com.curbside.nCurbside.app.help.SplashScreenActivity");
//        AndroidDriver<WebElement> driver =(AndroidDriver<WebElement>) DriverFactory.getDriver();
//        driver.startActivity("com.curbside.nCurbside.app.help.SplashScreenActivity");
//        ((AndroidDriver) driver).startActivity(com.curbside.nCurbside.app.help.SplashScreenActivity);
    }
}
