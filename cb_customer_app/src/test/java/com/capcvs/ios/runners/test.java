package com.capcvs.ios.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover on 25/10/17.
 */

@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/cap/features/android/ErrorMessage.feature",
        plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report_cap.html"},
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false, strict= true,
        glue = {"com.curbside.android.ui", "com.curbside.automation.uifactory","com.cap.android.ui"})

@Test
public class test extends AbstractTestNGCucumberTests {

}
