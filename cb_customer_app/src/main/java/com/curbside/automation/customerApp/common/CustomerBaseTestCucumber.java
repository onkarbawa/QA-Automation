package com.curbside.automation.customerApp.common;

import com.curbside.automation.common.utilities.Utilities;
import com.curbside.automation.customerApp.android.pages.account.Account;
import com.curbside.automation.customerApp.android.pages.applicationLaunch.ApplicationLaunchPageAndroid;
import com.curbside.automation.customerApp.android.pages.common.CommonLocators;
import com.curbside.automation.customerApp.android.pages.location.LocationPage;
import com.curbside.automation.customerApp.android.pages.shop.HomePage;
import com.curbside.automation.customerApp.android.pages.signInSignUp.SignInSignUp;
import com.curbside.automation.customerApp.ios.pages.applicationLaunch.ApplicationLaunchPageIOS;
import com.curbside.automation.customerApp.ios.pages.settingsPage.SettingsIOS;
import io.appium.java_client.AppiumDriver;

/**
 * Created by hitesh.grover on 23/06/17.
 */
public class CustomerBaseTestCucumber {
    private AppiumDriver driver;
    ApplicationLaunchPageAndroid applicationLaunchPageAndroid;
    Utilities utilities;
    ApplicationLaunchPageIOS iOSApplicationLaunchPageIOS;
    HomePage homePageAndroid;
    LocationPage locationPageAndroid;
    CommonLocators commonLocators;
    Account account;
    SignInSignUp signInSignUp;

    public CustomerBaseTestCucumber(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Intialization of class variable with driver
     *
     * Gets a Android application initialize
     * @return
     */
    public ApplicationLaunchPageAndroid getApplicationLaunchPageAndroid() {
        if (applicationLaunchPageAndroid == null)
            applicationLaunchPageAndroid = new ApplicationLaunchPageAndroid(this.driver);
        return applicationLaunchPageAndroid;
    }

    /**
     * Gets a iOS application initialize
     *
     * @return
     */
    public ApplicationLaunchPageIOS getiOSApplicationLaunch(){
        if (iOSApplicationLaunchPageIOS == null)
            iOSApplicationLaunchPageIOS = new ApplicationLaunchPageIOS(this.driver);
        return iOSApplicationLaunchPageIOS;
    }

    public Utilities getUtilities(){
        if (utilities == null)
            utilities = new Utilities(this.driver);
        return utilities;
    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public HomePage getHomePageAndroid(){
        if (homePageAndroid == null)
            homePageAndroid = new HomePage(this.driver);
        return homePageAndroid;
    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public LocationPage getLocationPageAndroid(){
        if (locationPageAndroid == null)
            locationPageAndroid = new LocationPage(this.driver);
        return locationPageAndroid;
    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public CommonLocators getCommonLocatorsPageAndroid(){
        if (commonLocators == null)
            commonLocators = new CommonLocators(this.driver);
        return commonLocators;
    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public Account getAccountPageAndroid(){
        if (account == null)
            account = new Account(this.driver);
        return account;
    }

    /**
     * Intialization of class variable with driver
     * @return
     */
    public SignInSignUp getSignInSignUpPage(){
        if (signInSignUp == null)
            signInSignUp = new SignInSignUp(this.driver);
        return signInSignUp;
    }
   }
