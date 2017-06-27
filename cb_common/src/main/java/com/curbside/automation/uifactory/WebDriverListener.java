package com.curbside.automation.uifactory;

/**
 * @author kumar.anil
 *
 */

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.curbside.automation.devicefactory.DeviceStore;

public class WebDriverListener implements IInvokedMethodListener {

	@Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            try {
				DriverFactory.releaseDriver();
			} catch (MalformedURLException e) {
			}
            
            DeviceStore.releaseDevice();
        }
	}

	@Override
	public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult testResults) {

		ITestContext context= testResults.getTestContext();
		
		//platform should be defined in testNG suite file
		context.setAttribute("platform", 
				testResults.getTestContext().getSuite().getParameter("platform"));
		
		DeviceStore.setPlatform(context.getAttribute("platform").toString());
		
	}
}