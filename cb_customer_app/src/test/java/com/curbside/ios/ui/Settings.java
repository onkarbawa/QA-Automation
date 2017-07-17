package com.curbside.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

/**
 * Created by bawa.onkar on 05/07/17.
 */
public class Settings extends AbstractScreen {

	UIElement settings = new UIElement(By.name("Settings"));
	UIElement location = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Location']"));

	public Settings() {
		// TODO Auto-generated constructor stub
	}

	@Given("^'Location' preference is set as '(.*)' for '(.*)' app$")
	public void locationPreferenceIsSetAsForApp(String value, String appName) throws Throwable {
		try {
			welcomeScreen.btnAllow.tapOptional();
		}catch (Exception e){}
		MobileDevice.setLocationPreference(appName, value);
	}

	@Then("^I should see Location Services Disabled screen$")
	public void iShouldSeeLocationServicesDisabledScreen() throws Throwable {
		settings.isDisplayed();
		MobileDevice.getScreenshot(true);
	}

	@And("^I set '(.*)' permission as '(.*)'$")
	public void iSetAs(String appName, String newValue) throws Throwable {
		location.waitFor(20);
		location.tap();
		new UIElement(By.name(newValue)).tap();
	}
}
