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
	
	public static void launchActivity() throws Exception {
		
		// Get current device
		((AndroidDriver)DriverFactory.getDriver()).startActivity(new Activity("com.curbside.nCurbside", "com.curbside.nCurbside.app.help.SplashScreenActivity"));
	}
}