package com.curbside.ios.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by kumar.anil
 */

@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/ios/features/AppLaunch.feature",
        plugin = "json:target/cucumber-report.json",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false, strict= true,
        glue = {"com.curbside.ios.ui", "com.curbside.automation.uifactory"})

@Test
public class AppLaunchTest extends AbstractTestNGCucumberTests {
}