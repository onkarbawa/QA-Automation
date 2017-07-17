package com.curbside.automation.uifactory;

/**
 * @author kumar.anil
 *
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.curbside.automation.devicefactory.DeviceStore;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

@SuppressWarnings("rawtypes")
public class DriverFactory {
	private static final Logger logger = Logger.getLogger(DriverFactory.class);
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<Capabilities> driverInfo = new ThreadLocal<Capabilities>();
	public static ThreadLocal<Dimension> deviceSize = new ThreadLocal<Dimension>();
	
	/**
	 * This method must be called when creating a new driver instance
	 * @param platform parameter should have been defined in TestNG suite file
	 * @param additionalCaps Name Value pairs of additional capabilities that overwrites device info
	 * @return @WebDriver
	 * @throws Throwable
	 */
	public static WebDriver getDriver(JSONObject deviceInfo) throws Throwable {
		if(deviceInfo != null)
			System.out.println(deviceInfo.toString());
        
		if (webDriver.get() == null) {
			
			DriverFactory.createInstance(
            		DeviceStore.getPlatform(), deviceInfo);
        }
        return webDriver.get();
    }
	
	public static WebDriver getDriver() throws Throwable {
        return getDriver(null);
    }
	
	public static WebDriver getDriver(boolean reinstall) throws Throwable {
		return getDriver(reinstall, false);
    }
	
	public static WebDriver getDriver(boolean reinstall, boolean givePermissions) throws Throwable {
		JSONObject deviceInfo= new JSONObject(DeviceStore.getDevice().toString());
		if(!reinstall)
		{
			deviceInfo.remove("app");
			deviceInfo.remove("ipa");
		}
		deviceInfo.put("noReset", true);
		
		if(givePermissions)
			deviceInfo.put("autoGrantPermissions", true);
		
		return getDriver(deviceInfo);
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
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverFactory.webDriver.set(driver);
    }
	
	public static Map<String, ?> getDriverInfo() {
        return driverInfo.get().asMap();
    }
	
	private static void createInstance(String platform, JSONObject deviceInfo) throws Throwable
	{
		if (deviceInfo == null)
		{
			System.out.println("Getting a new device from store");
			deviceInfo= new JSONObject(DeviceStore.getDevice().toString());
		}
		
		URL url= new URL(deviceInfo.get("url").toString());
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("sendKeyStrategy", "grouped");
		
		Iterator<?> keys = deviceInfo.keySet().iterator();
		while( keys.hasNext() ) {
		    String key = (String)keys.next();
		    
		    if(key.equalsIgnoreCase("url") || key.equalsIgnoreCase("passcode"))
		    	continue;
		    
		    if(key.equalsIgnoreCase("app") || key.equalsIgnoreCase("ipa") )
		    	caps.setCapability(key, new File(deviceInfo.get(key).toString()).getAbsolutePath());
		    else
		    	caps.setCapability(key, deviceInfo.get(key));
		}
		
		switch (platform.toLowerCase()) {
		case "ios":
			setDriver(new IOSDriver(url, caps));
			UIElement.byAccessibilityId("Trust").tapOptional();
			break;
		case "android":
			setDriver(new AndroidDriver(url, caps));
			break;
		default:
			throw new Exception("Unknown platform: " + platform);
		}
		
		driverInfo.set(((AppiumDriver)(webDriver.get())).getCapabilities());
		System.out.println("Actual device capabilities: " +driverInfo.get().asMap());
		deviceSize.set(webDriver.get().manage().window().getSize());
		MobileDevice.logDeviceInfo();
		
		//System.out.println("Device screenshot captured at " + MobileDevice.takeScreenshot().getAbsolutePath());
		//new ImageElement(new File("src/test/resources/ios/elements/DontAllow.png")).tap();
	}
	
	public static Dimension getSize()
	{
		return deviceSize.get();
	}
}
