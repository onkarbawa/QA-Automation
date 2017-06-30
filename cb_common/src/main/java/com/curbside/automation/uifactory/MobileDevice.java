package com.curbside.automation.uifactory;
import org.apache.commons.lang3.NotImplementedException;
import org.json.JSONObject;

/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.devicefactory.IOSApps;

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
}