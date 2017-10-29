package com.curbside.automation.devicefactory;

import java.io.File;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.IOSServerFlag;

/**
 * @author kumar.anil
 *
 */

public class AppiumService {
	static ThreadLocal<AppiumDriverLocalService> runningService = new ThreadLocal<>();
	
	static final File nodeJsPath= new File("/Applications/Appium.app/Contents/???");

	static String start() {
		AppiumServiceBuilder b = new AppiumServiceBuilder();
		b.usingAnyFreePort()
		 .withIPAddress("127.0.0.1")
		 .withArgument(GeneralServerFlag.LOG_TIMESTAMP)
		 .withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		
		if(nodeJsPath.exists())
		{
			System.out.println("Using node at " + nodeJsPath.getAbsolutePath());
			b.usingDriverExecutable(nodeJsPath);
		}
		b.build();
		runningService.set(AppiumDriverLocalService.buildService(b));
		runningService.get().start();
		return runningService.get().getUrl().toString();
	}

	/*
	public static void stop() {
		if (runningService != null) {
			runningService.stop();
			runningService = null;
		}
	}*/

	public static synchronized String getUrl() {
		if (runningService.get() == null)
			start();
		
		return runningService.get().getUrl().toString();
	}
}