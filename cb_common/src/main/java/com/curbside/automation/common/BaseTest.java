package com.curbside.automation.common;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class BaseTest {

    public static AppiumDriver driver;
    private String platForm = Properties.getPlatForm();
    private int newCommandTimeout=Properties.getNewCommandTimeout();
    private String androidDeviceName = Properties.getAndroidDeviceName();
    private String androidAppLocation = Properties.getAndroidAppLocation();
    private String androidAppPackage = Properties.getAndroidAppPackage();
    private String androidAppActivity = Properties.getAndroidAppActivity();
    private String iosDeviceName = Properties.getIosDeviceName();
    private String iosAppLocation = Properties.getIosAppLocation();
    private String iosUDID = Properties.getIosDeviceUDID();
    private String iosPlatformVersion = Properties.getIosPlatformVersion();
    private int implicitWaitInSeconds = Properties.getImplicitWaitInSeconds();

    URL url = null;
    DesiredCapabilities caps = new DesiredCapabilities();

   // @BeforeSuite
    public void setUp(){
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (platForm.equalsIgnoreCase("iOS")) {
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, iosDeviceName);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platForm);
            caps.setCapability(MobileCapabilityType.UDID, iosUDID);
            caps.setCapability(MobileCapabilityType.APP, iosAppLocation);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, iosPlatformVersion);
            caps.setCapability(MobileCapabilityType.FULL_RESET,true);
            caps.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES,true);
            driver = new IOSDriver(url,caps);

        }
        else if (platForm.equalsIgnoreCase("Android")){
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, androidDeviceName);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platForm);
            caps.setCapability(MobileCapabilityType.APP, androidAppLocation);
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, newCommandTimeout);
            caps.setCapability("appPackage", androidAppPackage);
            caps.setCapability("appActivity", androidAppActivity);
            driver = new AndroidDriver<WebElement>(url,caps);

        }
        driver.manage().timeouts().implicitlyWait(implicitWaitInSeconds, TimeUnit.SECONDS);

    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }

    public void getIOSSettingApp(){
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, iosDeviceName);
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platForm);
        caps.setCapability(MobileCapabilityType.UDID, iosUDID);
        caps.setCapability(MobileCapabilityType.APP, "settings");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, iosPlatformVersion);
        driver = new IOSDriver(url,caps);
    }
}
