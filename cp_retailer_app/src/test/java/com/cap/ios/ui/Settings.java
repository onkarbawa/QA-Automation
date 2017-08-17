package com.cap.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * Created by bawa.onkar on 05/07/17.
 */
public class Settings extends AbstractScreen {

	UIElement settings = new UIElement(By.name("Settings"));
	UIElement location = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Location']"));
	UIElement backgroundAppRefresh= UIElement.byXpath("//XCUIElementTypeSwitch[@name='" + "Background App Refresh" + "']");
	UIElement allowNotifications = UIElement.byXpath("//XCUIElementTypeSwitch[@name='" + "Allow Notifications" + "']");

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

	@When("^I turn '(.*)' '(.*)' for '(.*)'$")
	public void iTurnFor(String ONorOFF, String button, String appName) throws Throwable {
		UIElement toggleButton = UIElement.byXpath("//XCUIElementTypeSwitch[@name='" + button + "']");

		String currentButtonValue = toggleButton.getAttribute("value");
		System.out.println("Current toggle value is " + currentButtonValue);

		currentButtonValue = currentButtonValue.equals("true") ? "ON" : "OFF";

		if(!ONorOFF.equalsIgnoreCase(currentButtonValue))
			toggleButton.tap();

//		String currentBackgroundRefreshValue= backgroundAppRefresh.getAttribute("value");
//		System.out.println("Current toggle value is " + currentBackgroundRefreshValue);
//
//		currentBackgroundRefreshValue = currentBackgroundRefreshValue.equals("true") ? "ON" : "OFF";
//
//		if(!ONorOFF.equalsIgnoreCase(currentBackgroundRefreshValue))
//			backgroundAppRefresh.tap();
		
		MobileDevice.getScreenshot(true);
	}
}
