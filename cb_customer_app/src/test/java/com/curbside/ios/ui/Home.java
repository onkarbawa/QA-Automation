package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.When;

import com.curbside.automation.uifactory.UIElement;

import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * @author kumar.anil Page that appears after skipping into or clicking Get
 *         Started
 */

public class Home extends AbstractScreen {

	UIElement nearBy = UIElement.byXpath("//XCUIElementTypeOther[3]/XCUIElementTypeStaticText[1]");
	UIElement iconSearch = UIElement.byAccessibilityId("Search");
	UIElement btnSearchKeyboard = UIElement.byAccessibilityId("Search");
	UIElement txtProductSearch = UIElement.byClass("UISearchBarTextField");
	UIElement txtSearchNearBy = UIElement.byClass("XCUIElementTypeSearchField");

	UIElement currentLocation = UIElement.byAccessibilityId("Current Location");
	UIElement cityZipSearchTextBox = UIElement.byAccessibilityId("City, Zip or Address");

	UIElement productImage = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Popular']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeImage");
	UIElement productName = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Popular']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeStaticText");


	Steps steps = new Steps();
	Welcome welcome = new Welcome();
	Search searchpage = new Search();

	public Home() {
		// TODO Auto-generated constructor stub
	}

	@Then("^I should see 'Nearby stores' landing page$")
	public void isDisplayed() throws Throwable {
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

	@Given("I select '(.*)' > '(.*)' location")
	public void setLocation(String category, String cityName) throws Throwable {
		currentLocation.tap();
		UIElement.byAccessibilityId(category).tap();
		UIElement.byAccessibilityId(cityName).scrollTo().tap();
	}

	@Given("I search for '(.*)' location")
	public void searchForLocation(String cityName) throws Throwable {
		footerTabsScreen.tapShop();
		currentLocation.tap();
		cityZipSearchTextBox.sendKeys(cityName);
		UIElement.byAccessibilityId(cityName).waitFor(40).tap();

		loadingIcon.waitForNot(30);
	}

	@Given("I search for '(.*)' product")
	public void searchForProduct(String productName) throws Throwable {
		iconSearch.tap();
		Thread.sleep(1000);
		txtSearchNearBy.sendKeys(productName);
	}

	@And("^I have selected test environment$")
	public void iHaveSelectedTestEnvironment() throws Throwable {
		iconSearch.tap();
		Thread.sleep(1000);
		txtSearchNearBy.sendKeys("_#csndc#env#s");
		btnSearchKeyboard.tap();
		loadingIcon.waitForNot(30);
		MobileDevice.getScreenshot(true);
	}

	@Given("I select '(.*)' retailer partner on stores screen")
	public void selectRetailerPartner(String retailerPartner) throws Throwable {
		UIElement.byAccessibilityId(retailerPartner).scrollTo().tap();
		loadingIcon.waitForNot(30);
	}

	@Given("I select 1st retailer partner on stores screen")
	public void select1stRetailerPartner() throws Throwable {
		UIElement.byClass("XCUIElementTypeCell").waitFor(10).tap();
		loadingIcon.waitForNot(30);
	}

	@Given("I select 1st product from list")
	public void select1stProduct() throws Throwable {
		UIElement.byXpath("//XCUIElementTypeCollectionView//XCUIElementTypeImage").tap();
	}

	@Given("I add any product to cart in '(.*)' location")
	public void i_add_any_product_in_location(String location) throws Throwable {
		footerTabsScreen.tapShop();
		searchForLocation(location);
		select1stRetailerPartner();
		select1stProduct();
		productDetailsScreen.addToCart();
		MobileDevice.getScreenshot(true);
	}

	@And("^I am on Shop Screen$")
	public void iAmOnShopScreen() throws Throwable {
		//steps.acceptLocationAlert();
		welcome.skipIntro.tap();
	}

	@And("^I am on '(.*)' location 'Stores' Screen$")
	public void iAmOnLocationStoresScreen(String location) throws Throwable {
		footerTabsScreen.tapShop();
		searchForLocation(location);
	}

	@And("^I select a store$")
	public void iSelectAStore() throws Throwable {
		select1stRetailerPartner();
	}

	@When("^I tap on product from the list$")
	public void iTapOnProductFromTheList() throws Throwable {
		select1stProduct();
	}

	@And("^I add product in cart$")
	public void iAddProductInCart() throws Throwable {
		Properties.setVariable("productName",productDetailsScreen.getProductName());
		productDetailsScreen.addToCart();
	}

	@Then("^I should see following products listed on partner screen$")
	public void iShouldSeeFollowingProductsListedOnPartnerScreen() throws Throwable {
		Assert.assertTrue(productImage.isDisplayed(),"Product Image is not displayed");
		Assert.assertTrue(productName.isDisplayed(),"Product Name is not displayed");
	}

	@Then("^I should see nearby stores$")
	public void iShouldSeeNearbyStores() throws Throwable {
		Assert.assertTrue(UIElement.byClass("XCUIElementTypeCell").waitFor(10).isDisplayed(),"Stores are not displayed in selected location");
	}
}
