package com.cap.ios.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by bawa.onkar
 */

@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/cap/features/ios/features/TaskManagement1.feature",
        plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report_cap.html"},
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false, strict= true,
        glue = {"com.curbside.ios.ui", "com.curbside.automation.uifactory","com.cap.ios.ui"})

@Test
public class TaskManagement1Test extends AbstractTestNGCucumberTests {
}
