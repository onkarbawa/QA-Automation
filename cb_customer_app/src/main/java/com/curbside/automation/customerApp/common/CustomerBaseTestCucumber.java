package com.curbside.automation.customerApp.common;

import com.curbside.automation.common.utilities.Utilities;
import com.curbside.automation.customerApp.android.pages.SettingsAndroid;
import com.curbside.automation.customerApp.android.pages.SettingsAndroid;
import com.curbside.automation.customerApp.android.pages.account.Account;
import com.curbside.automation.customerApp.android.pages.applicationLaunch.ApplicationLaunchPageAndroid;
import com.curbside.automation.customerApp.android.pages.common.CommonLocators;
import com.curbside.automation.customerApp.android.pages.location.LocationPage;
import com.curbside.automation.customerApp.android.pages.shop.HomePage;
import com.curbside.automation.customerApp.android.pages.signInSignUp.SignInSignUp;
import com.curbside.automation.customerApp.android.pages.staging.DebugPage;
import com.curbside.automation.uifactory.DriverFactory;
import io.appium.java_client.AppiumDriver;

/**
 * Created by hitesh.grover on 23/06/17.
 */
public class CustomerBaseTestCucumber {
    private AppiumDriver driver;
    ApplicationLaunchPageAndroid applicationLaunchPageAndroid;
    Utilities utilities;
    HomePage homePageAndroid;
    LocationPage locationPageAndroid;
    CommonLocators commonLocators;
    Account account;
    SignInSignUp signInSignUp;
    DebugPage debugPage;
    SettingsAndroid settingsAndroid;


    /**
     * Intialization of class variable with driver
     *
     * Gets a Android application initialize
     * @return
     */
    public ApplicationLaunchPageAndroid getApplicationLaunchPageAndroid() throws Throwable {
        applicationLaunchPageAndroid = new ApplicationLaunchPageAndroid((AppiumDriver) DriverFactory.getDriver());
        return applicationLaunchPageAndroid;
    }

    public Utilities getUtilities() throws Throwable {
        utilities = new Utilities((AppiumDriver) DriverFactory.getDriver());
        return utilities;
    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public HomePage getHomePageAndroid() throws Throwable {
        homePageAndroid = new HomePage((AppiumDriver) DriverFactory.getDriver());
        return homePageAndroid;
    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public LocationPage getLocationPageAndroid() throws Throwable {
        locationPageAndroid = new LocationPage((AppiumDriver) DriverFactory.getDriver());
        return locationPageAndroid;
    }

    /**
     * Intialization of class variable with driver
     * @return
     * @throws Throwable 
     */
    public CommonLocators getCommonLocatorsPageAndroid() throws Throwable {
        commonLocators = new CommonLocators((AppiumDriver) DriverFactory.getDriver());
        return commonLocators;
    }

    /**
     * Intialization of class variable with driver
     * @return
     * @throws Throwable 
     */
    public Account getAccountPageAndroid() throws Throwable {
        account = new Account((AppiumDriver) DriverFactory.getDriver());
        return account;
    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public SignInSignUp getSignInSignUpPage() throws Throwable {
        signInSignUp = new SignInSignUp((AppiumDriver) DriverFactory.getDriver());
        return signInSignUp;
    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public DebugPage getDebugPagePage() throws Throwable {
        debugPage = new DebugPage((AppiumDriver) DriverFactory.getDriver());
        return debugPage;
    }

    public SettingsAndroid getSettingsAndroid() throws Throwable {
        settingsAndroid = new SettingsAndroid((AppiumDriver) DriverFactory.getDriver());
        return settingsAndroid;
    }
   }
