package com.curbside.ios.runners;

import cucumber.api.CucumberOptions;
import org.testng.annotations.Test;

/**
 * Created by bawa.onkar on 7/4/17.
 */

@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/ios/features/Login.feature",
        plugin = "json:target/cucumber-report.json",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false, strict= true,
        glue = {"com.curbside.ios.ui", "com.curbside.automation.uifactory"})
@Test
public class SignUpTest {
}
