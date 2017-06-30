package com.curbside.automation.uifactory;
import org.apache.commons.lang3.NotImplementedException;
import org.json.JSONObject;

/**
 * @author kumar.anil
 *
 */

import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.devicefactory.IOSApps;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.Dimension;

public class MobileDevice {

    private static Dimension size;
	public MobileDevice() {
    }
	
	public static void launchSettings() throws Exception
	{
		//Get current device
		JSONObject device= DeviceStore.getDevice();
		device.remove("app");
		device.remove("bundleId");
		device.remove("ipa");
		
		if(DeviceStore.getPlatform().equalsIgnoreCase("ios"))
		{
			device.put("bundleId", IOSApps.Settings);
			DriverFactory.releaseDriver();
			DriverFactory.getDriver(device);
		}
		else
		{
			throw new NotImplementedException("Not yet implemented");
		}
	}

	public static int getHeight() throws Exception {
        size = DriverFactory.getDriver().manage().window().getSize();
	    return size.getHeight();
    }

    public static int getWidth() throws Exception {
        size = DriverFactory.getDriver().manage().window().getSize();
        return size.getWidth();
    }
}