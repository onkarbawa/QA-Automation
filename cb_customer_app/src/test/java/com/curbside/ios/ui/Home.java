package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.When;

import com.curbside.automation.uifactory.UIElement;

import cucumber.api.java.en.Then;
import gherkin.lexer.Th;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;

/**
 * @author kumar.anil Page that appears after skipping into or clicking Get
 *         Started
 */

public class Home extends AbstractScreen {

	UIElement nearBy = UIElement.byXpath("//XCUIElementTypeOther[3]/XCUIElementTypeStaticText[1]");
	UIElement btnCancel = UIElement.byName("Cancel");
	UIElement iconSearch = UIElement.byName("Search");
	UIElement btnSearchKeyboard = UIElement.byXpath("//XCUIElementTypeKeyboard//XCUIElementTypeButton[@name='Search']");
	UIElement txtProductSearch = UIElement.byClass("UISearchBarTextField");
	UIElement txtSearchNearBy = UIElement.byClass("XCUIElementTypeSearchField");

	UIElement lnkCurrentLocation = UIElement.byXpath("//XCUIElementTypeStaticText[@label='Near ']/following-sibling::XCUIElementTypeButton");
	UIElement cityZipSearchTextBox = UIElement.byAccessibilityId("City, Zip or Address");

	UIElement productImage = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Popular']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeImage");
	UIElement productName = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Popular']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeStaticText");
	UIElement recentLocation = UIElement.byXpath("//XCUIElementTypeOther[XCUIElementTypeStaticText[contains(@name,'Recent Locations')]]/following-sibling::XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Palo Alto')]]");
	UIElement checkEnvironment = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[8]//XCUIElementTypeStaticText");

	Steps steps = new Steps();
	Welcome welcome = new Welcome();

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
	public void open() throws Throwable {
		welcomeScreen.wait_for_app_launch();
		try {
			for (int i = 0; i < 3; i++) {

				if(welcomeScreen.skipIntro.isDisplayed() || welcomeScreen.btnGetStarted.isDisplayed()
						|| welcomeScreen.okWithMe.isDisplayed() || welcomeScreen.btnAllow.isDisplayed())
				{
					welcomeScreen.btnAllow.tapOptional();
					welcomeScreen.skipIntro.tapOptional();
					welcomeScreen.btnGetStarted.tapOptional();
					welcomeScreen.okWithMe.tapOptional();
					commonSteps.acceptNotificationAlert();
					commonSteps.acceptLocationAlert();
				}
				else
					return;
			}
		} catch (Exception e) {
			System.out.println("inCAtchblock"+e.getMessage());
		}
	}

	@Given("I select '(.*)' > '(.*)' location")
	public void setLocation(String category, String cityName) throws Throwable {
		if(lnkCurrentLocation.getText().equals(cityName))
			return;
		
		lnkCurrentLocation.tap();
		UIElement.byAccessibilityId(category).tap();
		UIElement.byAccessibilityId(cityName).scrollTo().tap();
	}

	@Given("I search for '(.*)' location")
	public void searchForLocation(String cityName) throws Throwable {
		footerTabsScreen.tapShop();
		if(lnkCurrentLocation.getText().equals(cityName))
			return;
		
		lnkCurrentLocation.tap();
		cityZipSearchTextBox.sendKeys(cityName,false);
		try {
			UIElement.byAccessibilityId(cityName).waitFor(40).tap();
		}catch (Exception e){
			UIElement.byXpath("//XCUIElementTypeTable[@name='Search results']//XCUIElementTypeStaticText[@name='"+cityName+"']").waitFor(20).tap();
		}

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
		homeScreen.open();

		String envAPIKey = "_#csndc#env#s";
		if (DriverFactory.getEnvironment().equalsIgnoreCase(envAPIKey))
					return;

		iconSearch.tap();
		txtSearchNearBy.waitFor(5).sendKeys(envAPIKey, false);
		btnSearchKeyboard.tap();

		//btnCancel.tapOptional();
		loadingIcon.waitForNot(30);

		
		MobileDevice.getScreenshot(true);
		DriverFactory.setEnvironment(envAPIKey);
//		footerTabsScreen.tapMyAccount();
//		Steps.tapButton("Help");
//		checkEnvironment.scrollTo();
		MobileDevice.getScreenshot(true);
		DriverFactory.closeApp();
		DriverFactory.launchApp();
//		DriverFactory.getDriver(false);
		homeScreen.open();
	}

	@Given("I select '(.*)' retailer partner on stores screen")
	public void selectRetailerPartner(String retailerPartner) throws Throwable {
		UIElement.byAccessibilityId(retailerPartner).scrollTo().tap();
		loadingIcon.waitForNot(30);
	}

	@Given("I select 1st retailer partner on stores screen")
	public void select1stRetailerPartner() throws Throwable {

		UIElement.byXpath("//XCUIElementTypeCell").waitFor(10).tap();
		MobileDevice.getScreenshot(true);
		loadingIcon.waitForNot(30);
	}

	@Given("I select 1st product from list")
	public void select1stProduct() throws Throwable {
		UIElement.byXpath("//XCUIElementTypeCollectionView//XCUIElementTypeImage").tap();
		productDetailsScreen.btnAddtoCart.waitFor(5);
	}

	@Given("I select ('.*') retailer and add any product to cart")
	public void i_select_retailer_and_add_product(String retailer) throws Throwable {
		footerTabsScreen.tapShop();
		select1stRetailerPartner();
		storeDetails.iSelectNoProductFromList(1);
		productDetailsScreen.iAddQuantityOfIt(1);
		MobileDevice.getScreenshot(true);
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
		Thread.sleep(2000);
		//steps.acceptLocationAlert();
		//welcome.skipIntro.tap();
		footerTabsScreen.btnShop.tap();
	}

	@And("^I am on '(.*)' location 'Stores' Screen$")
	public void iAmOnLocationStoresScreen(String location) throws Throwable {
		footerTabsScreen.tapShop();
		searchForLocation(location);
	}

	@And("^I select a store$")
	public void iSelectAStore() throws Throwable {
		try {
		    footerTabsScreen.tapShop();
        }catch (Exception e){}
        select1stRetailerPartner();
	}

	@When("^I tap on product from the list$")
	public void iTapOnProductFromTheList() throws Throwable {
		select1stProduct();
	}

	@And("^I add product in cart$")
	public void iAddProductInCart() throws Throwable {
		productDetailsScreen.addToCart();
		Properties.setVariable("productName",productDetailsScreen.getProductName());
	}

	@Then("^I should see following products listed on partner screen$")
	public void iShouldSeeFollowingProductsListedOnPartnerScreen() throws Throwable {
		Assert.assertTrue(productImage.waitFor(15).isDisplayed(),"Product Image is not displayed");
		Assert.assertTrue(productName.waitFor(10).isDisplayed(),"Product Name is not displayed");
	}

	@Then("^I should see nearby stores$")
	public void iShouldSeeNearbyStores() throws Throwable {
		Assert.assertTrue(UIElement.byClass("XCUIElementTypeCell").waitFor(25).isDisplayed(),"Stores are not displayed in selected location");
	}
}
