package com.curbside.automation.uifactory;

import java.awt.image.BufferedImage;

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

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.curbside.automation.appfactory.AppStore;
import com.curbside.automation.devicefactory.AppiumService;
import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.devicefactory.IOSApps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

@SuppressWarnings("rawtypes")
public class DriverFactory {
	private static final Logger logger = Logger.getLogger(DriverFactory.class);
	static HashMap<Object, WebDriver> driverMap= new HashMap<>();
	static HashMap<Object, String> driverEnvironment= new HashMap<>();
	static ThreadLocal<Capabilities> driverInfo = new ThreadLocal<Capabilities>();
	static ThreadLocal<Dimension> deviceSize = new ThreadLocal<Dimension>();
	
	public static WebDriver getDriver(Object deviceInfo) throws Throwable {
		return driverMap.get(DeviceStore.getLockedDevice());
	}

	public static WebDriver getDriver() throws Throwable {
		return getDriver(DeviceStore.getLockedDevice());
	}

	public static WebDriver createDriver(boolean reinstall) throws Throwable {
		return createDriver(reinstall, false);
	}

	public static WebDriver createDriver(boolean reinstall, boolean givePermissions) throws Throwable {
		JSONObject deviceInfo = new JSONObject(DeviceStore.getDevice().toString());

		if (!reinstall && DeviceStore.isAppInstalled()) {
			deviceInfo.remove("app");
			deviceInfo.remove("ipa");
			deviceInfo.put("noReset", true);
		} else {
			DriverFactory.clearEnvironment();
			deviceInfo.put("noReset", false);
		}
		deviceInfo.put("fullReset", false);

		if (givePermissions)
			deviceInfo.put("autoGrantPermissions", true);
		
		createInstance(deviceInfo.getString("platformName"), deviceInfo);
		
		DeviceStore.setAppInstalled(AppStore.getAppName());
		return getDriver();
	}

	/**
	 * This method should be called when tests are exiting, usually by a test
	 * listener
	 * @throws Throwable 
	 */
	public static void releaseDriver() throws Throwable {
		if (driverMap.containsKey(DeviceStore.getLockedDevice())) {
			driverMap.get(DeviceStore.getLockedDevice()).quit();
			driverMap.remove(DeviceStore.getLockedDevice());
		}
	}

	private static void setDriver(WebDriver driver) throws Throwable {
		driverMap.put(DeviceStore.getLockedDevice(), driver);
	}

	public static Map<String, ?> getDriverInfo() throws Throwable {
		if(driverInfo.get() == null)
			driverInfo.set(((RemoteWebDriver)getDriver()).getCapabilities());
		
		return driverInfo.get().asMap();
	}

	public static synchronized void createInstance(String platform, JSONObject deviceInfo) throws Throwable {
		try {
			if(deviceInfo.getString("url").contains("127.0.0.1")
					/*&& deviceInfo.getString("platformName").equalsIgnoreCase("android")*/)
			{
				try {
					//Check if appium is running on given local port
					URL url= new URL(deviceInfo.getString("url") + "/status");
					url.getContent();
				} catch (Exception e) {
					//Start appium server programmatically if exception is thrown meaning appium is not already running
					deviceInfo.put("url", AppiumService.getUrl());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		URL url = new URL(deviceInfo.get("url").toString());

		DesiredCapabilities caps = new DesiredCapabilities();
		Iterator<?> keys = deviceInfo.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			Object keyValue= deviceInfo.get(key);

			if (key.equalsIgnoreCase("url") || key.equalsIgnoreCase("passcode"))
				continue;

			if ((key.equalsIgnoreCase("app") || key.equalsIgnoreCase("ipa")) 
					&& (keyValue.toString().endsWith(".ipa") || keyValue.toString().endsWith(".app")))
				caps.setCapability(key, new File(deviceInfo.get(key).toString()).getAbsolutePath());
			else
				caps.setCapability(key, deviceInfo.get(key));
		}
		
		if(caps.getBrowserName() == null)
			caps.setBrowserName("");

		System.out.println("Requesting new session with capabilities as: \n" + caps.asMap());
		caps.setCapability("newCommandTimeout", 120000);
		
		//Attach a free port
		caps.setCapability("wdaLocalPort", PortProber.findFreePort());
		
		switch (platform.toLowerCase()) {
		case "ios":
			caps.setCapability("preventWDAAttachments", true);
			caps.setCapability("clearSystemFiles", true);
			try {
				setDriver(new AppiumDriver(url, caps));
			} catch (Exception e) {
				e.printStackTrace();
				setDriver(new AppiumDriver(url, caps));
			}
			break;
			
		case "android":
			setDriver(new AndroidDriver(url, caps));
			break;
			
		default:
			throw new Exception("Unknown platform: " + platform);
		}

		driverInfo.set(((AppiumDriver) getDriver()).getCapabilities());
		System.out.println("Actual device capabilities: \n" + driverInfo.get().asMap());
		MobileDevice.logDeviceInfo();
	}

	public static Dimension getSize() throws Throwable {
		if(deviceSize.get() == null)
		{
			File scrnshot= MobileDevice.getScreenshot(false);
			BufferedImage bi= ImageIO.read(scrnshot);
			deviceSize.set(new Dimension(bi.getWidth(), bi.getHeight()));
		}
		
		return deviceSize.get();
	}
	
	public static String getEnvironment() throws Throwable
	{
		if(driverEnvironment.containsKey(DeviceStore.getLockedDevice()))
			return driverEnvironment.get(DeviceStore.getLockedDevice());
		else
			return "";
	}
	
	public static void setEnvironment(String envName) throws Throwable
	{
		driverEnvironment.put(DeviceStore.getLockedDevice(), envName);
	}
	
	public static void clearEnvironment() throws Throwable
	{
		driverEnvironment.remove(DeviceStore.getLockedDevice());
	}
	
	public static String getBundleId() throws Throwable {
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			return getDriverInfo().get("bundleId").toString();
		if(DeviceStore.getPlatform().equalsIgnoreCase("android"))
			return getDriverInfo().get("appPackage").toString();
		
		return null;
	}

	public static void closeApp() throws Throwable {
		((AppiumDriver)getDriver()).closeApp();
	}

	public static void launchApp() throws Throwable {
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			((AppiumDriver)getDriver()).launchApp();
		else
			AndroidDevice.startApplication();
	}

	public static boolean isSessionRunning() throws Throwable {
		Object lockedDevice= DeviceStore.getLockedDevice();
		if(lockedDevice == null) 
			return false;
		else
			return driverMap.containsKey(lockedDevice);
	}
}
