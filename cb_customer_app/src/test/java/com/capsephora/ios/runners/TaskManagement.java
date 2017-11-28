package com.capsephora.ios.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by grover.hitesh
 */
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/capsephora/features/ios/TaskManagement.feature",
        plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false, strict= true,
        glue = {"com.cap.ios.ui","com.capsephora.ios.ui", "com.curbside.automation.uifactory", "com.curbside.ios.ui"})

@Test
public class TaskManagement extends AbstractTestNGCucumberTests {
}
