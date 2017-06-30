package com.curbside.automation.uifactory;

/**
 * @author kumar.anil
 *
 */

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

@SuppressWarnings("rawtypes")
public class AndroidDevice extends MobileDevice {

    public AndroidDevice() {
	}
	
	public static void launchCurbsideActivity() throws Exception {
		
		// Get current device
		((AndroidDriver)DriverFactory.getDriver()).startActivity(new Activity("com.curbside.nCurbside", "com.curbside.nCurbside.app.help.SplashScreenActivity"));
	}

	public static void launchSettingApp() throws Exception {
        Activity activity = new Activity("com.android.settings", "Settings");
    }

    public static void swipeLeft() throws Exception {
        TouchAction touchAction = new TouchAction((PerformsTouchActions) DriverFactory.getDriver());
        int anchor = (int) (MobileDevice.getHeight() * 0.5);
        int startPoint = (int) (MobileDevice.getWidth() * 0.8);
        int endPoint = (int) (MobileDevice.getWidth() * 0.01);
        touchAction.press(startPoint, anchor).waitAction(1000).moveTo(endPoint, anchor).release().perform();

    }

}

