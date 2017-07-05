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
	
	static UIElement nearBy= new UIElement(By.xpath("//XCUIElementTypeOther[3]/XCUIElementTypeStaticText[1]"));


	@Then("^I should see 'Nearby stores' landing page$")
	public boolean isDisplayed() throws Throwable
	{
		return nearBy.isDisplayed();
	}
}
