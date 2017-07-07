package com.curbside.automation.uifactory;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.curbside.automation.devicefactory.DeviceStore;

import cucumber.api.java.After;
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
	
	@Given("^I launch (.*) application$")
	public void launchApplication(String appName) throws Throwable
	{
		DriverFactory.releaseDriver();
		DriverFactory.getDriver();
	}
	
	@Given("^I launch (.*) application for the first time$")
	public void launchApplicationClean(String appName) throws Throwable
	{
		//Launch app first
		DriverFactory.getDriver("autoAcceptAlerts", false);
		
		//Get application name for preferences
		try {
			declineNotificationAlert();
		} catch (Exception e) {}
		
		((AppiumDriver)DriverFactory.getDriver()).closeApp();
		MobileDevice.setLocationPreference(appName, "Never");
		
		DriverFactory.releaseDriver();
		DriverFactory.getDriver();
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
			new UIElement(By.xpath("//*[@text='ALLOW']")).waitForElement(5);
			new UIElement(By.xpath("//*[@text='ALLOW']")).tap();
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
		for (int i = 0; i < times; i++) {
				MobileDevice.swipeLeft();
			}
	}
	
	@Given("^I (?:tap|click) on '(.*)' button$")
	public void tapButton(String buttonName) throws Throwable
	{
		new UIElement(By.name(buttonName)).tap();
	}
	
	@Given("^I (?:tap|click) on '(.*)' button on '(.*)' .*")
	public void tapButtonOnPage(String buttonName, String pageName) throws Throwable
	{
		new UIElement(By.name(buttonName)).tap();
	}

	@Then("^'(.*)' preference should be set as '(.*)' for '(.*)' app$")
	public void PreferenceShouldBeSetAsForApp(String preferenceName, String expectedValue, String appName) throws Throwable {
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
		{
			AppleDevice.launchSettings();
			new UIElement(By.xpath("//XCUIElementTypeCell[@name='" + appName + "']")).scrollTo().tap();
			try {
				new UIElement(By.name("Location")).tap();
			} catch (Exception e) {
			}
			System.out.println(new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='" + preferenceName + "']/following-sibling::XCUIElementTypeStaticText")));
			//new UIElement(By.name(appName)).tap();
			Assert.assertEquals(new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='" + preferenceName + "']/following-sibling::XCUIElementTypeStaticText")).getText(), expectedValue);
		}
		else
			throw new NotImplementedException("");
	}


	@Given("^I turn '(.*)' '(.*)' for '(.*)' app$")
	public void iTurnForCurbsideApp(String buttonValue, String buttonName, String appName) throws Throwable {
		AppleDevice.launchSettings();

		new UIElement(By.xpath("//XCUIElementTypeCell[@name='" + appName + "']")).scrollTo().tap();

		String value = new UIElement(By.xpath("//XCUIElementTypeSwitch[@name='" + buttonName + "']")).getElement().getAttribute("value");
		System.out.println(value);
		if(buttonValue.equalsIgnoreCase("OFF") && (value.equalsIgnoreCase("true"))){
			System.out.println("Button value is OFF");
			new UIElement(By.xpath("//XCUIElementTypeSwitch[@name='" + buttonName + "']")).tap();
		} else if(buttonValue.equalsIgnoreCase("ON") && (value.equalsIgnoreCase("false"))){
			System.out.println("Button value is ON");
			new UIElement(By.xpath("//XCUIElementTypeSwitch[@name='" + buttonName + "']")).tap();
		}
	}
	
	@After
	public void attacheScreenshot()
	{}

}
