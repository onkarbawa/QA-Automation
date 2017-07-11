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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.curbside.automation.devicefactory.DeviceStore;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

@SuppressWarnings("rawtypes")
public class DriverFactory {
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method must be called when creating a new driver instance
	 * @param platform parameter should have been defined in TestNG suite file
	 * @param additionalCaps Name Value pairs of additional capabilities that overwrites device info
	 * @return @WebDriver
	 * @throws Throwable
	 */
	public static WebDriver getDriver(JSONObject deviceInfo, Object[] additionalCaps) throws Throwable {
		if(deviceInfo != null)
			System.out.println(deviceInfo.toString());
        
		if (webDriver.get() == null) {
			
			DriverFactory.createInstance(
            		DeviceStore.getPlatform(), deviceInfo, additionalCaps);
        }
        return webDriver.get();
    }
	
	public static WebDriver getDriver(Object... additionalCaps) throws Throwable {
        return getDriver(null, additionalCaps);
    }
	
	public static WebDriver getDriver() throws Throwable {
        return getDriver(null, new String[]{});
    }
	
	public static WebDriver getDriver(boolean reinstall) throws Throwable {
		if(reinstall)
			return getDriver(null, new Object[]{"fullReset", true, "noReset",  false});
		else
			return getDriver(null, new Object[]{"fullReset", false, "noReset",  true});
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
	
	private static void createInstance(String platform, JSONObject deviceInfo, Object[] additionalCaps) throws Throwable
	{
		if (deviceInfo == null)
		{
			System.out.println("Getting a new device from store");
			deviceInfo= new JSONObject(DeviceStore.getDevice().toString());
		}
		
		URL url= new URL(deviceInfo.get("url").toString());
		
		DesiredCapabilities caps = new DesiredCapabilities();
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
		
		for (int i = 0; i < additionalCaps.length; i=i+2) {
			caps.setCapability(additionalCaps[i].toString(), additionalCaps[i+1]);
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
		
		System.out.println("Actual device capabilities: " +
						((AppiumDriver)getDriver()).getCapabilities().asMap());
		
		//System.out.println("Device screenshot captured at " + MobileDevice.takeScreenshot().getAbsolutePath());
		//new ImageElement(new File("src/test/resources/ios/elements/DontAllow.png")).tap();
	}

	public static void hideKeyboard() {
		try {
			((AppiumDriver) getDriver()).hideKeyboard();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
}
