package com.curbside.automation.uifactory;

/**
 * @author kumar.anil
 *
 */

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import com.curbside.automation.devicefactory.DeviceStore;

public class WebDriverListener implements IInvokedMethodListener, ISuiteListener {

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

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite suite) {
		
		System.out.println("Starting suite " + suite.getName());

		String platform= suite.getXmlSuite().getParameter("platform");
		if(platform.equalsIgnoreCase("ios"))
		{
			suite.getXmlSuite().setThreadCount(DeviceStore.getIOSDeviceCount());
		}
		
		if(platform.equalsIgnoreCase("android"))
		{
			suite.getXmlSuite().setThreadCount(DeviceStore.getAndroidDeviceCount());
		}
	}
}