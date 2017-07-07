package com.curbside.android.ui;

import com.curbside.automation.uifactory.MobileDevice;
import cucumber.api.java.en.Given;

/**
 * Created by kumar.nipun on 7/5/2017.
 */
public class Settings {

  @Given("^'Location' preference is set as '(.*)' for '(.*)' app$")
  public void locationPreferenceIsSetAsForApp(String value, String appName) throws Throwable {
    MobileDevice.setLocationPreference(appName, value);
  }
}
