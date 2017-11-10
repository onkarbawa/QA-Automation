package com.curbside.automation.uifactory;

import com.cucumber.listener.Reporter;
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
	static UIElement btnAllow = UIElement.byName("Allow");
	static UIElement settingUi = UIElement.byXpath("//XCUIElementTypeTable");
	
	public AppleDevice() {
	}

    public static void launchSettings() throws Throwable {

        // Get current device
        JSONObject device = new JSONObject(DeviceStore.getDevice().toString());

        for (int i = 0; i < 2; ++i) {
            device.remove("app");
            device.remove("bundleId");
            device.remove("ipa");
            device.put("bundleId", IOSApps.Settings);
            DriverFactory.releaseDriver();
            DriverFactory.getDriver(device);
            settingUi.waitFor(10);

            if (!settingUi.isDisplayed() && (i == 0))
                Reporter.addStepLog("Launching Setting app again");
            else
                ++i;
        }
        MobileDevice.getScreenshot(true);
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

		btnAllow.tapOptional();

		settingGeneral.scrollTo().tap();;
		settingReset.scrollTo().tap();
		settingResetLocalAndPrivacy.tap();
		try {
			String code= DeviceStore.getDevice().get("passcode").toString();
			if(code != null && code != "")
				passcode.sendKeys(code);
		} catch (Exception e) {
		//	e.printStackTrace();
		}
		MobileDevice.getScreenshot(true);
		resetSetting.tap();
		back.waitFor(3).tap();
	}
}