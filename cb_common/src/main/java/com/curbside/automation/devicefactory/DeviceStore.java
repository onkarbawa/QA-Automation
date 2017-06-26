package com.curbside.automation.devicefactory;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.common.json.JsonFileParser;
import com.curbside.automation.common.json.Platform;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by kumar.nipun on 6/23/2017.
 */
public class DeviceStore {

  private static JSONArray androidDevices;
  private static JSONArray iOSDevices;
  private JsonFileParser jsonFileParser;

  static {
    JSONObject devices= new JsonFileParser().extractJsonFromFile("../devices.json");
    DeviceStore.androidDevices = (JSONArray) devices.get("android");
    DeviceStore.iOSDevices = (JSONArray) devices.get("iOS");
  }

  public static JSONObject getDeviceByPlatform(String platform) {
    JSONArray devices;
    switch (platform) {
      case "iOS":
        devices = iOSDevices;
        break;
      default:
        throw new IllegalArgumentException("No Such platform");
    }
    return (JSONObject) devices.stream().findFirst().get();
  }
  
  public static JSONObject getDeviceByUDID(String platform, String udid) {
	    JSONArray devices;
	    switch (platform) {
	      case "iOS":
	        devices = iOSDevices;
	        break;
	      default:
	        throw new IllegalArgumentException("No Such platform");
	    }
	    return (JSONObject) devices.stream().filter(device -> ((JSONObject)device).get("udid").toString().equals(udid))
	      .findFirst().get();
	  }
}
