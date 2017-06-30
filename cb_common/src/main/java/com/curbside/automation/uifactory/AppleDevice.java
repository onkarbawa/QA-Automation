package com.curbside.automation.uifactory;

import org.json.JSONObject;

/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.devicefactory.IOSApps;

public class AppleDevice extends MobileDevice {

	public AppleDevice() {
	}

	public static void launchSettings() throws Exception {
		// Get current device
		JSONObject device = DeviceStore.getDevice();
		device.remove("app");
		device.remove("bundleId");
		device.remove("ipa");

		device.put("bundleId", IOSApps.Settings);
		DriverFactory.releaseDriver();
		DriverFactory.getDriver(device);
	}
}