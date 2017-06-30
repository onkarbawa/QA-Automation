package com.curbside.automation.uifactory;
import org.apache.commons.lang3.NotImplementedException;
import org.json.JSONObject;

/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.devicefactory.IOSApps;
import io.appium.java_client.AppiumDriver;

public class MobileDevice {

	public MobileDevice() {
    }
	
	public static void launchSettings() throws Exception
	{
		//Get current device
		JSONObject device= DeviceStore.getDevice();
		device.remove("app");
		device.remove("bundleId");
		device.remove("ipa");
		
		if(DeviceStore.getPlatform().equalsIgnoreCase("ios"))
		{
			device.put("bundleId", IOSApps.Settings);
			DriverFactory.releaseDriver();
			DriverFactory.getDriver(device);
		}
		else
		{
			throw new NotImplementedException("Not yet implemented");
		}
	}
	
	public static String getDeviceId() throws Exception
	{
		return ((AppiumDriver)DriverFactory.getDriver())
				.getCapabilities().asMap().get("deviceUDID").toString();
	}
	
	public static String getPlatformVersion() throws Exception
	{
		return ((AppiumDriver)DriverFactory.getDriver())
				.getCapabilities().asMap().get("platformVersion").toString();
	}
	
	public static String getDeviceModel() throws Exception
	{
		return ((AppiumDriver)DriverFactory.getDriver())
				.getCapabilities().asMap().get("deviceModel").toString();
	}
	
	public static int getHeight() throws Exception {
	    return DriverFactory.getDriver().manage().window().getSize().getHeight();
    }

    public static int getWidth() throws Exception {
        return DriverFactory.getDriver().manage().window().getSize().getWidth();
    }
 }