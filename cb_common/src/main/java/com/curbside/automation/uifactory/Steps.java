package com.curbside.automation.uifactory;

import java.nio.file.Files;

import com.curbside.automation.common.utilities.SwipeOptions;
import com.curbside.automation.common.utilities.Utilities;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.utils.FileUtil;
import com.cucumber.listener.Reporter;
import com.curbside.automation.devicefactory.DeviceStore;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

/**
 * @author kumar.anil
 *
 */

@SuppressWarnings("rawtypes")
public class Steps {
	UIElement backgroundAppRefresh= UIElement.byXpath("//XCUIElementTypeSwitch[@name='" + "Background App Refresh" + "']");
	static Logger logger= Logger.getLogger(Steps.class);
	
	@Given("^I launch (.*) application$")
	public void launchApplication(String appName) throws Throwable
	{
		logger.info("Launching application without install");
		DriverFactory.releaseDriver();
		DriverFactory.getDriver(false);
	}
	
	@Given("^I launch (.*) application for the first time$")
	public void launchApplicationClean(String appName) throws Throwable
	{
		logger.info("Re-installing and launching application");
		
		//Reset app permissions from mobile device
		DeviceStore.getDevice();
		if(DeviceStore.getPlatform().equalsIgnoreCase("android"))
			appName= DeviceStore.getDevice().get("appPackage").toString();
		
		logger.info("Resetting permissions for " + appName);
		MobileDevice.resetPermissions(appName);
		MobileDevice.clearAppData(appName);
		
		//Close settings app
		((AppiumDriver)DriverFactory.getDriver()).closeApp();

		//Close current drive, keeping device locked
		DriverFactory.releaseDriver();
		
		//Install and launch application
		logger.info("Launching " + appName + " application");
		DriverFactory.getDriver(true);
	}
	
	@Given("^I accept notifications alert$")
	public void acceptNotificationAlert() throws Throwable
	{
		logger.info("Accepting notification alert");
		
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("Allow")).tap();
		else
			throw new NotImplementedException(
					"Method acceptNotificationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}
	
	public void declineNotificationAlert() throws Throwable
	{
		logger.info("Denying notification alert");
		
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("Don’t Allow")).tap();
		else if(DeviceStore.getPlatform().equalsIgnoreCase("android"))
		{}
		else
		    throw new NotImplementedException(
					"Method declineNotificationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}
	
	@When("^I accept location access alert$")
	public void acceptLocationAlert() throws Throwable {
		logger.info("Accepting location alert");

		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("Allow")).tap();
		else if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
			UIElement e = UIElement.byUISelector("new UiSelector().text(\"Allow\")").waitFor(10);
			for (int i = 0; i < 10; i++) {
				if (!e.isDisplayed())
					break;
				e.touch();
			}

		} else
			throw new NotImplementedException(
					"Method acceptLocationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}
	
	public void declineLocationAlert() throws Throwable
	{
		logger.info("Denying location alert");
		
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("Don’t Allow")).tap();
		else
			throw new NotImplementedException(
					"Method declineLocationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}
	
	@Given("^I swipe left (\\d+) time(?:s)$")
	public void swipeLeft(int times) throws Throwable {
		logger.info("Swiping left " + times + " times");
		
		for (int i = 0; i <= times; i++) {
			new Utilities((AppiumDriver) DriverFactory.getDriver()).swipeOptions(SwipeOptions.Left);
				//MobileDevice.swipeLeft();
			}
	}
	
	@Given("^I (?:tap|click) on '(.*)' button$")
	public static void tapButton(String buttonName) throws Throwable
	{
		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			UIElement.byAccessibilityId(buttonName).tap();
		else if (DeviceStore.getPlatform().equalsIgnoreCase("Android")) {
			UIElement.byXpath("//*[@text='" + buttonName + "']").waitFor(5).tap();
		}
	}
	
	@Given("^I wait for '(.*)' button$")
	public static void waitForButton(String buttonName) throws Throwable
	{
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
	public void tapButtonOnPage(String buttonName, String pageName) throws Throwable
	{
		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name(buttonName)).tap();
		else if (DeviceStore.getPlatform().equalsIgnoreCase("Android")) {
			UIElement okButton = UIElement.byXpath("//*[@text='" + buttonName + "']");
			okButton.waitFor(3);
			okButton.tap();
		}
	}

	@Then("^'(.*)' preference should be set as '(.*)' for '(.*)' app$")
	public void PreferenceShouldBeSetAsForApp(String preferenceName, String expectedValue, String appName) throws Throwable {
		logger.info("Setting value of " + preferenceName + " to " + expectedValue);
		
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
		{
			AppleDevice.launchSettings();
			new UIElement(By.xpath("//XCUIElementTypeCell[@name='" + appName + "']")).scrollTo().tap();

			Assert.assertEquals(new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='" + preferenceName + "']/following-sibling::XCUIElementTypeStaticText")).getText(), expectedValue);
		}
		else
			throw new NotImplementedException("");
	}

	@Given("^I turn '(.*)' Background App Refresh for '(.*)' app$")
	public void changeBackgroundRefresh(String ONorOFF, String appName) throws Throwable {
		logger.info("Turning " + ONorOFF + " background refresh for " + appName);
		
		AppleDevice.launchSettings();
		UIElement.byXpath("//XCUIElementTypeCell[@name='" + appName + "']").scrollTo().tap();

		String currentBackgroundRefreshValue= backgroundAppRefresh.getAttribute("value");
		System.out.println("Current background refresh is " + currentBackgroundRefreshValue);
		
		currentBackgroundRefreshValue = currentBackgroundRefreshValue.equals("true") ? "ON" : "OFF";
		
		if(!ONorOFF.equalsIgnoreCase(currentBackgroundRefreshValue))
				backgroundAppRefresh.tap();
		
	}
	
	@SuppressWarnings("deprecation")
	@After
	public void embedScreenshot(Scenario scenario) {

		try {
			MobileDevice.getScreenshot(true);	
			DriverFactory.releaseDriver();
		} catch (Throwable e) {
		}
	}
}
