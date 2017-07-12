package com.curbside.automation.uifactory;

import java.nio.file.Files;

import com.curbside.automation.devicefactory.DeviceStore;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class CucumberHooks {

	@After
	public void embedScreenshot(Scenario scenario) {

		try {
			byte[] screenshot = MobileDevice.getScreenshotAsBytes();
			scenario.embed(screenshot, "image/png");
			
			byte[] source = Files.readAllBytes(MobileDevice.getSource().toPath());
			scenario.embed(source, "text/html");
			
		} catch (Throwable e) {
		}
	}
}