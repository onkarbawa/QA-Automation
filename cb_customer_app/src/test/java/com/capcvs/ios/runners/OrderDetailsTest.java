package com.capcvs.ios.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by bawa.onkar
 */

@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/capcvs/features/ios/OrderDetails.feature",
        plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
        format = {"pretty", "html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json"},
        dryRun = false, strict = true,
        glue = {"com.curbside.ios.ui", "com.cap.ios.ui", "com.capcvs.ios.ui", "com.curbside.automation.uifactory"})

@Test
public class OrderDetailsTest extends AbstractTestNGCucumberTests {
}
