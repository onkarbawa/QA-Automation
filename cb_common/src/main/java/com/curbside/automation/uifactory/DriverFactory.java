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

import org.json.JSONObject;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.TestRunner;

import com.curbside.automation.devicefactory.DeviceStore;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method must be called when creating a new driver instance
	 * @param platform parameter should have been defined in TestNG suite file
	 * @return @WebDriver
	 * @throws Exception
	 */
	public static WebDriver getDriver(JSONObject deviceInfo) throws Exception {
		if(deviceInfo != null)
			System.out.println(deviceInfo.toString());
        
		if (webDriver.get() == null) {
            DriverFactory.createInstance(
            		Reporter.getCurrentTestResult().getTestContext().getAttribute("platform").toString(), deviceInfo);
        }
        return webDriver.get();
    }
	
	public static WebDriver getDriver()  {
        try{
        	return getDriver(null);
		}catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
		}
    }
	
	/**
	 * This method should be called when tests are exiting, usually by a test listener
	 * @throws MalformedURLException
	 */
	public static void releaseDriver() throws MalformedURLException {
		WebDriver driver = webDriver.get();
        if (driver != null) {
            driver.quit();
            webDriver.set(null);
        }
    }
	
	private static void setDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverFactory.webDriver.set(driver);
    }
	
	private static void createInstance(String platform, JSONObject deviceInfo) throws Exception
	{
		if (deviceInfo == null)
		{
			System.out.println("Getting a new device from store");
			deviceInfo= new JSONObject(DeviceStore.getDevice().toString());
		}
		
		URL url= new URL(deviceInfo.get("url").toString());
		deviceInfo.remove("url");
		
		DesiredCapabilities caps = new DesiredCapabilities();
		Iterator<?> keys = deviceInfo.keySet().iterator();
		while( keys.hasNext() ) {
		    String key = (String)keys.next();
		    
		    if(key.equalsIgnoreCase("app") || key.equalsIgnoreCase("ipa") )
		    	caps.setCapability(key, new File(deviceInfo.get(key).toString()).getAbsolutePath());
		    else
		    	caps.setCapability(key, deviceInfo.get(key));
		}
		
		switch (platform.toLowerCase()) {
		case "ios":
			setDriver(new IOSDriver(url, caps));
			break;
		case "android":
			setDriver(new AndroidDriver(url, caps));
			break;
		default:
			throw new Exception("Unknown platform: " + platform);
		}
	}
}
