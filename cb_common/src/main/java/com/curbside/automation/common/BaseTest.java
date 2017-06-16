package com.curbside.automation.common;

import com.curbside.automation.common.configuration.Properties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected AppiumDriver driver;
    private String platForm;

    @BeforeSuite
//    @Parameters(value = "platForm")
    public void setUp(){
        System.out.println(Properties.getPlatForm());
        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        DesiredCapabilities caps = new DesiredCapabilities();
//        if (platForm.equalsIgnoreCase("iOS")) {
//            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "TFT");
//            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
//            caps.setCapability(MobileCapabilityType.UDID, "0ddb226173711460741617d2d10e0dcd734755d9");
//            caps.setCapability(MobileCapabilityType.APP, "/Users/tft/Downloads/Curbside.debug.ipa");
//            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3.2");
//            driver = new IOSDriver(url,caps);
//        }
//        else if (platForm.equalsIgnoreCase("Android")){
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.APP, "/Users/tft/Desktop/Curbside.apk");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 65);
            caps.setCapability("appPackage", "com.curbside.nCurbside");
            caps.setCapability("appActivity","com.curbside.nCurbside.app.help.SplashScreenActivity");
            driver = new AndroidDriver<WebElement>(url,caps);

//        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
}
