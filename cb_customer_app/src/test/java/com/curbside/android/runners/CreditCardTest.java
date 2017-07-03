package com.curbside.android.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover on 03/07/17.
 */
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/android/features/CreditCard.feature",
        plugin = "json:target/cucumber-report.json",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false,strict= true,
        glue = {"com.curbside.android.ui", "com.curbside.automation.uifactory","com.curbside.automation.steps"})

@Test
public class CreditCardTest extends AbstractTestNGCucumberTests {
}
