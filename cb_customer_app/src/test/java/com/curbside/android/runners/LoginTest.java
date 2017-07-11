package com.curbside.android.runners;

import com.curbside.automation.uifactory.DriverFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by kumar.nipun on 7/4/2017.
 */
@CucumberOptions(
  monochrome = true,
  features = "src/test/resources/android/features/Login.feature",
  plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
  format = { "pretty","html: cucumber-html-reports",
    "json: cucumber-html-reports/cucumber.json" },
  dryRun = false, strict= true,
  glue = {"com.curbside.android.ui", "com.curbside.automation.uifactory", "com.curbside.automation.steps"})

@Test
public class LoginTest extends AbstractTestNGCucumberTests {
  @BeforeMethod
  public void setup() {

  }

  @AfterMethod
  public void cleanUp() {
    try {
      DriverFactory.releaseDriver();
    } catch (Throwable th) {
      th.printStackTrace();
    }

  }
}
