package com.curbside.automation.devicefactory;

import java.io.File;
import java.util.HashMap;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/**
 * @author kumar.anil
 *
 */

public class AppiumService {
	static HashMap<String, AppiumDriverLocalService> runningServices = new HashMap<>();
	static final File nodeJsPath= new File("/Applications/Appium.app/Contents/???");

	static String start(String deviceId) {
		AppiumServiceBuilder b = new AppiumServiceBuilder();
		b.usingAnyFreePort().withIPAddress("127.0.0.1");
		if(nodeJsPath.exists())
		{
			System.out.println("Using node at " + nodeJsPath.getAbsolutePath());
			b.usingDriverExecutable(nodeJsPath);
		}
		b.build();
		AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(b);
		appiumService.start();
		runningServices.put(deviceId, appiumService);
		return appiumService.getUrl().toString();
	}

	public static void stop(String deviceId) {
		if (runningServices.containsKey(deviceId)) {
			runningServices.get(deviceId).stop();
			runningServices.remove(deviceId);
		}
	}

	public static String getUrl(String deviceId) {
		if (runningServices.containsKey(deviceId))
			return runningServices.get(deviceId).getUrl().toString();
		else
			return start(deviceId);
	}
	
	public static String getUrl() throws Throwable {
		return getUrl(DeviceStore.getDeviceId());
	}
}