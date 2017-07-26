package com.curbside.automation.uifactory;

import org.json.JSONObject;


/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.devicefactory.IOSApps;

public class AppleDevice extends MobileDevice {

	//static UIElement settingGeneral= UIElement.byXpath("//XCUIElementTypeCell[@name='General']");
	static UIElement settingGeneral= UIElement.byPredicate("type ='XCUIElementTypeCell' AND label == 'General'");
	static UIElement settingReset= UIElement.byPredicate("type ='XCUIElementTypeCell' AND label == 'Reset'");
	static UIElement settingResetLocalAndPrivacy= UIElement.byPredicate("type ='XCUIElementTypeCell' AND label == 'Reset Location & Privacy'");
	static UIElement resetSetting= UIElement.byAccessibilityId("Reset Settings");
	static UIElement back= UIElement.byAccessibilityId("Back");
	static UIElement passcode= UIElement.byAccessibilityId("Passcode");
	
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
		DriverFactory.getDriver(device);
	}
	
	public static void resetPermissions(String appName) throws Throwable {
		
		launchSettings();
		settingGeneral.scrollTo(SwipeDirection.UP);
		if(settingGeneral.isDisplayed()){
			settingGeneral.tap();
		}else {
			settingGeneral.scrollTo(SwipeDirection.DOWN).tap();
		}
		settingReset.scrollTo(SwipeDirection.UP).tap();
		settingResetLocalAndPrivacy.tap();
		try {
			String code= DeviceStore.getDevice().get("passcode").toString();
			if(code != null && code != "")
				passcode.sendKeys(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resetSetting.tap();
		back.tap();
	}
}