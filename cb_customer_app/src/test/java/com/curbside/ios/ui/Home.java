package com.curbside.ios.ui;

import com.curbside.automation.uifactory.Steps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
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
	public UIElement search = new UIElement(By.name("Search"));

	Steps steps = new Steps();
	Welcome welcome = new Welcome();
	Search searchpage = new Search();

	@Then("^I should see 'Nearby stores' landing page$")
	public boolean isDisplayed() throws Throwable
	{
		return nearBy.isDisplayed();
	}

	@And("^I am on Home Screen$")
	public void iAmOnHomeScreen() throws Throwable {
		steps.acceptLocationAlert();
		welcome.skipIntro.tap();
		welcome.okWithMe.tap();
		steps.acceptLocationAlert();
	}

	@And("^I have selected test environment$")
	public void iHaveSelectedTestEnvironment() throws Throwable {
		search.tap();
		searchpage.searchField.sendKeys("_#csndc#env#s");
		searchpage.search.tap();
	}
}
