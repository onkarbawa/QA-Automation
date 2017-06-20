package com.curbside.automation.common;

import com.curbside.automation.common.configuration.Properties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class BaseTest {

    protected AppiumDriver driver;
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


    @BeforeSuite
    public void setUp(){
        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        DesiredCapabilities caps = new DesiredCapabilities();
        if (platForm.equalsIgnoreCase("iOS")) {
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, iosDeviceName);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platForm);
            caps.setCapability(MobileCapabilityType.UDID, iosUDID);
            caps.setCapability(MobileCapabilityType.APP, iosAppLocation);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, iosPlatformVersion);
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
}
