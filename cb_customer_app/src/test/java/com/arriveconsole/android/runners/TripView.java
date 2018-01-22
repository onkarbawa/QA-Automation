package com.arriveconsole.android.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover
 */
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/arriveconsole.feature/android/TripView.feature",
        plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false, strict= true,
        glue = {"com.arriveconsole.android.ui", "com.curbside.automation.uifactory"})

@Test
public class TripView extends AbstractTestNGCucumberTests{
}
