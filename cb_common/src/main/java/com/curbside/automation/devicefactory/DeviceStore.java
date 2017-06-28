package com.curbside.automation.devicefactory;

/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.json.JsonFileParser;
import com.curbside.automation.common.json.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

/**
 * Created by kumar.nipun on 6/23/2017.
 */
public class DeviceStore {

  private static volatile List<Object> androidDeviceList= new ArrayList<>();
  private static volatile List<Object> iOSDeviceList= new ArrayList<>();
  
  private JsonFileParser jsonFileParser;
    
  private static ThreadLocal<Object> lockedDevice = new ThreadLocal<>();
  private static ThreadLocal<String> lockedPlatform = new ThreadLocal<>();

  static {
	String pDeviceStore= System.getProperty("deviceStore");
	JSONObject devices= null;
	if(pDeviceStore == null)
		devices= new JsonFileParser().extractJsonFromFile("../devices.json");
	else
		devices= new JsonFileParser().extractJsonFromFile(pDeviceStore);
    
	JSONArray androidDevices = (JSONArray) devices.get("android");
	JSONArray iOSDevices = (JSONArray) devices.get("iOS");
    
    for (Object androidDevice : androidDevices) {
		androidDeviceList.add(androidDevice);
	}
    
    for (Object iOSDevice : iOSDevices) {
    	iOSDeviceList.add(iOSDevice);
	}
  }

  public static synchronized JSONObject getDevice() {
    
	//TODO- Wait when no device is available
	  
	if(lockedDevice.get() != null)
	  return (JSONObject) lockedDevice.get();
	
	Object deviceToReturn;
    switch (lockedPlatform.get().toLowerCase()) {
      case "ios":
    	  deviceToReturn= iOSDeviceList.get(0);
    	  break;
      case "android":
          deviceToReturn= androidDeviceList.get(0);
          break;
      default:
        throw new IllegalArgumentException("No Such platform");
    }
    
	lockDevice(deviceToReturn);
	
    return (JSONObject) deviceToReturn;
  }
  
  private static void lockDevice(Object deviceInfo)
  {
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
  
  public static void setPlatform(String platform)
  {
	  lockedPlatform.set(platform);
  }
  
  public static String getPlatform()
  {
	  return lockedPlatform.get();
  }
}
