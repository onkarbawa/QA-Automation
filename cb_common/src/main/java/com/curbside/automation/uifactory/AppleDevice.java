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
	//static UIElement resetSetting= UIElement.byAccessibilityId("Reset Settings");
	static UIElement resetSetting= UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'Reset Settings') or contains(@name,'Reset Warnings')]");
	//static UIElement back= UIElement.byAccessibilityId("Back");
	static UIElement back= UIElement.byName("Back");
	static UIElement passcode= UIElement.byAccessibilityId("Passcode");
	static UIElement settingTitle = UIElement.byXpath("//XCUIElementTypeSearchField[@name='Settings']");
	
	public AppleDevice() {
	}

	public static void launchSettings() throws Throwable {
		JSONObject device = new JSONObject(DeviceStore.getDevice().toString());
		launchSettings(device);
	}
	
	public static void launchSettings(JSONObject device) throws Throwable {
		device.remove("app");
		device.remove("bundleId");
		device.remove("ipa");
		device.put("bundleId", IOSApps.Settings);
		
		DriverFactory.createInstance(device.getString("platformName"), device);
	}
	
	public static void resetPermissions(String appName) throws Throwable {
		
		launchSettings();
		
		/*
		settingGeneral.scrollTo(SwipeDirection.UP);
		if(settingGeneral.isDisplayed()){
			settingGeneral.tap();
		}else {
			settingGeneral.scrollTo(SwipeDirection.DOWN).tap();
		}
		settingReset.scrollTo(SwipeDirection.UP).tap();
		*/
		if (!settingTitle.isDisplayed()){
			for (int i = 0;i < 7; i++){
				if (UIElement.byName("Back").isDisplayed()){
					UIElement.byName("Back").tap();
				}else {
					break;
				}
			}
		}

		try {
			MobileDevice.acceptAlert();
		} catch (Exception e) {}

		settingGeneral.scrollTo().tap();;
		settingReset.scrollTo().tap();
		settingResetLocalAndPrivacy.tap();
		try {
			String code= DeviceStore.getDevice().get("passcode").toString();
			if(code != null && code != "")
				passcode.sendKeys(code);
		} catch (Exception e) {
		}
		
		resetSetting.tap();
		back.waitFor(3).tap();
	}
}