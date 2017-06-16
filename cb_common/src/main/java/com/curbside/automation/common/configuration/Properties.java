package com.curbside.automation.common.configuration;

import org.apache.log4j.Logger;

/**
 * Created by kumar.nipun on 6/16/2017.
 */
public class Properties {
  final static Logger logger = Logger.getLogger(Properties.class);
  static Configuration configuration = new Configuration();

  /**
   * Gets the value of platform property
   * @return value as String
   */
  public static String getPlatForm() {
    return configuration.get("platform");
  }
}
