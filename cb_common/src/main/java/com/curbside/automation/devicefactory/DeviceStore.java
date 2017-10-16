package com.curbside.automation.devicefactory;

import com.curbside.automation.appfactory.AppStore;

/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.common.json.JsonFileParser;
import com.curbside.automation.uifactory.MobileDevice;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by kumar.nipun on 6/23/2017.
 */
public class DeviceStore {

	private static volatile List<Object> androidDeviceList = new ArrayList<>();
	private static volatile List<Object> iOSDeviceList = new ArrayList<>();

	private static ThreadLocal<Object> lockedDevice = new ThreadLocal<>();
	private static ThreadLocal<String> lockedPlatform = new ThreadLocal<>();
	private static ThreadLocal<String> deviceID = new ThreadLocal<>();
	private static HashMap<String, List<String>> appInstalled = new HashMap<>();
	
	static {
		String pDeviceStore = System.getProperty("deviceStore");
		JSONObject devices = null;
		if (pDeviceStore == null)
			devices = new JsonFileParser().extractJsonFromFile("../devices.json");
		else
			devices = new JsonFileParser().extractJsonFromFile(pDeviceStore);

		JSONArray androidDevices = (JSONArray) devices.get("android");
		JSONArray iOSDevices = (JSONArray) devices.get("iOS");

		for (Object androidDevice : androidDevices) {
			androidDeviceList.add(androidDevice);
		}

		for (Object iOSDevice : iOSDevices) {
			iOSDeviceList.add(iOSDevice);
		}
	}

	public static synchronized JSONObject getDevice() throws Exception{
		return getDevice(AppStore.getAppName());
	}

	public static synchronized JSONObject getDevice(String appName) throws Exception {

		// TODO- Wait when no device is available

		JSONObject deviceToReturn;
		System.out.println("There are "  + iOSDeviceList.size() + " ios devices available");
		System.out.println("There are "  + androidDeviceList.size() + " android devices available");
		
		if (lockedDevice.get() != null)
			deviceToReturn = (JSONObject) lockedDevice.get();
		else
		{
			switch (lockedPlatform.get().toLowerCase()) {
			case "ios":
				deviceToReturn = (JSONObject) iOSDeviceList.get(0);
				break;
			case "android":
				deviceToReturn = (JSONObject) androidDeviceList.get(0);
				break;
			default:
				throw new IllegalArgumentException("No Such platform");
			}
		}
		
		lockDevice(deviceToReturn);
		
		if(appName != null)
		{
			JSONObject app= AppStore.getApp(appName);
			for (String k : app.keySet())
				deviceToReturn.put(k, app.getString(k));
		}
		
		return deviceToReturn;
	}

	private static void lockDevice(Object deviceInfo) {
		lockedDevice.set(deviceInfo);

		switch (lockedPlatform.get().toLowerCase()) {
		case "ios":
			iOSDeviceList.remove(deviceInfo);
			break;
		case "android":
			androidDeviceList.remove(deviceInfo);
			break;
		default:
			throw new IllegalArgumentException("No Such platform");
		}
	}

	public static synchronized void releaseDevice() {
		if(lockedDevice.get() == null)
			return;
		
		switch (lockedPlatform.get().toLowerCase()) {
		case "ios":
			iOSDeviceList.add(lockedDevice.get());
			break;
		case "android":
			androidDeviceList.add(lockedDevice.get());
			break;
		}

		lockedDevice.set(null);
	}

	public static void setPlatform(String platform) {
		lockedPlatform.set(platform);
	}

	public static String getPlatform() {
		return lockedPlatform.get();
	}

	public static int getAndroidDeviceCount() {
		return androidDeviceList.size();
	}

	public static int getIOSDeviceCount() {
		return iOSDeviceList.size();
	}

	public static String getDeviceId() throws Throwable {
		if (deviceID.get() == null)
			deviceID.set(MobileDevice.getDeviceId());

		return deviceID.get();
	}

	public static String getApplicationPath() {
		try {
			return getDevice().getString("app");
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isAppInstalled(String appName) throws Throwable {
		return isAppInstalled(getDeviceId(), appName);
	}

	public static boolean isAppInstalled() throws Throwable {
		return isAppInstalled(getDeviceId(), AppStore.getAppName());
	}

	public static boolean isAppInstalled(String udid, String appName) {
		if (appInstalled.containsKey(udid))
			return appInstalled.get(udid).contains(appName);
		else
			return false;
	}

	public static synchronized void setAppInstalled(String udid, String appName) {
		if(!appInstalled.containsKey(udid))
		{
			List<String> installedApps= new ArrayList<>();
			installedApps.add(appName);
			appInstalled.put(udid, installedApps);
		}
		else
			appInstalled.get(udid).add(appName);
	}

	public static synchronized void setAppInstalled(String appName) throws Throwable {
		setAppInstalled(getDeviceId(), appName);
	}
}
