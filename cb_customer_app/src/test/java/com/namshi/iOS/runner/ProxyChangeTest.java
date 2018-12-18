package com.namshi.iOS.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

/**
 * Created by bawa.onkar
 */
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/proxyChange/iOS/ProxyChange.feature",
        plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
        format = {"pretty", "html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json"},
        dryRun = false, strict = true,
        glue = {"com.proxyChange.iOS.ui", "com.curbside.automation.uifactory"})

@Test
public class ProxyChangeTest extends AbstractTestNGCucumberTests {
}
