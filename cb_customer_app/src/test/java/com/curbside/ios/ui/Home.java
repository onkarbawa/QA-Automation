package com.curbside.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import org.openqa.selenium.By;

import com.curbside.automation.uifactory.UIElement;

import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * @author kumar.anil
 * Page that appears after skipping into or clicking Get Started
 */

public class Home {
	
	static UIElement nearBy= UIElement.byXpath("//XCUIElementTypeOther[3]/XCUIElementTypeStaticText[1]");
	static UIElement search = UIElement.byAccessibilityId("Search");
	
	static UIElement searchNearByTextBox= UIElement.byAccessibilityId("Search All Nearby");
	static UIElement cityZipSearchTextBox= UIElement.byAccessibilityId("City, Zip or Address");
	
	static UIElement currentLocation= UIElement.byAccessibilityId("Current Location");
	public static UIElement loadingIcon= UIElement.byAccessibilityId("In progress");

	Steps steps = new Steps();
	Welcome welcome = new Welcome();
	Search searchpage = new Search();

	@Then("^I should see 'Nearby stores' landing page$")
	public void isDisplayed() throws Throwable
	{
		try {
			Assert.assertTrue(nearBy.isDisplayed());
		} finally {
			MobileDevice.getScreenshot(true);
		}
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
		//TODO: Test environment should come from suite file and JVM arguments
		search.tap();
		searchpage.searchField.sendKeys("_#csndc#env#s");
		searchpage.search.tap();
	}
	
	@Given("I select '(.*)' > '(.*)' location")
	public void setLocation(String category, String cityName) throws Throwable
	{
		currentLocation.tap();
		UIElement.byAccessibilityId(category).tap();
		UIElement.byAccessibilityId(cityName).scrollTo().tap();
	}
	
	@Given("I search for '(.*)' location")
	public void searchForLocation(String cityName) throws Throwable
	{
		currentLocation.tap();
		cityZipSearchTextBox.sendKeys(cityName);
		UIElement.byAccessibilityId(cityName).waitFor(30).tap();
		
		loadingIcon.waitForNot(30);
	}
	
	@Given("I search for '(.*)' product")
	public void searchForProduct(String cityName) throws Throwable
	{
		search.tap();
		searchNearByTextBox.sendKeys(cityName);
		UIElement.byAccessibilityId(cityName).tap();
		
		loadingIcon.waitForNot(30);
	}
	
	@Given("I select '(.*)' retailer partner on stores screen")
	public void selectRetailerPartner(String retailerPartner) throws Throwable
	{
		UIElement.byAccessibilityId(retailerPartner).scrollTo().tap();
		loadingIcon.waitForNot(30);
	}
	
	@Given("I select 1st retailer partner on stores screen")
	public void select1stRetailerPartner() throws Throwable
	{
		UIElement.byClass("XCUIElementTypeCell").tap();
		loadingIcon.waitForNot(30);
	}
	
	@Given("I select 1st product from list")
	public void select1stProduct() throws Throwable
	{
		UIElement.byXpath("//XCUIElementTypeCollectionView//XCUIElementTypeImage").tap();
	}

	@And("^I am on Main Screen$")
	public void iAmOnMainScreen() throws Throwable {
		steps.acceptLocationAlert();
		welcome.skipIntro.tap();
	}


}
