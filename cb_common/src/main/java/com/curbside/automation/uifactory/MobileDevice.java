package com.curbside.automation.uifactory;
import java.io.File;
import java.util.List;

import com.curbside.automation.common.utilities.SwipeOptions;
import com.curbside.automation.common.utilities.Utilities;
import com.curbside.automation.devicefactory.AndroidApps;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.*;

import com.cucumber.listener.Reporter;

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

			UIElement.byPredicate("type ='XCUIElementTypeCell' AND label == '" + appName + "'").scrollTo(SwipeDirection.UP).tap();
			try {
				new UIElement(By.name("Location")).tap();
				new UIElement(By.name(newValue)).tap();
			} catch (Exception e) {
			}

		} else if(DeviceStore.getPlatform().equalsIgnoreCase("android")) {
			if (newValue.equalsIgnoreCase("ON"))
				AndroidDevice.grantLocationPermission(DeviceStore.getDevice().get("appPackage").toString());
			else
				AndroidDevice.revokeLocationPermission(DeviceStore.getDevice().get("appPackage").toString());
		}
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
        
        new TouchAction((AppiumDriver)DriverFactory.getDriver())
			        .press(startx, starty)
			        .moveTo(xOffset, yOffset)
			        .waitAction(1000)
			        .release().perform();
	}
	
	public static void swipe(SwipeDirection swipeDirection) throws Throwable {
		switch (swipeDirection) {
		case UP:
			new Utilities((AppiumDriver) DriverFactory.getDriver()).swipeOptions(SwipeOptions.Up);
		//	swipeUp();
			break;
		case DOWN:
			new Utilities((AppiumDriver) DriverFactory.getDriver()).swipeOptions(SwipeOptions.Down);
		//	swipeDown();
			break;
		case LEFT:
			new Utilities((AppiumDriver) DriverFactory.getDriver()).swipeOptions(SwipeOptions.Left);
		//	swipeLeft();
			break;
		case RIGHT:
			new Utilities((AppiumDriver) DriverFactory.getDriver()).swipeOptions(SwipeOptions.Right);
		//	swipeRight();
			break;
		default:
			break;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static File getSource() throws Throwable
	{
		if(DeviceStore.getPlatform().equalsIgnoreCase("android"))
			return null;
		
		File outputFile= null;
		String source= DriverFactory.getDriver().getPageSource();
		if(source.startsWith("<html"))
			outputFile= File.createTempFile("src_", ".html");
		else
			outputFile= File.createTempFile("src_", ".xml");

		FileUtils.write(outputFile, source);
		return outputFile;
	}
	
	public static File getScreenshot(boolean attachToReport) throws Throwable
	{
		File scrnshot= ((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		if(attachToReport)
		{
			File tmpFile= File.createTempFile("scrn_", ".png");
			FileUtils.copyFile(scrnshot, tmpFile);
			Reporter.addScreenCaptureFromPath(tmpFile.getAbsolutePath());
			
			
			File srcFile= getSource();
			File tmpSrcFile= File.createTempFile("src_", "." + FilenameUtils.getExtension(srcFile.getAbsolutePath()));
			FileUtils.copyFile(srcFile, tmpSrcFile);
			Reporter.addStepLog("<a href='" + tmpSrcFile.getAbsolutePath() + "'>page source</a>");
			
			return tmpFile;
		}
		else
			return scrnshot;
	}

	public static byte[] getScreenshotAsBytes() throws Throwable
	{
		return ((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
	}

	public static void tap(int x, int y) throws Throwable {
		new TouchAction((AppiumDriver)DriverFactory.getDriver()).press(x, y).release().perform();
	}

	public static void resetPermissions(String appName) throws Throwable {
		if (DeviceStore.getPlatform().equalsIgnoreCase("ios"))
			AppleDevice.resetPermissions(appName);
		else if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
			AndroidDevice.resetPermissions(appName);
		} else
			throw new NotImplementedException("Not yet implemented");
	}

	public static void swipeUpSlowly() throws Throwable
	{
        int anchor = (int) (MobileDevice.getWidth() * 0.5);
        int startPoint = (int) (MobileDevice.getHeight() * 0.8);
        int endPoint = (int) (MobileDevice.getHeight() * 0.6);

        if(DeviceStore.getPlatform().equalsIgnoreCase("ios")){
            new TouchAction((PerformsTouchActions) DriverFactory.getDriver())
                    .press(anchor, startPoint)
                    .waitAction(1000)
                    .moveTo(0, startPoint - (2 * startPoint))
                    .release().perform();

        }
        else if(DeviceStore.getPlatform().equalsIgnoreCase("android")) {
            new TouchAction((PerformsTouchActions) DriverFactory.getDriver())
                    .press(anchor, startPoint)
                    .waitAction(1000)
                    .moveTo(0, endPoint)
                    .release().perform();
        }
	}
 }