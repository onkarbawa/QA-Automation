package com.curbside.automation.common.json;

import com.curbside.automation.common.configuration.Properties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by kumar.nipun on 6/23/2017.
 */
public class DeviceStore {

  private JSONArray androidDevices;
  private JSONArray iOSDevices;
  private JsonFileParser jsonFileParser;
  private String deviceFileName;

  public DeviceStore() {
    jsonFileParser = new JsonFileParser();
    deviceFileName = Properties.getDeviceJsonFileName();
    this.androidDevices = (JSONArray) jsonFileParser.extractJsonFromFile(deviceFileName).get("android");
    this.iOSDevices = (JSONArray) jsonFileParser.extractJsonFromFile(deviceFileName).get("ios");
  }

  /**
   * Gets the device by UDID
   * @param platform platform type
   * @param udid UDID as String
   * @return Device as JSONObject
   */
  public JSONObject getDeviceByUDID(Platform platform, String udid) {
    JSONArray devices;
    switch (platform) {
      case IOS:
        devices = iOSDevices;
        break;
      case ANDROID:
        devices = androidDevices;
        break;
      default:
        throw new IllegalArgumentException("No Such platform");
    }
    return (JSONObject) devices.stream().filter(device -> ((JSONObject)device).get("UDID").toString().equals(udid))
      .findFirst().get();
  }
}
