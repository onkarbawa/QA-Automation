package com.curbside.automation.common.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by kumar.nipun on 6/16/2017.
 */
public class Helpers {
  /**
   * Checks whether a particular property exist on OS
   * @param key Property name
   * @return true if property exists on OS, false otherwise
   */
  public static boolean isPropertySpecifiedAtOsLevel(String key) {
    return StringUtils.isNotBlank(System.getProperty(key));
  }
  
  public static String getRandomEmailId()
  {
	  return RandomStringUtils.randomAlphabetic(4).toLowerCase() + "_" + RandomStringUtils.randomNumeric(7) + "@mailinator.com";
  }

  public static String getRandomFirstName() {
    return RandomStringUtils.randomAlphabetic(5).toLowerCase();
  }

  public static String getRandomLastName() {
    return RandomStringUtils.randomAlphabetic(5).toLowerCase();
  }
}
