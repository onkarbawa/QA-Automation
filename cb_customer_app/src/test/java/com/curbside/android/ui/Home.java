package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

/**
 * @author hitesh.grover
 * Page that appears after skipping into or clicking Get Started
 */

public class Home {
	
	static UIElement nearBy= new UIElement(By.name("Near"));
	static UIElement myAccount = new UIElement(By.id("com.curbside.nCurbside:id/bb_bottom_bar_icon"));
	
	@Then("^I should see 'Nearby stores' landing page$")
	public boolean isDisplayed() throws Throwable
	{
		return nearBy.getElement().isDisplayed();
	}

	@And("^I tap on My Account icon$")
	public void iTapOnMyAccountIcon() throws Throwable {
		myAccount.waitFor(5);
		myAccount.tap();
	}
}
