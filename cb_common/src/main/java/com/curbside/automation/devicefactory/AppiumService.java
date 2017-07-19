package com.curbside.automation.devicefactory;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/**
 * @author kumar.anil
 *
 */

public class AppiumService {
	static ThreadLocal<AppiumDriverLocalService> service= new ThreadLocal<>();
	
	public static String start(){
		AppiumServiceBuilder b= new AppiumServiceBuilder();
		b.usingAnyFreePort().withIPAddress("127.0.0.1").build();
		AppiumDriverLocalService appiumService= AppiumDriverLocalService.buildService(b);
		appiumService.start();;
		service.set(appiumService);
		
		return appiumService.getUrl().toString();
	}
	
	public static void stop(){
		if(service.get() != null)
			service.get().stop();
	}
	
	public static String getUrl(){
		if(service.get() != null)
			return service.get().getUrl().toString();
		else
			return start();
	}
}