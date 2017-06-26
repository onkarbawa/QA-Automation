package com.curbside.automation.uifactory;

/**
 * @author kumar.anil
 *
 */

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

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
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
	}
}