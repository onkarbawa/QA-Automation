package com.curbside.runners.android;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover on 28/06/17.
 */
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/com.curbside.automation.customerApp/android/AppLaunch.feature",
        plugin = "json:target/cucumber-report.json",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false,
        glue = {"com.curbside.automation.steps"})

@Test
public class AppLaunch extends AbstractTestNGCucumberTests {
}
