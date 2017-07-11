package com.curbside.automation.uifactory;

/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.devicefactory.AndroidApps;
import com.curbside.automation.devicefactory.IOSApps;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;

import com.curbside.automation.devicefactory.DeviceStore;

import static com.curbside.automation.common.BaseTest.driver;

@SuppressWarnings("rawtypes")
public class AndroidDevice extends MobileDevice {

    public AndroidDevice() {
	}
	
	public static void launchCurbsideActivity() throws Throwable {
		
		// Get current device
		((AndroidDriver)DriverFactory.getDriver()).startActivity(new Activity("com.curbside.nCurbside", "com.curbside.nCurbside.app.help.SplashScreenActivity"));
	}

  public static void launchSettings() throws Throwable {

    // Get current device
    JSONObject device = new JSONObject(DeviceStore.getDevice().toString());

    device.remove("app");
    device.remove("appPackage");
    device.remove("appActivity");

    device.put("appPackage", AndroidApps.Settings_Package);
    device.put("appActivity", AndroidApps.Settings_Activity);

    DriverFactory.releaseDriver();
    DriverFactory.getDriver(device, new String[]{});
  }

	public static void launchSettingApp() throws Throwable {
        Activity activity = new Activity("com.android.settings", "Settings");
    }

    public static void swipeLeft() throws Throwable {
        TouchAction touchAction = new TouchAction((PerformsTouchActions) DriverFactory.getDriver());
        int anchor = (int) (MobileDevice.getHeight() * 0.5);
        int startPoint = (int) (MobileDevice.getWidth() * 0.8);
        int endPoint = (int) (MobileDevice.getWidth() * 0.01);
        touchAction.press(startPoint, anchor).waitAction(1000).moveTo(endPoint, anchor).release().perform();

    }

    public static void hitEnter() throws Throwable {
        ((AndroidDriver)DriverFactory.getDriver()).pressKeyCode(66);
    }

    public static void goBack() throws Throwable {
        ((AndroidDriver)DriverFactory.getDriver()).pressKeyCode(AndroidKeyCode.BACK);
    }
    
    public static void resetPermissions(String appName) throws Throwable {
	}
}

