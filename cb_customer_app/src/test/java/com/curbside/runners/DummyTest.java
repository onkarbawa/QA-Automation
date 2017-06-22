package com.curbside.runners;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		monochrome = true,
		features = "src/test/resources/com.curbside.automation.customerApp/Dummy.feature",
		plugin = "json:target/cucumber-report.json",
		format = { "pretty","html: cucumber-html-reports",
        "json: cucumber-html-reports/cucumber.json" },
		dryRun = false,
		glue = {"com.curbside.automation.steps"})

@Test
public class DummyTest extends AbstractTestNGCucumberTests{

}