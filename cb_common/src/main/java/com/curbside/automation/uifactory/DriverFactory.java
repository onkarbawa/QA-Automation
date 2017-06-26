package com.curbside.automation.uifactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.curbside.automation.devicefactory.DeviceStore;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver(String platform) throws Exception {
        if (driver.get() == null) {
            DriverFactory.createInstance(platform);
        }
        return driver.get();
    }
	
	public static WebDriver getDriver() throws MalformedURLException {
        return driver.get();
    }
	
	public static void setDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverFactory.driver.set(driver);
    }
	
	public static void createInstance(String platform) throws Exception
	{
		JSONObject device= DeviceStore.getDeviceByPlatform(platform);
		URL url= new URL(device.get("url").toString());
		device.remove("url");
		
		DesiredCapabilities caps = new DesiredCapabilities();
		Iterator<?> keys = device.keySet().iterator();
		while( keys.hasNext() ) {
		    String key = (String)keys.next();
		    caps.setCapability(key, device.get(key));
		}
		
		switch (platform.toLowerCase()) {
		case "ios":
			DriverFactory.setDriver(new IOSDriver(url,caps));
			break;
		case "android":
			DriverFactory.setDriver(new AndroidDriver(url,caps));
			break;
		default:
			throw new Exception("Unknown platform: " + platform);
		}
		
	}
}
