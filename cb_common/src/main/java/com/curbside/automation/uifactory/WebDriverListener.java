package com.curbside.automation.uifactory;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class WebDriverListener implements IInvokedMethodListener {

	@Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            try {
                WebDriver driver = DriverFactory.getDriver();
                if (driver != null) {
                    driver.quit();
                }
            }
            catch(Exception e){}
            finally {
			}
        }
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
	}
}