package com.curbside.ios.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;



/**
 * Created by kumar.anil
 */

@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/ios/features/AppLaunch.feature",
        plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false, strict= true,
        glue = {"com.curbside.ios.ui", "com.curbside.automation.uifactory"})

@Test
public class AppLaunchTest extends AbstractTestNGCucumberTests {
	
	@AfterClass
    public static void teardown() {
        //Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        //Reporter.setSystemInfo("user", System.getProperty("user.name"));
        //Reporter.setSystemInfo("os", "Mac OSX");
        //Reporter.setTestRunnerOutput("Sample test runner output message");
    }
}
