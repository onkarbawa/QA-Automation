package com.curbside.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import com.curbside.automation.uifactory.XmlElement;

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
	
	UIElement thisPageElement = UIElement.byPredicate("label == 'Skip Intro' or label == 'OK with me' or label == 'Get Started'");

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
			
			try {
				MobileDevice.getAlertText(); return;
			} catch (Exception e) {}
			
			if (thisPageElement.isDisplayed())
 				return;
			
			Thread.sleep(100);
		}
	    
		//MobileDevice.getScreenshot(true);
	}
}