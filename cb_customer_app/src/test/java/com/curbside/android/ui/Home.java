package com.curbside.android.ui;

import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.uifactory.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
	UIElement apiHostOkButton = UIElement.byId("android:id/button1");
	UIElement debugBackButton = UIElement.byAccessibilityId("Navigate up");
	UIElement myLocationButton = UIElement.byAccessibilityId("My Location");
	UIElement firstRetailerPartner = UIElement.byXpath("//*[@resource-id='com.curbside.nCurbside:id/grid_view']/android.widget.RelativeLayout[@index='0']");
	UIElement firstRetailerPartnerListView = UIElement.byXpath("//android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[@index='0']");
	UIElement noStoresInAreaText = UIElement.byId("com.curbside.nCurbside:id/textView1");


	@Then("^I should see 'Nearby stores' landing page$")
	public void isDisplayed() throws Throwable
	{	try {
				Assert.assertTrue(shopNearLabel.isDisplayed() || sorryMessage.isDisplayed());
			} finally {
				MobileDevice.getScreenshot(true);
			}
	}

	@And("^I tap on My Account icon$")
	public void iTapOnMyAccountIcon() throws Throwable {
		myAccount.waitFor(5);
		myAccount.tap();
	}

	@And("^I am on Home Screen$")
	public void iAmOnHomeScreen() throws Throwable {
		welcomeScreen.skipIntro.waitFor(5);
		welcomeScreen.skipIntro.tap();
		welcomeScreen.okButton.waitFor(5);
		welcomeScreen.okButton.tap();
		commonSteps.acceptLocationAlert();
		commonSteps.acceptLocationAlert();
	}

	@And("^I have selected test environment$")
	public void iHaveSelectedTestEnvironment() throws Throwable {
		searchIcon.waitFor(5);
		searchIcon.tap();
		searchBox.waitFor(5);
		searchBox.sendKeys("_#csndc#ena");
		AndroidDevice.pressEnter();
		
		apiHost.waitFor(3);
		if (myLocationButton.isDisplayed()) {
			debugBackButton.tap();
			apiHost.waitFor(3);
		}
		apiHost.tap();

		apiHostTextField.waitFor(3);
		apiHostTextField.clearText();
		apiHostTextField.sendKeys("https://api-s.shopcurbside.com");
		apiHostOkButton.tap();
		debugBackButton.waitFor(2);
		debugBackButton.tap();
        AndroidDevice.startApplication(DeviceStore.getDevice().get("appPackage").toString(), 
        		DeviceStore.getDevice().get("appActivity").toString());
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
		currentLocation.waitFor(10).tap();
		cityZipSearchTextBox.waitFor(10).sendKeys(cityName);
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

	@When("^I tap on retailer on Near by stores screen$")
	public void iTapOnRetailerOnNearByStoresScreen() throws Throwable {
		select1stRetailerPartner();
	}
}
