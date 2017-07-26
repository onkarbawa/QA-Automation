package com.curbside.android.ui;

import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.uifactory.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * @author hitesh.grover
 * Page that appears after skipping into or clicking Get Started
 */

public class Home extends AbstractScreen {
	
	static UIElement nearBy= new UIElement(By.name("Near"));
	UIElement currentLocation = UIElement.byId("com.curbside.nCurbside:id/button_location");
	UIElement shopNearLabel = UIElement.byUISelector("new UiSelector().text(\"Nearby Stores\")");
	UIElement myAccount =  UIElement.byId("com.curbside.nCurbside:id/bb_bottom_bar_icon");
	UIElement sorryMessage = UIElement.byXpath("//*[@text='Sorry, we’re not in that area yet.']");
	UIElement searchIcon = UIElement.byId("com.curbside.nCurbside:id/action_search");
	UIElement searchBox = UIElement.byId("com.curbside.nCurbside:id/search_src_text");
	UIElement searchBackButton = UIElement.byId("com.curbside.nCurbside:id/img_tool_back");
	UIElement cityZipSearchTextBox = UIElement.byId("com.curbside.nCurbside:id/edit_location");


	UIElement apiHost = UIElement.byXpath("//*[@resource-id='android:id/list']/android.widget.LinearLayout[1]");
	UIElement apiHostTextField = UIElement.byId("android:id/edit");
	UIElement apiHostOkButton = UIElement.byUISelector("new UiSelector().text(\"OK\")");
	UIElement debugBackButton = UIElement.byAccessibilityId("Navigate up");
	UIElement imageBackButton= UIElement.byId("com.curbside.nCurbside:id/img_tool_back");
	UIElement myLocationButton = UIElement.byAccessibilityId("My Location");
	UIElement firstRetailerPartner = UIElement.byXpath("//*[@resource-id='com.curbside.nCurbside:id/grid_view']/android.widget.RelativeLayout[@index='0']");
	UIElement firstRetailerPartnerListView = UIElement.byXpath("//android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[@index='0']");
	UIElement noStoresInAreaText = UIElement.byId("com.curbside.nCurbside:id/textView1");
	UIElement westFieldStore = UIElement.byXpath("//*[@resource-id='com.curbside.nCurbside:id/container_relative_layout_card_Store_icon' and @index='2']");
	
	UIElement progressBar =  UIElement.byId("com.curbside.nCurbside:id/progress_bar");

	@Then("^I should see 'Nearby stores' landing page$")
	public void isDisplayed() throws Throwable {
		progressBar.waitForNot(60);
		
		//Assert.assertTrue(shopNearLabel.isDisplayed() || sorryMessage.isDisplayed(),
				Assert.assertTrue(shopNearLabel.waitFor(20).isDisplayed() || sorryMessage.isDisplayed(), "Near by stores page is not visible");
	}

	@And("^I tap on My Account icon$")
	public void iTapOnMyAccountIcon() throws Throwable {
		myAccount.waitFor(5).tap();
	}

	@And("^I am on Home Screen$")
	public void open() throws Throwable {
		welcomeScreen.wait_for_app_launch();
		try {
			for (int i = 0; i < 10; i++) {
				if(!footerTabsScreen.btnShop.isDisplayed())
				{
					welcomeScreen.skipIntro.tapOptional();
					welcomeScreen.btnGetStarted.tapOptional();
					welcomeScreen.okButton.tapOptional();
					commonSteps.acceptLocationAlert();
				}
			}
		} catch (Exception e) {
		}
	}

	@And("^I have selected test environment$")
	public void iHaveSelectedTestEnvironment() throws Throwable {
		String envSearchKey= "_#csndc#ena";
		String envAPIKey= "https://api-s.shopcurbside.com";
		homeScreen.open();
		
		if(DriverFactory.getEnvironment().equalsIgnoreCase(envAPIKey))
			return;

		searchIcon.waitFor(5).tap();
		searchBox.waitFor(5).sendKeys(envSearchKey, false);
		AndroidDevice.pressEnter();
		
		try {
			apiHost.waitFor(5).tap();
			apiHostTextField.setText(envAPIKey, false);
		} catch (Exception e) {
			debugBackButton.tap();
			apiHost.tap();
			apiHostTextField.setText(envAPIKey, false);
		}
		
		MobileDevice.getScreenshot(true);
		
		apiHostOkButton.tap();
		debugBackButton.tap();
		imageBackButton.waitFor(5).tap();
		DriverFactory.closeApp();
		DriverFactory.launchApp();
		welcomeScreen.wait_for_app_launch();
		homeScreen.open();

		DriverFactory.setEnvironment(envAPIKey);
	}

	@Given("I select 1st retailer partner on stores screen")
	public void select1stRetailerPartner() throws Throwable {
		if (firstRetailerPartner.waitFor(10).isDisplayed()) {
			firstRetailerPartner.tap();
			firstRetailerPartner.waitForNot(30);
		} else {
			firstRetailerPartnerListView.waitFor(10).tap();
			firstRetailerPartnerListView.waitForNot(30);
		}
	}

	@Given("I search for '(.*)' location")
	public void searchForLocation(String cityName) throws Throwable {
		footerTabsScreen.tapShop();
		if(currentLocation.getText().equals(cityName))
			return;
		
		try {
            currentLocation.waitFor(10).tap();
        }catch (Exception e){}

        if(searchBox.isDisplayed()){
            searchBackButton.tap();
            currentLocation.tap();
        }

		cityZipSearchTextBox.waitFor(10).sendKeys(cityName, false);
		AndroidDevice.pressEnter();
		currentLocation.waitFor(30);
	}

	@Given("I add any product to cart in '(.*)' location")
	public void i_add_any_product_in_location(String location) throws Throwable {
		Thread.sleep(1000);
		footerTabsScreen.tapShop();
		searchForLocation(location);
		select1stRetailerPartner();
		storeDetailsScreen.select1stProduct();
		productDetailsScreen.addToCart();
		MobileDevice.getScreenshot(true);
		AndroidDevice.goBack();
	}

	@Then("I should see nearby stores to current location")
    public void nearbyStoresCurrentLocation() throws Throwable {

	    Assert.assertFalse(noStoresInAreaText.isDisplayed(), "Curbside services are not in this area");
	    try{
            firstRetailerPartner.waitFor(5);
            firstRetailerPartnerListView.waitFor(5);
        }catch (Exception e){}

		Assert.assertTrue(firstRetailerPartnerListView.isDisplayed() || firstRetailerPartner.isDisplayed(),
			"Android : Not able to load the stores");

    }

	@And("^I am on '(.*)' location 'Stores' Screen$")
	public void iAmOnLocationStoresScreen(String location) throws Throwable {
		searchForLocation(location);
	}

    @And("^I select a store$")
    public void iSelectAStore() throws Throwable {
        select1stRetailerPartner();
    }

	@When("^I tap on retailer on Near by stores screen$")
	public void iTapOnRetailerOnNearByStoresScreen() throws Throwable {
		select1stRetailerPartner();
	}
}
