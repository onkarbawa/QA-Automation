package com.curbside.automation.uifactory;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.curbside.automation.common.utilities.SwipeOptions;
import com.curbside.automation.common.utilities.Utilities;
import com.curbside.automation.devicefactory.AndroidApps;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;

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

	private static final Logger logger = Logger.getLogger(MobileDevice.class);

	public MobileDevice() {
	}

	public static void launchSettings() throws Throwable {
		if (DeviceStore.getPlatform().equalsIgnoreCase("ios"))
			AppleDevice.launchSettings();
		else if (DeviceStore.getPlatform().equalsIgnoreCase("android"))
			AndroidDevice.launchSettings();
		else
			throw new NotImplementedException("Not yet implemented");
	}

	public static String getDeviceId() throws Throwable {
		return getDeviceId(DeviceStore.getDevice());
	}

	public static String getDeviceId(JSONObject device) throws Throwable {
		if (device.getString("platformName").equalsIgnoreCase("ios"))
			return device.getString("deviceName");
		else
			return device.getString("deviceName");
	}

	public static String getPlatformVersion() throws Throwable {
		return DriverFactory.getDriverInfo().get("platformVersion").toString();
	}

	public static String getDeviceModel() throws Throwable {
		return DriverFactory.getDriverInfo().get("deviceModel").toString();
	}

	public static Dimension getSize() throws Throwable {
		return DriverFactory.getSize();
	}

	public static int getHeight() throws Throwable {
		return getSize().getHeight();
	}

	public static int getWidth() throws Throwable {
		return getSize().getWidth();
	}

	/**
	 * 
	 * @param appName
	 * @param newValue
	 *            Never/ Always
	 * @throws Throwable
	 */
	public static void setLocationPreference(String appName, String newValue) throws Throwable {
		if (DeviceStore.getPlatform().equalsIgnoreCase("iOS")) {
			AppleDevice.launchSettings();

			UIElement.byPredicate("type ='XCUIElementTypeCell' AND label == '" + appName + "'")
					.scrollTo(SwipeDirection.UP).tap();
			try {
				new UIElement(By.name("Location")).tap();
				MobileDevice.getScreenshot(true);
				new UIElement(By.name(newValue)).tap();
			} catch (Exception e) {
			}
			MobileDevice.getScreenshot(true);

		} else if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
			if (newValue.equalsIgnoreCase("ON"))
				AndroidDevice.grantLocationPermission(DeviceStore.getDevice().get("appPackage").toString());
			else
				AndroidDevice.revokeLocationPermission(DeviceStore.getDevice().get("appPackage").toString());
		} else
			throw new NotImplementedException("");

	}

	public static void swipeUp() throws Throwable {
		int height = getHeight();
		int width = getWidth();

		MobileDevice.swipe(width / 2, (int) (height * 0.80), width / 2, (int) (height * 0.20));
	}

	public static void swipeDown() throws Throwable {
		int height = getHeight();
		int width = getWidth();

		MobileDevice.swipe(width / 2, (int) (height * 0.15), width / 2, (int) (height * 0.85));
	}

	public static void swipeLeft() throws Throwable {
		int height = getHeight();
		int width = getWidth();

		MobileDevice.swipe((int) (width * 0.85), height / 2, (int) (width * 0.15), height / 2);
	}

	public static void swipeRight() throws Throwable {
		int height = getHeight();
		int width = getWidth();

		MobileDevice.swipe((int) (width * 0.15), height / 2, (int) (width * 0.85), height / 2);
	}

	public static void swipe(int startx, int starty, int endx, int endy) throws Throwable {
		/*
		 * if (DeviceStore.getPlatform().equalsIgnoreCase("ios")) {
		 * JavascriptExecutor js = (JavascriptExecutor)
		 * DriverFactory.getDriver(); Map<String, Object> params = new
		 * HashMap<>(); params.put("duration", 1.0); params.put("fromX",
		 * startx); params.put("fromY", starty); params.put("toX", endx);
		 * params.put("toY", endy); js.executeScript(
		 * "mobile: dragFromToForDuration", params); } else
		 */ {
			int xOffset = endx - startx;
			int yOffset = endy - starty;

			new TouchAction((AppiumDriver) DriverFactory.getDriver()).press(startx, starty).moveTo(xOffset, yOffset)
					.release().perform();
		}

		if (DeviceStore.getPlatform().equalsIgnoreCase("android"))
			Thread.sleep(1000);
	}

	public static void swipe(SwipeDirection swipeDirection) throws Throwable {
		switch (swipeDirection) {
		case UP:
			// new Utilities((AppiumDriver)
			// DriverFactory.getDriver()).swipeOptions(SwipeOptions.Up);
			swipeUp();
			break;
		case DOWN:
			// new Utilities((AppiumDriver)
			// DriverFactory.getDriver()).swipeOptions(SwipeOptions.Down);
			swipeDown();
			break;
		case LEFT:
			// new Utilities((AppiumDriver)
			// DriverFactory.getDriver()).swipeOptions(SwipeOptions.Left);
			swipeLeft();
			break;
		case RIGHT:
			// new Utilities((AppiumDriver)
			// DriverFactory.getDriver()).swipeOptions(SwipeOptions.Right);
			swipeRight();
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("deprecation")
	public static File getSource() throws Throwable {
		return getSource(false);
	}

	public static File getSource(boolean attachToReport) throws Throwable {
		// if(DeviceStore.getPlatform().equalsIgnoreCase("android") &&
		// !attachToReport)
		// return null;

		File outputFile = null;
		String source = DriverFactory.getDriver().getPageSource();
		if (source.startsWith("<html"))
			outputFile = File.createTempFile("src_", ".html");
		else
			outputFile = File.createTempFile("src_", ".xml");

		FileUtils.write(outputFile, source);

		if (attachToReport)
			Reporter.addStepLog("<a href='" + outputFile.getAbsolutePath() + "'>page source</a>");

		return outputFile;
	}

	public static File getScreenshot(boolean attachToReport) throws Throwable {
		File scrnshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		if (attachToReport) {
			final String pwd = System.getProperty("user.dir");
			final String path = pwd + "/output/screenshots";
			File tmpFile = File.createTempFile("scrn_", ".png", new File(path));
			FileUtils.copyFile(scrnshot, tmpFile);
			Reporter.addScreenCaptureFromPath(tmpFile.getAbsolutePath());

			File srcFile = getSource();
			if (srcFile != null) {
				File tmpSrcFile = File.createTempFile("src_",
						"." + FilenameUtils.getExtension(srcFile.getAbsolutePath()));
				FileUtils.copyFile(srcFile, tmpSrcFile);
				Reporter.addStepLog("<a href='" + tmpSrcFile.getAbsolutePath() + "'>page source</a>");
			}

			return tmpFile;
		} else
			return scrnshot;
	}

	public static byte[] getScreenshotAsBytes() throws Throwable {
		return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
	}

	public static void tap(int x, int y) throws Throwable {
		new TouchAction((AppiumDriver) DriverFactory.getDriver()).press(x, y).release().perform();
	}

	public static void resetPermissions(String appName) throws Throwable {
		if (DeviceStore.getPlatform().equalsIgnoreCase("ios"))
			AppleDevice.resetPermissions(appName);
		else if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
			AndroidDevice.resetPermissions(appName);
		} else
			throw new NotImplementedException("Not yet implemented");

		DriverFactory.clearEnvironment();
	}

	public static void clearAppData(String appName) throws Throwable {
		if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
			AndroidDevice.clearAppData(appName);
			DriverFactory.clearEnvironment();
		}
	}

	public static void swipeUpSlowly() throws Throwable {
		int anchor = (int) (MobileDevice.getWidth() * 0.5);
		int startPoint = (int) (MobileDevice.getHeight() * 0.8);
		int endPoint = (int) (MobileDevice.getHeight() * 0.6);

		if (DeviceStore.getPlatform().equalsIgnoreCase("ios")) {
			new TouchAction((PerformsTouchActions) DriverFactory.getDriver()).press(anchor, startPoint).waitAction(Duration.ofSeconds(1000))
					.moveTo(0, startPoint - (2 * startPoint)).release().perform();

		} else if (DeviceStore.getPlatform().equalsIgnoreCase("android")) {
			new TouchAction((PerformsTouchActions) DriverFactory.getDriver()).press(anchor, startPoint).waitAction(Duration.ofSeconds(1000))
					.moveTo(0, endPoint).release().perform();
		}
	}

	public static void setGeoLocation(double latitude, double longitude, double altitude) throws Throwable {
		Location l = new Location(latitude, longitude, altitude);
		((AppiumDriver) DriverFactory.getDriver()).setLocation(l);
		DriverFactory.closeApp();
		DriverFactory.launchApp();
	}

	public static void switchToWebView() throws Throwable {
		Set<String> contexts = ((AppiumDriver) DriverFactory.getDriver()).getContextHandles();
		for (int i = 0; i < 10; i++) {
			for (String context : contexts) {
				if (context.contains("WEBVIEW")) {
					((AppiumDriver) DriverFactory.getDriver()).context(context);
					return;
				}
			}

			Thread.sleep(2);
		}
	}

	public static void switchToNativeView() throws Throwable {
		Set<String> contexts = ((AppiumDriver) DriverFactory.getDriver()).getContextHandles();
		for (String context : contexts) {
			if (context.contains("NATIVE")) {
				((AppiumDriver) DriverFactory.getDriver()).context(context);
				break;
			}
		}
	}

	public static void hideKeyboard() {
		try {
			((AppiumDriver) DriverFactory.getDriver()).hideKeyboard();
		} catch (Throwable ex) {
			logger.debug("Problem in Hiding keyboard due to: " + ex.getMessage());
		}
	}

	public static void logDeviceInfo() {
		try {
			Reporter.addStepLog("Platform: " + DeviceStore.getPlatform() + "; " + "Version: " + getPlatformVersion()
					+ "; " + "Model: " + getDeviceModel() + "; " + "ID: " + getDeviceId() + "; ");
		} catch (Throwable e) {
		}
	}

	public static String getBundleId() throws Exception {
		if(DeviceStore.getPlatform().equalsIgnoreCase("iOS"))
			return DeviceStore.getDevice().getString("bundleId");
		if(DeviceStore.getPlatform().equalsIgnoreCase("android"))
			return DeviceStore.getDevice().getString("appPackage");
		
		return null;
	}

	public static void acceptAlert() throws Throwable {
		DriverFactory.getDriver().switchTo().alert().accept();
	}

	public static void dismissAlert() throws Throwable {
		DriverFactory.getDriver().switchTo().alert().dismiss();;
	}

	public static String getAlertText() throws Throwable {
		return DriverFactory.getDriver().switchTo().alert().getText();
	}
}