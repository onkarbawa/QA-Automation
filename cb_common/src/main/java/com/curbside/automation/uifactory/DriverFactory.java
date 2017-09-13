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
import org.openqa.selenium.remote.RemoteWebDriver;

import com.curbside.automation.devicefactory.AppiumService;
import com.curbside.automation.devicefactory.DeviceStore;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

@SuppressWarnings("rawtypes")
public class DriverFactory {
	private static final Logger logger = Logger.getLogger(DriverFactory.class);
	static HashMap<String, WebDriver> driverMap= new HashMap<>();
	static HashMap<String, String> driverEnvironment= new HashMap<>();
	static ThreadLocal<Capabilities> driverInfo = new ThreadLocal<Capabilities>();
	static ThreadLocal<Dimension> deviceSize = new ThreadLocal<Dimension>();

	/**
	 * This method must be called when creating a new driver instance
	 * 
	 * @param platform
	 *            parameter should have been defined in TestNG suite file
	 * @param additionalCaps
	 *            Name Value pairs of additional capabilities that overwrites
	 *            device info
	 * @return @WebDriver
	 * @throws Throwable
	 */
	public static WebDriver getDriver(JSONObject deviceInfo) throws Throwable {
		//if (deviceInfo != null)
		//	System.out.println(deviceInfo.toString());

		if (!driverMap.containsKey(MobileDevice.getDeviceId(deviceInfo))) {
			DriverFactory.createInstance(DeviceStore.getPlatform(), deviceInfo);
		}
		
		return driverMap.get(MobileDevice.getDeviceId(deviceInfo));
	}

	public static WebDriver getDriver() throws Throwable {
		return getDriver(new JSONObject(DeviceStore.getDevice().toString()));
	}

	public static WebDriver getDriver(boolean reinstall) throws Throwable {
		return getDriver(reinstall, false);
	}

	public static WebDriver getDriver(boolean reinstall, boolean givePermissions) throws Throwable {
		JSONObject deviceInfo = new JSONObject(DeviceStore.getDevice().toString());

		if (!reinstall && DeviceStore.isAppInstalled()) {
			//No need to install application
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
		
		try {
			if(deviceInfo.getString("url").contains("127.0.0.1")
					&& deviceInfo.getString("platformName").equalsIgnoreCase("android"))
			{
				deviceInfo.put("url", AppiumService.getUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getDriver(deviceInfo);
	}

	/**
	 * This method should be called when tests are exiting, usually by a test
	 * listener
	 * @throws Throwable 
	 */
	public static void releaseDriver() throws Throwable {
		if (driverMap.containsKey(DeviceStore.getDeviceId())) {
			driverMap.get(DeviceStore.getDeviceId()).quit();
			driverMap.remove(DeviceStore.getDeviceId());
		}
	}

	private static void setDriver(WebDriver driver) throws Throwable {
		driverMap.put(DeviceStore.getDeviceId(), driver);
	}

	public static Map<String, ?> getDriverInfo() throws Throwable {
		if(driverInfo.get() == null)
			driverInfo.set(((RemoteWebDriver)getDriver()).getCapabilities());
		
		return driverInfo.get().asMap();
	}

	private static void createInstance(String platform, JSONObject deviceInfo) throws Throwable {
		if (deviceInfo == null) {
			System.out.println("Getting a new device from store");
			deviceInfo = new JSONObject(DeviceStore.getDevice().toString());
		}

		URL url = new URL(deviceInfo.get("url").toString());

		DesiredCapabilities caps = new DesiredCapabilities();
		//caps.setCapability("sendKeyStrategy", "grouped");

		Iterator<?> keys = deviceInfo.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();

			if (key.equalsIgnoreCase("url") || key.equalsIgnoreCase("passcode"))
				continue;

			if ((key.equalsIgnoreCase("app") || key.equalsIgnoreCase("ipa")) &&
					!deviceInfo.getString(key).equalsIgnoreCase("settings"))
				caps.setCapability(key, new File(deviceInfo.get(key).toString()).getAbsolutePath());
			else
				caps.setCapability(key, deviceInfo.get(key));
		}
		
		if(caps.getBrowserName() == null)
			caps.setBrowserName("");

		switch (platform.toLowerCase()) {
		case "ios":
			setDriver(new AppiumDriver(url, caps));
			UIElement.byAccessibilityId("Trust").tapOptional();
			break;
		case "android":
			setDriver(new AndroidDriver(url, caps));
			break;
		default:
			throw new Exception("Unknown platform: " + platform);
		}

		driverInfo.set(((AppiumDriver) getDriver()).getCapabilities());
		System.out.println("Actual device capabilities: " + driverInfo.get().asMap());
		deviceSize.set(getDriver().manage().window().getSize());
		MobileDevice.logDeviceInfo();

		// System.out.println("Device screenshot captured at " +
		// MobileDevice.takeScreenshot().getAbsolutePath());
		// new ImageElement(new
		// File("src/test/resources/ios/elements/DontAllow.png")).tap();
	}

	public static Dimension getSize() throws Throwable {
		if(deviceSize.get() == null)
			deviceSize.set(getDriver().manage().window().getSize());
		
		return deviceSize.get();
	}
	
	public static String getEnvironment() throws Throwable
	{
		String deviceId= DeviceStore.getDeviceId();
		if(driverEnvironment.containsKey(deviceId))
			return driverEnvironment.get(deviceId);
		else
			return "";
	}
	
	public static void setEnvironment(String envName) throws Throwable
	{
		driverEnvironment.put(DeviceStore.getDeviceId(), envName);
	}
	
	public static void clearEnvironment() throws Throwable
	{
		driverEnvironment.remove(DeviceStore.getDeviceId());
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
}
