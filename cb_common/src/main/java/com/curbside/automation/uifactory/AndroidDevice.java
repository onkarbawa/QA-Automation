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

import java.io.IOException;

import static com.curbside.automation.common.BaseTest.driver;

@SuppressWarnings("rawtypes")
public class AndroidDevice extends MobileDevice {

    public AndroidDevice() {
	}
	
	//public static void launchCurbsideActivity() throws Throwable {
	//	
	//	// Get current device
	//	((AndroidDriver)DriverFactory.getDriver()).startActivity(new Activity("com.curbside.nCurbside", "com.curbside.nCurbside.app.help.SplashScreenActivity"));
	//}

	public static void startApplication(String packageName, String activityName) throws Throwable {
		((AndroidDriver)DriverFactory.getDriver()).startActivity(
				new Activity(packageName, activityName));
	}
	
	public static void launchSettings() throws Throwable {
		// Get current device
		JSONObject device = new JSONObject(DeviceStore.getDevice().toString());

		device.remove("app");
		device.put("appPackage", AndroidApps.Settings_Package);
		device.put("appActivity", AndroidApps.Settings_Activity);

		// DriverFactory.releaseDriver();
		DriverFactory.getDriver(device);
	}

    public static void pressKey(int keyCode) throws Throwable {
        ((AndroidDriver)DriverFactory.getDriver()).pressKeyCode(keyCode);
    }
    
    public static void pressEnter() throws Throwable {
        pressKey(AndroidKeyCode.ENTER);
    }
    
    public static void goBack() throws Throwable {
        pressKey(AndroidKeyCode.BACK);
    }

    public static void resetPermissions(String appPackage) throws Throwable {
      String command = getPMPrefix() + "reset-permissions " + appPackage;
      Runtime.getRuntime().exec(command).waitFor();
	}

    public static void grantLocationPermission(String appPackage) throws Throwable {
      String command = getPMPrefix() + "grant " + appPackage + " android.permission.ACCESS_FINE_LOCATION";
      Runtime.getRuntime().exec(command).waitFor();
    }

    public static void revokeLocationPermission(String appPackage) throws Throwable {
      String command = getPMPrefix() + "revoke " + appPackage + " android.permission.ACCESS_FINE_LOCATION";
      Process p = Runtime.getRuntime().exec(command);
      p.waitFor();
      System.out.println(p.exitValue());
    }

    public static void uninstallApp(String appPackage) throws Throwable {
      String command = getPMPrefix() + " uninstall " + appPackage;
      Runtime.getRuntime().exec(command).waitFor();
    }
    
    public static void clearAppData(String appPackage) throws Throwable {
        String command = getPMPrefix() + " clear " + appPackage;
        Runtime.getRuntime().exec(command).waitFor();
      }
    
    private static String getPMPrefix() throws Throwable
    {
    	return "adb -s " + DeviceStore.getDeviceId() + " shell pm ";
    }
}

