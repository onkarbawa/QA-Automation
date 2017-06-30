package com.curbside.automation.uifactory;

/**
 * @author kumar.anil
 *
 */

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

@SuppressWarnings("rawtypes")
public class AndroidDevice extends MobileDevice {

	public AndroidDevice() {
	}
	
	public static void launchSettings() throws Exception {
		
		// Get current device
		((AndroidDriver)DriverFactory.getDriver()).startActivity(new Activity("", ""));
	}
}