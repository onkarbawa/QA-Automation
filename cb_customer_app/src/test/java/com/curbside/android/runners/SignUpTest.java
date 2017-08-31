package com.curbside.android.runners;

import com.curbside.automation.uifactory.DriverFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by hitesh.grover on 28/06/17.
 */
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/curbside/features/android/SignUp.feature",
        plugin = {"json:target/cucumber-report.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false,strict= true,
        glue = {"com.curbside.android.ui", "com.curbside.automation.uifactory","com.curbside.automation.steps"})

@Test
public class SignUpTest extends AbstractTestNGCucumberTests {

}
