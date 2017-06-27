package com.curbside.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by bawa.onkar on 27/06/17.
 */

@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/com.curbside.automation.customerApp/SignUp.feature",
        plugin = "json:target/cucumber-report.json",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false,
        glue = {"com.curbside.automation.steps"})

@Test
public class SignUpTest extends AbstractTestNGCucumberTests {
}
