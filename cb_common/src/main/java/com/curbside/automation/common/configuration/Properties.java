package com.curbside.automation.common.configuration;

import com.curbside.automation.common.utilities.Helpers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Created by kumar.nipun on 6/16/2017.
 */
public class Properties {
  final static Logger logger = Logger.getLogger(Properties.class);
  static Configuration configuration = new Configuration();
  
  private static ThreadLocal<Map<String,String>> variables = new ThreadLocal<>();
  
  static{
	  variables.set(new HashMap<>());
  }
  
  public static void setVariable(String name, String value)
  {
	  if(variables.get() == null)
		  variables.set(new HashMap<>());
	  
	  variables.get().put(name, value);
  }
  
  public static String getVariable(String name)
  {
	  return variables.get().get(name);
  }

  /**
   * Gets the value of platform property
   * @return value as String
   */
  public static String getPlatForm() {
    return configuration.get("platform");
  }

  /**
   * Gets the value of New command time out property
   * @retur value as Integer
   */
  public static int getNewCommandTimeout(){
      return (Integer.parseInt(configuration.get("newCommandTimeOut")));
  }

  /**
   * Gets the value of Android device name
   * @return value as string
   */
  public static String getAndroidDeviceName(){
      return configuration.get("androidDeviceName");
  }

  /**
   * Gets the value of Android app location
   * @return value as String
   */
  public static String getAndroidAppLocation(){
        return configuration.get("androidAppLocation");
    }

  /**
   * Gets the value of the IOS device name
   * @return value as String
   */
  public static String getIosDeviceName(){
      return configuration.get("iosDeviceName");
  }

  /**
   * Gets the value of IOS app location
   * @return value as String
   */
  public static String getIosAppLocation(){
    return configuration.get("iosAppLocation");
  }

  /**
   *  Gets Android app package name
   *  @return value as String
   */
  public static String getAndroidAppPackage(){
      return configuration.get("androidAppPackage");
  }

  /**
   *  Gets Android app activity name
   *  @return vaue as String
   */
  public static String getAndroidAppActivity(){
      return configuration.get("androidAppActivity");
  }

  /**
   * Gets IOS device UDID
   * @return value as String
   */
  public  static String getIosDeviceUDID(){
      return configuration.get("iosDeviceUDID");
  }

  /**
   *  Gets IOS device platform version
   *  @return value as String
   */
  public static String getIosPlatformVersion(){
      return configuration.get("iosPlatformVersion");
  }

  /**
   *  Gets Implicitly wait in seconds
   *  @return value as Integer
   */
  public static int getImplicitWaitInSeconds(){
      return Integer.parseInt(configuration.get("implicitWaitInSeconds"));
  }

  /**
   * Gets the device json file path
   * @return
   */
  public static String getDeviceJsonFileName() {
    return Helpers.isPropertySpecifiedAtOsLevel("deviceFile") ?
      System.getProperty("deviceFile") :
      Properties.class.getClassLoader().getResource(configuration.get("deviceFile")).getPath();
  }
}
