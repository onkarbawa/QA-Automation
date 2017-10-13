package com.curbside.automation.uifactory;

import cucumber.api.PendingException;
import cucumber.api.java.eo.Do;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.curbside.automation.appfactory.AppStore;
import com.curbside.automation.devicefactory.DeviceStore;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;

/**
 * @author kumar.anil
 *
 */

@SuppressWarnings("rawtypes")
public class Steps {
	UIElement backgroundAppRefresh = UIElement
			.byXpath("//XCUIElementTypeSwitch[@name='" + "Background App Refresh" + "']");
	static Logger logger = Logger.getLogger(Steps.class);

	@Given("^I launch (.*) application$")
	public void launchApplication(String appName) throws Throwable {
		AppStore.setAppName(appName);
		logger.info("Launching application without install");
		// DriverFactory.releaseDriver();
		// DeviceStore.releaseDevice();

		DriverFactory.getDriver(false);

		if (!MobileDevice.getBundleId().equals(DriverFactory.getBundleId())) {
			DriverFactory.releaseDriver();
			DriverFactory.getDriver(false);
		}
		else
		{
			if (DeviceStore.getPlatform().equalsIgnoreCase("android"))
				AndroidDevice.startApplication();
			else
				DriverFactory.launchApp();
		}

		MobileDevice.getScreenshot(true);
	}

	@Given("^I launch (.*) application with required permissions$")
	public void launchApplicationWithPermissions(String appName) throws Throwable {
		AppStore.setAppName(appName);
		logger.info("Launching application with needed permissions");
		// DriverFactory.releaseDriver();
		// DeviceStore.releaseDevice();

		DriverFactory.getDriver(false, true);
		if (!MobileDevice.getBundleId().equals(DriverFactory.getBundleId())) {
			DriverFactory.releaseDriver();
			DriverFactory.getDriver(false, true);
		}

		if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
			AndroidDevice.grantLocationPermission();
			// ((AppiumDriver)DriverFactory.getDriver()).resetApp();
			((AppiumDriver) DriverFactory.getDriver()).closeApp();
			((AppiumDriver) DriverFactory.getDriver()).launchApp();
		}

