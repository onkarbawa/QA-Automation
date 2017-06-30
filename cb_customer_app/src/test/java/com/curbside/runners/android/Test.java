package com.curbside.runners.android;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by bawa.onkar on 23/06/17.
 */

@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/com.curbside.automation.customerApp/android/Test.feature",
        plugin = "json:target/cucumber-report.json",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false,
        glue = {"com.curbside.automation.steps"})

@org.testng.annotations.Test
public class Test extends AbstractTestNGCucumberTests {
}
