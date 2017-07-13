package com.curbside.automation.uifactory;

import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
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
	
	@Given("^I launch (.*) application$")
	public void launchApplication(String appName) throws Throwable
	{
		DriverFactory.releaseDriver();
		DriverFactory.getDriver(false);
	}
	
	@Given("^I launch (.*) application for the first time$")
	public void launchApplicationClean(String appName) throws Throwable
	{
		//Reset app permissions from mobile device
		DeviceStore.getDevice();
		if(DeviceStore.getPlatform().equalsIgnoreCase("android"))
			appName= DeviceStore.getDevice().get("appPackage").toString();
		
		MobileDevice.resetPermissions(appName);
		
		//Close settings app
		((AppiumDriver)DriverFactory.getDriver()).closeApp();

		//Close current drive, keeping device locked
		DriverFactory.releaseDriver();
		
		//Install and launch application
		DriverFactory.getDriver(true);
	}
	
	@Given("^I accept notifications alert$")
	public void acceptNotificationAlert() throws Throwable
	{
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("Allow")).tap();
		else
			throw new NotImplementedException(
					"Method acceptNotificationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}
	
	public void declineNotificationAlert() throws Throwable
	{
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("Don’t Allow")).tap();
		else if(DeviceStore.getPlatform().equalsIgnoreCase("android"))
		{}
		else
		    throw new NotImplementedException(
					"Method declineNotificationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}
	
	@When("^I accept location access alert$")
	public void acceptLocationAlert() throws Throwable
	{
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("Allow")).tap();
		else if (DeviceStore.getPlatform().equalsIgnoreCase("android")){
			UIElement allow = UIElement.byId("com.android.packageinstaller:id/permission_allow_button");
			allow.waitFor(5).tap();
			allow.waitForNot(2);
			// Retry - in some devices it appears twice
			if (allow.isDisplayed())
				allow.tap();
		}
		else
			throw new NotImplementedException(
					"Method acceptLocationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}
	
	public void declineLocationAlert() throws Throwable
	{
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			new UIElement(By.name("Don’t Allow")).tap();
		else
			throw new NotImplementedException(
					"Method declineLocationAlert is not implemented for platform: " + DeviceStore.getPlatform());
	}
	
	@Given("^I swipe left (\\d+) time(?:s)$")
	public void swipeLeft(int times) throws Throwable {
		for (int i = 0; i <= times; i++) {
				MobileDevice.swipeLeft();
			}
	}
	
	@Given("^I (?:tap|click) on '(.*)' button$")
	public static void tapButton(String buttonName) throws Throwable
	{
		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			UIElement.byAccessibilityId(buttonName).tap();
		else if (DeviceStore.getPlatform().equalsIgnoreCase("Android")) {
			UIElement.byXpath("//*[@text='" + buttonName + "']").waitFor(3).tap();
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
