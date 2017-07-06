package com.curbside.automation.uifactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;

import org.json.JSONObject;
import org.openqa.selenium.By;

/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.devicefactory.IOSApps;

public class AppleDevice extends MobileDevice {

	static UIElement settingGeneral= new UIElement(By.xpath("//XCUIElementTypeCell[@name='General']"));
	static UIElement settingReset= new UIElement(By.xpath("//XCUIElementTypeCell[@name='Reset']"));
	static UIElement settingResetLocalAndPrivacy= new UIElement(By.xpath("//XCUIElementTypeCell[@name='Reset Location & Privacy']"));
	static UIElement resetSetting= new UIElement(MobileBy.AccessibilityId("Reset Settings"));
	static UIElement back= new UIElement(MobileBy.AccessibilityId("Back"));
	static UIElement passcode= new UIElement(MobileBy.AccessibilityId("Passcode"));
	
	public AppleDevice() {
	}

	public static void launchSettings() throws Throwable {

		// Get current device
		JSONObject device = new JSONObject(DeviceStore.getDevice().toString());
		
		device.remove("app");
		device.remove("bundleId");
		device.remove("ipa");

		device.put("bundleId", IOSApps.Settings);
		
		DriverFactory.releaseDriver();
		DriverFactory.getDriver(device, new String[]{});
	}
	
	public static void resetPermissions(String appName) throws Throwable {
		
		launchSettings();
		settingGeneral.scrollTo().tap();
		settingReset.scrollTo().tap();
		settingResetLocalAndPrivacy.tap();
		try {
			String code= DeviceStore.getDevice().get("passcode").toString();
			if(code != null && code != "")
				passcode.sendKeys(code);
		} catch (Exception e) {
		}
		
		resetSetting.tap();
		back.tap();
	}

	public static void swipeLeft() throws Throwable {
		TouchAction touchAction = new TouchAction((PerformsTouchActions) DriverFactory.getDriver());
		int anchor = (int) (MobileDevice.getHeight() * 0.5);
		int startPoint = (int) (MobileDevice.getWidth() * 0.8);
		touchAction.press(startPoint, anchor).waitAction(1000).moveTo((startPoint - (2 * startPoint)), 0).release().perform();
	}
}