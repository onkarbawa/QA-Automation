package com.curbside.automation.uifactory;

/**
 * @author kumar.anil
 *
 */

import java.io.File;
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
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method must be called when creating a new driver instance
	 * @param platform iOS/ Android
	 * @return @WebDriver
	 * @throws Exception
	 */
	public static WebDriver getDriver(String platform) throws Exception {
        if (webDriver.get() == null) {
            DriverFactory.createInstance(platform);
        }
        return webDriver.get();
    }
	
	/**
	 * This method should be called when tests are already in progress
	 * @return @WebDriver
	 * @throws MalformedURLException
	 */
	public static WebDriver getDriver() throws MalformedURLException {
        return webDriver.get();
    }
	
	/**
	 * This method should be called when tests are exiting, usually by a test listener
	 * @throws MalformedURLException
	 */
	public static void releaseDriver() throws MalformedURLException {
		WebDriver driver = webDriver.get();
        if (driver != null) {
            driver.quit();
        }
    }
	
	private static void setDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverFactory.webDriver.set(driver);
    }
	
	private static void createInstance(String platform) throws Exception
	{
		JSONObject device= DeviceStore.getDevice(platform);
		URL url= new URL(device.get("url").toString());
		device.remove("url");
		
		DesiredCapabilities caps = new DesiredCapabilities();
		Iterator<?> keys = device.keySet().iterator();
		while( keys.hasNext() ) {
		    String key = (String)keys.next();
		    
		    if(key.equalsIgnoreCase("app") || key.equalsIgnoreCase("ipa") )
		    	caps.setCapability(key, new File(device.get(key).toString()).getAbsolutePath());
		    else
		    	caps.setCapability(key, device.get(key));
		}
		
		switch (platform.toLowerCase()) {
		case "ios":
			setDriver(new IOSDriver(url,caps));
			break;
		case "android":
			setDriver(new AndroidDriver(url,caps));
			break;
		default:
			throw new Exception("Unknown platform: " + platform);
		}
	}
}
