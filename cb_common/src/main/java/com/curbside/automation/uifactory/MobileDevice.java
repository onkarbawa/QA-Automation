package com.curbside.automation.uifactory;
import java.io.File;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.devicefactory.IOSApps;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

@SuppressWarnings("rawtypes")
public class MobileDevice {

	public MobileDevice() {
    }
	
	public static void launchSettings() throws Throwable
	{
		if(DeviceStore.getPlatform().equalsIgnoreCase("ios"))
			AppleDevice.launchSettings();
		else if(DeviceStore.getPlatform().equalsIgnoreCase("android"))
			AndroidDevice.launchSettings();
		else
			throw new NotImplementedException("Not yet implemented");
	}
	
	public static String getDeviceId() throws Throwable
	{
		return ((AppiumDriver)DriverFactory.getDriver())
				.getCapabilities().asMap().get("deviceUDID").toString();
	}
	
	public static String getPlatformVersion() throws Throwable
	{
		return ((AppiumDriver)DriverFactory.getDriver())
				.getCapabilities().asMap().get("platformVersion").toString();
	}
	
	public static String getDeviceModel() throws Throwable
	{
		return ((AppiumDriver)DriverFactory.getDriver())
				.getCapabilities().asMap().get("deviceModel").toString();
	}
	
	public static int getHeight() throws Throwable {
	    return DriverFactory.getDriver().manage().window().getSize().getHeight();
    }

    public static int getWidth() throws Throwable {
        return DriverFactory.getDriver().manage().window().getSize().getWidth();
    }
    
    /**
	 * 
	 * @param appName
	 * @param newValue Never/ Always
	 * @throws Throwable
	 */
	public static void setLocationPreference(String appName, String newValue) throws Throwable {
		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS")) {
			AppleDevice.launchSettings();

			new UIElement(By.xpath("//XCUIElementTypeCell[@name='" + appName + "']")).scrollTo().tap();

			try {
				new UIElement(By.name("Location")).tap();
				new UIElement(By.name(newValue)).tap();
			} catch (Exception e) {
			}

		} else if(DeviceStore.getPlatform().equalsIgnoreCase("android"))
		{}
		else
			throw new NotImplementedException("");

	}
	
	public static void swipeUp() throws Throwable
	{
		int height= getHeight();
		int width= getWidth();
		
		MobileDevice.swipe(width/2, (int)(height * 0.85), width/2, (int)(height * 0.15));
	}
	
	public static void swipeDown() throws Throwable
	{
		int height= getHeight();
		int width= getWidth();
		
		MobileDevice.swipe(width/2, (int)(height * 0.15), width/2, (int)(height * 0.85));
	}
	
	public static void swipeLeft() throws Throwable
	{
		int height= getHeight();
		int width= getWidth();
		
		MobileDevice.swipe((int)(width * 0.85), height/2, (int)(width * 0.15), height/2);
	}
	
	public static void swipeRight() throws Throwable
	{
		int height= getHeight();
		int width= getWidth();
		
		MobileDevice.swipe((int)(width * 0.15), height/2, (int)(width * 0.85), height/2);
	}
	
	public static void swipe(int startx, int starty, int endx, int endy) throws Throwable
	{
		int xOffset = endx - startx;
        int yOffset = endy - starty;
        
        new TouchAction((AppiumDriver)DriverFactory.getDriver()).press(startx, starty).moveTo(xOffset, yOffset).release().perform();	
	}
	
	public static File takeScreenshot() throws Throwable
	{
		return ((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
	}

	public static void tap(int x, int y) throws Throwable {
		new TouchAction((AppiumDriver)DriverFactory.getDriver()).press(x, y).release().perform();
	}

	public static void resetPermissions(String appName) throws Throwable {
		if(DeviceStore.getPlatform().equalsIgnoreCase("ios"))
			AppleDevice.resetPermissions(appName);
		else if(DeviceStore.getPlatform().equalsIgnoreCase("android"))
			AndroidDevice.resetPermissions(appName);
		else
			throw new NotImplementedException("Not yet implemented");
	}
 }