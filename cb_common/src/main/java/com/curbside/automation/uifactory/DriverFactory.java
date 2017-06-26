package com.curbside.automation.uifactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver() throws MalformedURLException {
        if (driver.get() == null) {
            setDriver(DriverFactory.createInstance());
        }
        return driver.get();
    }
	
	public static void setDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverFactory.driver.set(driver);
    }
	
	public static WebDriver createInstance() throws MalformedURLException
	{
		URL url= new URL("http://127.0.0.1:4723/wd/hub");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Anil Kumar’s iPhone");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        caps.setCapability(MobileCapabilityType.UDID, "05d1e37141f8057d0b5b2acae52c359012455afe");
        caps.setCapability(MobileCapabilityType.APP, "/Users/anilk/workspace/tft/Curbside.ipa");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3.3");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        caps.setCapability("bundleId", "cash.uchange.ios");
        DriverFactory.setDriver(new IOSDriver(url,caps));
        
        return getDriver();
	}
}
