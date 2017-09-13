package com.cap.android.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover
 */

@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/cap/features/android/ProductDetail.feature",
        plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report_cap.html"},
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false, strict= true,
        glue = {"com.curbside.automation.uifactory","com.cap.android.ui"})

@Test
public class ProductDetail extends AbstractTestNGCucumberTests {

}
