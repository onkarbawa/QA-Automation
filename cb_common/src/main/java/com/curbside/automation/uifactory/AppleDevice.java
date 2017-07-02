package com.curbside.automation.uifactory;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;

import org.apache.xpath.operations.String;
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
		JSONObject device = new JSONObject(DeviceStore.getDevice().toString());
		
		device.remove("app");
		device.remove("bundleId");
		device.remove("ipa");

		device.put("bundleId", IOSApps.Settings);
		
		DriverFactory.releaseDriver();
		DriverFactory.getDriver(device, new String[]{});
	}

	public static void swipeLeft() throws Exception {
		TouchAction touchAction = new TouchAction((PerformsTouchActions) DriverFactory.getDriver());
		int anchor = (int) (MobileDevice.getHeight() * 0.5);
		int startPoint = (int) (MobileDevice.getWidth() * 0.8);
		touchAction.press(startPoint, anchor).waitAction(1000).moveTo((startPoint - (2 * startPoint)), 0).release().perform();
	}
}