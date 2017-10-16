package com.curbside.ios.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by bawa.onkar on 7/4/17.
 */

@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/curbside/features/ios/features/SignUp.feature",
        plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false, strict= true,
        glue = {"com.curbside.ios.ui", "com.curbside.automation.uifactory"})
@Test
public class SignUpTest extends AbstractTestNGCucumberTests {
}
