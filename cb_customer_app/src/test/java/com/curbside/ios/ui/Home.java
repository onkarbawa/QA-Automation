package com.curbside.ios.ui;

import cucumber.api.PendingException;
import org.openqa.selenium.By;

import com.curbside.automation.uifactory.UIElement;

import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * @author kumar.anil
 * Page that appears after skipping into or clicking Get Started
 */

public class Home {
	
	static UIElement nearBy= new UIElement(By.name("Near"));
	static UIElement location =  new UIElement(By.name("Current Location"));

	@Then("^I should see 'Nearby stores' landing page$")
	public boolean isDisplayed() throws Throwable
	{
		return nearBy.getElement().isDisplayed();
	}

	@Then("^I should see the Store Selection Screen$")
	public void iShouldSeeTheStoreSelectionScreen() throws Throwable {
		Assert.assertEquals(location.getText(), "Current Location",
				"The pointer is not landing on current location page");
	}
}
