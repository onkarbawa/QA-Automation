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
import com.curbside.automation.customerApp.ios.pages.applicationLaunch.ApplicationLaunchPageIOS;
import com.curbside.automation.customerApp.ios.pages.facebookLogin.FacebookLoginIOS;
import com.curbside.automation.customerApp.ios.pages.home.HomePageIOS;
import com.curbside.automation.customerApp.ios.pages.login.LoginPageIOS;
import com.curbside.automation.customerApp.ios.pages.myAccount.AccountInfoIOS;
import com.curbside.automation.customerApp.ios.pages.myAccount.PaymentInfo;
import com.curbside.automation.customerApp.ios.pages.settingsPage.SettingsIOS;
import com.curbside.automation.customerApp.ios.pages.signInSignUp.SignInSignUpPageIOS;
import com.curbside.automation.customerApp.ios.pages.signUp.SignUpPageIOS;
import com.curbside.automation.uifactory.DriverFactory;
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
    DebugPage debugPage;
    SettingsAndroid settingsAndroid;
    AccountInfoIOS accountInfoIOS;
    SignInSignUpPageIOS signInSignUpPageIOS;
    SignUpPageIOS signUpPageIOS;
    HomePageIOS homePageIOS;
    LoginPageIOS loginPageIOS;
    PaymentInfo paymentInfo;
    FacebookLoginIOS facebookLoginIOS;


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

    /**
     * Gets a iOS application initialize
     *
     * @return
     */
    public ApplicationLaunchPageIOS getiOSApplicationLaunch() throws Throwable {
        iOSApplicationLaunchPageIOS = new ApplicationLaunchPageIOS((AppiumDriver) DriverFactory.getDriver());
        return iOSApplicationLaunchPageIOS;
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
    /**
     * Intialization of class variable with driver
     * @return
     */
    public AccountInfoIOS getAccountInfoIOS() throws Throwable {
        accountInfoIOS = new AccountInfoIOS((AppiumDriver) DriverFactory.getDriver());
        return accountInfoIOS;
    }

    public SignInSignUpPageIOS getSignInSignUpPageIOS() throws Throwable {
        signInSignUpPageIOS = new SignInSignUpPageIOS((AppiumDriver) DriverFactory.getDriver());
        return signInSignUpPageIOS;
    }

    public SignUpPageIOS getSignUpPageIOS() throws Throwable {
        signUpPageIOS = new SignUpPageIOS((AppiumDriver) DriverFactory.getDriver());
        return signUpPageIOS;
    }
    public HomePageIOS getHomePageIOS() throws Throwable {
        homePageIOS = new HomePageIOS((AppiumDriver) DriverFactory.getDriver());
        return homePageIOS;
    }
    public LoginPageIOS getLoginPageIOS() throws Throwable {
        loginPageIOS = new LoginPageIOS((AppiumDriver) DriverFactory.getDriver());
        return loginPageIOS;
    }
    public PaymentInfo getPaymentInfo() throws Throwable {
        paymentInfo = new PaymentInfo((AppiumDriver) DriverFactory.getDriver());
        return paymentInfo;
    }
    public FacebookLoginIOS getFacebookLoginIOS() throws Throwable {
        facebookLoginIOS = new FacebookLoginIOS((AppiumDriver) DriverFactory.getDriver());
        return facebookLoginIOS;
    }
   }
