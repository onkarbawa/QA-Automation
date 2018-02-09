package com.curbside.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * @author kumar.anil This is page that appears when you launch application for
 *         first time
 */
public class Welcome extends AbstractScreen {

	UIElement skipIntro = new UIElement(By.name("Skip Intro"));
	UIElement okWithMe = new UIElement(By.name("OK with me"));
	UIElement btnGetStarted = UIElement.byAccessibilityId("Get Started");
	UIElement btnAllow = UIElement.byName("Allow");

	Steps steps = new Steps();

	public Welcome() {
		// TODO Auto-generated constructor stub
	}

	@Then("^I am on Welcome Screen$")
	public void iAmOnWelcomeScreen() throws Throwable {

		try {
			steps.acceptLocationAlert();
		} catch (Exception e) {
		}

		Assert.assertEquals(skipIntro.getText(), "Skip Intro", "Application not launch to main screen");
	}

	public void wait_for_app_launch() throws Throwable {
		for (int i = 0; i < 10; i++) {
			if (homeScreen.iconSearch.isDisplayed())
				return;

			if(skipIntro.isDisplayed() || btnGetStarted.isDisplayed()
	    			|| footerTabsScreen.btnMyAccount.isDisplayed() || okWithMe.isDisplayed()
	    			|| btnAllow.isDisplayed())
	    		break;
	    	else
	    		Thread.sleep(1000);
		}
	    MobileDevice.getScreenshot(true);
	}

	@And("^I '(.*)' the pop-up if displayed$")
	public void iCloseIfPopUpIsDisplayed(String btnClose) throws Throwable {
		if (UIElement.byName("" + btnClose + "").isDisplayed()) {
			UIElement.byName("" + btnClose + "").tap();
		}
	}
}