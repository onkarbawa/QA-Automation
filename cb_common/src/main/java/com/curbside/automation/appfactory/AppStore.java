package com.curbside.automation.appfactory;

/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.common.json.JsonFileParser;
import com.curbside.automation.devicefactory.DeviceStore;
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
public class AppStore {

	private static volatile List<JSONObject> androidAppList = new ArrayList<>();
	private static volatile List<JSONObject> iOSAppList = new ArrayList<>();
	
	private static ThreadLocal<String> appInUse = new ThreadLocal<>();

	static {
		String pAppStore = System.getProperty("appStore");
		JSONObject apps = null;
		if (pAppStore == null)
			apps = new JsonFileParser().extractJsonFromFile("../app_store.json");
		else
			apps = new JsonFileParser().extractJsonFromFile(pAppStore);

		JSONArray androidApps = (JSONArray) apps.get("android");
		JSONArray iOSApps = (JSONArray) apps.get("iOS");

		for (Object androidApp : androidApps) {
			androidAppList.add((JSONObject) androidApp);
		}

		for (Object iOSApp : iOSApps) {
			iOSAppList.add((JSONObject) iOSApp);
		}
	}
	
	public static synchronized String getAppName() throws Exception {
		return appInUse.get();
	}
	
	public static synchronized void setAppName(String appName) throws Exception {
		appInUse.set(appName);
	}
	
	public static synchronized JSONObject getApp() throws Exception{
		return getApp(getAppName());
	}

	public static synchronized JSONObject getApp(String appName) throws Exception {
		List<JSONObject> appList= new ArrayList<>();
		
		switch (DeviceStore.getPlatform().toLowerCase()) {
		case "ios":
			appList.addAll(iOSAppList);
			break;
		case "android":
			appList.addAll(androidAppList);
			break;
		default:
			throw new IllegalArgumentException("No Such platform");
		}
		
		for (JSONObject appInList : appList) {
			if(appInList.getString("name").equalsIgnoreCase(appName))
			{
				JSONObject app = new JSONObject(appInList.toString());

				app.remove("name");
				return app;
					//	new JSONObject(appInList.toString());
			}
		}
		
		//If we are here, it mean no application is defined with given name
		throw new Exception("No application found with given name: " + appName);	
	}
}