		MobileDevice.getScreenshot(true);
	}

	@Given("^I launch (.*) application for the first time$")
	public void launchApplicationClean(String appName) throws Throwable {
		AppStore.setAppName(appName);

		DriverFactory.releaseDriver();
		DeviceStore.releaseDevice();

		// Reset app permissions from mobile device
		DeviceStore.getDevice();
		DriverFactory.clearEnvironment();

		if (DeviceStore.getPlatform().equalsIgnoreCase("ios")
				&& appName.equalsIgnoreCase("Curbside")) {
			AppleDevice.resetPermissions(appName);
			((AppiumDriver) DriverFactory.getDriver()).closeApp();
			DriverFactory.releaseDriver();
		}

		logger.info("Launching " + appName + " application");
		DriverFactory.getDriver(true);
		acceptNotificationAlert();
		DeviceStore.setAppInstalled(appName);
		if (DeviceStore.getPlatform().equalsIgnoreCase("ios")) {
			DriverFactory.releaseDriver();
			DriverFactory.getDriver(false);
		}

		MobileDevice.getScreenshot(true);
	}

	@Given("^I accept notifications alert$")
	public void acceptNotificationAlert() throws Throwable {
		logger.info("Accepting notification alert");

		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			try {
				new UIElement(By.name("Allow")).tap();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		else if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
        } else
            throw new NotImplementedException(
					"Method acceptNotificationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}

	public void declineNotificationAlert() throws Throwable {
		logger.info("Denying notification alert");

		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("Don’t Allow")).tap();
		else if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
		} else
			throw new NotImplementedException(
					"Method declineNotificationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}

	@When("^I accept location access alert$")
	public void acceptLocationAlert() throws Throwable {
		logger.info("Accepting location alert");

		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS")) {
			try {
				new UIElement(By.name("Allow")).tap();
			}catch (Exception e){}
		}
		else if (DeviceStore.getPlatform().equalsIgnoreCase("android")){
			if(MobileDevice.getPlatformVersion().charAt(0) != '5') {
//				UIElement e = UIElement.byId("com.android.packageinstaller:id/permission_allow_button").waitFor(10);
				UIElement e = UIElement.byUISelector("new UiSelector().text(\"Allow\")").waitFor(10);
				for (int i = 0; i < 10; i++) {
					if (!e.isDisplayed())
						break;
					e.touch();
				}
			}
		} else
			throw new NotImplementedException(
					"Method acceptLocationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}

	public void declineLocationAlert() throws Throwable {
		logger.info("Denying location alert");

		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("Don’t Allow")).tap();
		else
			throw new NotImplementedException(
					"Method declineLocationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}

	@Given("^I swipe left (\\d+) time(?:s)$")
	public void swipeLeft(int times) throws Throwable {
		logger.info("Swiping left " + times + " times");

		for (int i = 0; i <= times; i++) {
			// new Utilities((AppiumDriver)
			// DriverFactory.getDriver()).swipeOptions(SwipeOptions.Left);
			MobileDevice.swipeLeft();
		}
	}

	@Given("^I (?:tap|click) on '(.*)' button$")
	public static void tapButton(String buttonName) throws Throwable {
		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			try {
				UIElement.byName(buttonName).waitFor(5).tap();
				MobileDevice.getScreenshot(true);
			} catch (Exception e) {
				UIElement.byAccessibilityId(buttonName).waitFor(5).tap();
				MobileDevice.getScreenshot(true);
			}
		else if (DeviceStore.getPlatform().equalsIgnoreCase("Android")) {
			UIElement.byUISelector("new UiSelector().text(\"" + buttonName + "\")").waitFor(5).tap();
		}
	}

	@Given("^I (?:tap|click) on '(.*)' button if displayed$")
	public static void tapButton_optional(String buttonName) throws Throwable {
		try {
			tapButton(buttonName);
		} catch (Exception e) {
		}
	}

	@Given("^I wait for '(.*)' button$")
	public static void waitForButton(String buttonName) throws Throwable {
		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			UIElement.byAccessibilityId(buttonName).waitFor(15);
		else if (DeviceStore.getPlatform().equalsIgnoreCase("Android")) {
			UIElement.byUISelector("new UiSelector().text(\"" + buttonName + "\")").waitFor(15);
		}
	}

	@And("^I tap on '(.*)' text$")
	public static void tapText(String value) throws Throwable {
		UIElement.byAccessibilityId(value).tap();
	}

	@Given("^I (?:tap|click) on '(.*)' button on '(.*)' .*")
	public void tapButtonOnPage(String buttonName, String pageName) throws Throwable {
		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name(buttonName)).tap();
		else if (DeviceStore.getPlatform().equalsIgnoreCase("Android")) {
		    if(MobileDevice.getPlatformVersion().charAt(0) != '5') {
                UIElement okButton = UIElement.byXpath("//*[@text='" + buttonName + "']");
                okButton.waitFor(3);
                okButton.tap();
            }
		}
	}

	@Then("^'(.*)' preference should be set as '(.*)' for '(.*)' app$")
	public void PreferenceShouldBeSetAsForApp(String preferenceName, String expectedValue, String appName)
			throws Throwable {
		logger.info("Setting value of " + preferenceName + " to " + expectedValue);

		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS")) {
			AppleDevice.launchSettings();
			new UIElement(By.xpath("//XCUIElementTypeCell[@name='" + appName + "']")).scrollTo().tap();

			Assert.assertEquals(new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='" + preferenceName
					+ "']/following-sibling::XCUIElementTypeStaticText")).getText(), expectedValue);
		} else
			throw new NotImplementedException("");

		MobileDevice.getScreenshot(true);
		UIElement.byName("Notifications").tap();
		MobileDevice.getScreenshot(true);

		UIElement toggleButton = UIElement.byXpath("//XCUIElementTypeSwitch[@name='Allow Notifications']");
		String currentButtonValue = toggleButton.getAttribute("value");
		System.out.println("Current toggle value is " + currentButtonValue);

		if(currentButtonValue.equals("false")){
			toggleButton.tap();
		}
		iTapOnBackButton();
		toggleButton = UIElement.byXpath("//XCUIElementTypeSwitch[@name='Background App Refresh']");
		currentButtonValue = toggleButton.getAttribute("value");
		System.out.println("Current toggle value is " + currentButtonValue);

		MobileDevice.getScreenshot(true);
		if(currentButtonValue.equals("false")){
			toggleButton.tap();
		}

	}

	@Given("^I turn '(.*)' Background App Refresh for '(.*)' app$")
	public void changeBackgroundRefresh(String ONorOFF, String appName) throws Throwable {
		logger.info("Turning " + ONorOFF + " background refresh for " + appName);

		AppleDevice.launchSettings();

		UIElement.byXpath("//XCUIElementTypeCell[@name='" + appName + "']").scrollTo().tap();

		String currentBackgroundRefreshValue = backgroundAppRefresh.getAttribute("value");
		System.out.println("Current background refresh is " + currentBackgroundRefreshValue);

		currentBackgroundRefreshValue = currentBackgroundRefreshValue.equals("1") ? "ON" : "OFF";

		if (!ONorOFF.equalsIgnoreCase(currentBackgroundRefreshValue))
			backgroundAppRefresh.tap();
		MobileDevice.getScreenshot(true);
	}

	@SuppressWarnings("deprecation")
	@After
	public void embedScreenshot(Scenario scenario) {

		try {
			MobileDevice.getScreenshot(true);
		} catch (Throwable e) {
		}
	}

	@And("^I tap on back button$")
	public void iTapOnBackButton() throws Throwable {
		if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
			AndroidDevice.hideKeyboard();
			AndroidDevice.goBack();
		} else if(DeviceStore.getPlatform().equalsIgnoreCase("iOS")) {
			new UIElement(By.name("Back")).tap();
		}
	}

	@And("^I turn '(.*)' '(.*)' and '(.*)' for '(.*)' app$")
	public void iTurnAndForCurbsideApp(String ONorOFF, String button1, String button2, String appName) throws Throwable {
	//	logger.info("Turning " + ONorOFF + " background refresh for " + appName);

		AppleDevice.launchSettings();

		if (!AppleDevice.settingTitle.isDisplayed()){
			for (int i = 0;i < 7; i++){
				if (UIElement.byName("Back").isDisplayed()){
					UIElement.byName("Back").tap();
				}else {
					break;
				}
			}
		}
		try {
			UIElement.byXpath("//XCUIElementTypeCell[@name='" + appName + "']").scrollTo().tap();
			String button = button1;

			for (int i = 0; i < 2; i++) {
				UIElement toggleButton = UIElement.byXpath("//XCUIElementTypeSwitch[@name='" + button + "']");
				String currentButtonValue = toggleButton.getAttribute("value");
				System.out.println("Current toggle value is " + currentButtonValue);

				currentButtonValue = currentButtonValue.equals("true") ? "ON" : "OFF";

				if (!ONorOFF.equalsIgnoreCase(currentButtonValue)) {
					toggleButton.tap();
				}
				if (i == 1) {
					break;
				}

				UIElement.byName("Notifications").tap();
				button = button2;
				MobileDevice.getScreenshot(true);
			}
		}catch (Exception e){}
		MobileDevice.getScreenshot(true);
	}

	@Given("^I accept remote notifications alert$")
	public void acceptRemoteNotificationAlert() throws Throwable {
		logger.info("Accept remote notification alert");

		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("OK")).tap();
		else if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
		} else
			throw new NotImplementedException(
					"Method declineNotificationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}

	@And("^I turn '(.*)' '(.*)' through Control Center$")
	public void iTurnThroughControlCenter(String ONorOFF, String button) throws Throwable {
		int height = UIElement.byClass("UIAWindow").getSize().getHeight();
		int width = UIElement.byClass("UIAWindow").getSize().getWidth();

		MobileDevice.swipe(width-100, height-5, width-100,0);

		//UIElement toggleButton = UIElement.byXpath("//XCUIElementTypeSwitch[@name='" + button + "']");
		UIElement toggleButton = UIElement.byAccessibilityId(button);

		String currentButtonValue = toggleButton.getAttribute("value");
		System.out.println("Current toggle value is " + currentButtonValue);

		currentButtonValue = currentButtonValue.equals("true") ? "ON" : "OFF";

		if(ONorOFF.equalsIgnoreCase(currentButtonValue))
			toggleButton.tap();

		MobileDevice.tap(width/2,new Double(height*0.10).intValue());
	}
}
