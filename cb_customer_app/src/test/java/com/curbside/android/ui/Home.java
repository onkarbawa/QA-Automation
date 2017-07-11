package com.curbside.android.ui;

import com.curbside.automation.uifactory.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * @author hitesh.grover
 * Page that appears after skipping into or clicking Get Started
 */

public class Home extends AbstractScreen {
	
	static UIElement nearBy= new UIElement(By.name("Near"));
	static UIElement locationLink = UIElement.byId("com.curbside.nCurbside:id/button_location");
	static UIElement shopNearLabel = UIElement.byXpath("//*[@resource-id='com.curbside.nCurbside:id/container_linear_layout_toolbar']/android.widget.TextView");
	static UIElement myAccount = new UIElement(By.id("com.curbside.nCurbside:id/bb_bottom_bar_icon"));
	static UIElement sorryMessage = UIElement.byXpath("//*[@text='Sorry, we’re not in that area yet.']");
	UIElement searchIcon = UIElement.byId("com.curbside.nCurbside:id/action_search");
	UIElement searchBox = UIElement.byId("com.curbside.nCurbside:id/search_src_text");
	UIElement searchBackButton = UIElement.byId("com.curbside.nCurbside:id/img_tool_back");


	UIElement apiHost = UIElement.byXpath("//*[@resource-id='android:id/list']/android.widget.LinearLayout[1]");
	UIElement apiHostTextField = UIElement.byId("android:id/edit");
	UIElement apiHostOkButton = UIElement.byId("android:id/button1");
	UIElement debugBackButton = UIElement.byAccessibilityId("Navigate up");
	UIElement myLocationButton = UIElement.byAccessibilityId("My Location");

	Steps steps = new Steps();
	//Welcome welcome = new Welcome();


	@Then("^I should see 'Nearby stores' landing page$")
	public void isDisplayed() throws Throwable
	{	try {
			Assert.assertTrue(shopNearLabel.isDisplayed());
			} finally {
			MobileDevice.getScreenshot(true);
			}
	}

	@And("^I tap on My Account icon$")
	public void iTapOnMyAccountIcon() throws Throwable {
		myAccount.waitFor(5);
		myAccount.tap();
	}

	@And("^I am on Home Screen$")
	public void iAmOnHomeScreen() throws Throwable {
		welcomeScreen.skipIntro.waitFor(5);
		welcomeScreen.skipIntro.tap();
		welcomeScreen.okButton.waitFor(5);
		welcomeScreen.okButton.tap();
		steps.acceptLocationAlert();
        steps.acceptLocationAlert();
	}

	@And("^I have selected test environment$")
	public void iHaveSelectedTestEnvironment() throws Throwable {
		searchIcon.waitFor(5);
		searchIcon.tap();
		searchBox.waitFor(5);
		searchBox.sendKeys("_#csndc#ena");
		((AndroidDriver) DriverFactory.getDriver()).pressKeyCode(AndroidKeyCode.ENTER);

		apiHost.waitFor(3);
		if (myLocationButton.isDisplayed()) {
			debugBackButton.tap();
			apiHost.waitFor(3);
		}
		apiHost.tap();

		apiHostTextField.waitFor(3);
		apiHostTextField.clearText();
		apiHostTextField.sendKeys("https://api-s.shopcurbside.com");
		apiHostOkButton.tap();
		debugBackButton.waitFor(2);
		debugBackButton.tap();
        AndroidDevice.launchCurbsideActivity();
		//steps.launchApplication("Curbside");
	}
}
