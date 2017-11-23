package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * @author kumar.anil Page that appears after skipping into or clicking Get
 * Started
 */

public class Home extends AbstractScreen {

    UIElement nearBy = UIElement.byXpath("//XCUIElementTypeOther//XCUIElementTypeStaticText[@name='Near '] " +
            "| //XCUIElementTypeCell/../XCUIElementTypeOther[5]//XCUIElementTypeStaticText[@name='Near ']");
    UIElement btnCancel = UIElement.byName("Cancel");
    UIElement iconSearch = UIElement.byName("Search");
    UIElement btnSearchKeyboard = UIElement.byXpath("//XCUIElementTypeKeyboard//XCUIElementTypeButton[@name='Search']");
    UIElement txtProductSearch = UIElement.byClass("UISearchBarTextField");
    UIElement txtSearchNearBy = UIElement.byClass("XCUIElementTypeSearchField");

    UIElement lnkCurrentLocation = UIElement.byXpath("//XCUIElementTypeStaticText[@label='Near ']/following-sibling::XCUIElementTypeButton" +
            "| //XCUIElementTypeCell/../XCUIElementTypeOther[5]//XCUIElementTypeStaticText[@name='Near ']/following-sibling::XCUIElementTypeButton");
    UIElement cityZipSearchTextBox = UIElement.byAccessibilityId("City, Zip or Address");

    UIElement productImage = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Popular']/parent::XCUIElementTypeOther" +
            "/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeImage | " +
            "//XCUIElementTypeCell[XCUIElementTypeButton[@name='Departments ￼']]/following-sibling::XCUIElementTypeCell[1]" +
            "//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[2]");
    UIElement productName = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Popular']/parent::XCUIElementTypeOther/" +
            "following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeStaticText" +
            " | //XCUIElementTypeCell[XCUIElementTypeButton[@name='Departments ￼']]/following-sibling::XCUIElementTypeCell[1]" +
            "//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[2]");
    UIElement recentLocation = UIElement.byXpath("//XCUIElementTypeOther[XCUIElementTypeStaticText[contains(@name,'Recent Locations')]]/following-sibling::XCUIElementTypeCell[XCUIElementTypeStaticText[contains(@name,'Palo Alto')]]");
    UIElement checkEnvironment = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell[8]//XCUIElementTypeStaticText");

    Steps steps = new Steps();

    public Home() {
        // TODO Auto-generated constructor stub
    }

    @Then("^I should see 'Nearby stores' landing page$")
    public void isDisplayed() throws Throwable {
        try {
            System.out.println("Checking nearby---");
            //    Assert.assertTrue(nearBy.isDisplayed());
            Assert.assertEquals(nearBy.getText(), "Near ");
        } finally {
            MobileDevice.getScreenshot(true);
        }
    }

    @And("^I am on Home Screen$")
    public void open() throws Throwable {
        welcomeScreen.wait_for_app_launch();
        try {
            for (int i = 0; i < 3; i++) {

                if (homeScreen.iconSearch.isDisplayed())
                    return;

                if (welcomeScreen.skipIntro.isDisplayed() || welcomeScreen.btnGetStarted.isDisplayed()
                        || welcomeScreen.okWithMe.isDisplayed() || welcomeScreen.btnAllow.isDisplayed()) {
                    welcomeScreen.btnAllow.tapOptional();
                    welcomeScreen.skipIntro.tapOptional();
                    welcomeScreen.btnGetStarted.tapOptional();
                    welcomeScreen.okWithMe.tapOptional();
                    commonSteps.acceptNotificationAlert();
                    commonSteps.acceptLocationAlert();
                } else
                    return;
            }
        } catch (Exception e) {
            System.out.println("inCAtchblock" + e.getMessage());
        }
    }

    @Given("I select '(.*)' > '(.*)' location")
    public void setLocation(String category, String cityName) throws Throwable {
        if (lnkCurrentLocation.getText().equals(cityName))
            return;

        lnkCurrentLocation.tap();
        UIElement.byAccessibilityId(category).tap();
        UIElement.byAccessibilityId(cityName).scrollTo().tap();
    }

    @Given("I search for '(.*)' location")
    public void searchForLocation(String cityName) throws Throwable {
        footerTabsScreen.tapShop();
        lnkCurrentLocation.waitFor(5);
        if (lnkCurrentLocation.getText().equals(cityName))
            return;

        lnkCurrentLocation.tap();
        cityZipSearchTextBox.sendKeys(cityName, false);
        try {
            UIElement.byAccessibilityId(cityName).waitFor(40).tap();
        } catch (Exception e) {
            UIElement.byXpath("//XCUIElementTypeTable[@name='Search results']//XCUIElementTypeStaticText[@name='" + cityName + "']").waitFor(20).tap();
        }

        loadingIcon.waitForNot(30);
        welcomeScreen.skipIntro.tapOptional();
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

        loadingIcon.waitForNot(30);


        MobileDevice.getScreenshot(true);
        DriverFactory.setEnvironment(envAPIKey);
        MobileDevice.getScreenshot(true);
        DriverFactory.closeApp();
        DriverFactory.launchApp();
        homeScreen.open();
    }

    @And("^I have selected Experimental test environment$")
    public void iHaveSelectedExperimentalTestEnvironment() throws Throwable {
        homeScreen.open();

        String envAPIKey = "_#csndc#env#s";
        String envSearchKey = "_#csndc#str#eon";
        if (DriverFactory.getEnvironment().equalsIgnoreCase(envAPIKey))
            return;

        iconSearch.tap();
        txtSearchNearBy.waitFor(5).sendKeys(envAPIKey, false);
        btnSearchKeyboard.tap();

        loadingIcon.waitForNot(30);

        iconSearch.tap();
        txtSearchNearBy.waitFor(5).sendKeys(envSearchKey, false);
        btnSearchKeyboard.tap();
        loadingIcon.waitForNot(30);

        MobileDevice.getScreenshot(true);
        DriverFactory.setEnvironment(envAPIKey);
        MobileDevice.getScreenshot(true);
        DriverFactory.closeApp();
        DriverFactory.launchApp();
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
        UIElement firstProduct = UIElement.byXpath("//XCUIElementTypeCollectionView//XCUIElementTypeImage | //XCUIElementTypeCell[XCUIElementTypeButton[@name='Departments ￼']]/" +
                "following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[2]");
        firstProduct.tap();
        productDetailsScreen.btnAddtoCart.waitFor(5);
    }

    @Given("I select ('.*') retailer and add any product to cart")
    public void i_select_retailer_and_add_product(String retailer) throws Throwable {
        footerTabsScreen.tapShop();
        select1stRetailerPartner();
        storeDetailsScreen.iSelectNoProductFromList(1);
        productDetailsScreen.iAddQuantityOfIt(1);
        MobileDevice.getScreenshot(true);
    }

    @Given("I add any product to cart in '(.*)' location")
    public void i_add_any_product_in_location(String location) throws Throwable {
        iAmOnLocationStoresScreen(location);
        storeDetailsScreen.iSelectRetailer("CVS");
        storeDetailsScreen.iSelectNoProductFromList(1);
        productDetailsScreen.iAddQuantityOfIt(1);
        MobileDevice.getScreenshot(true);
    }

    @And("^I am on Shop Screen$")
    public void iAmOnShopScreen() throws Throwable {
        Thread.sleep(2000);
        footerTabsScreen.btnShop.tap();
    }

    @And("^I am on '(.*)' location 'Stores' Screen$")
    public void iAmOnLocationStoresScreen(String location) throws Throwable {
        footerTabsScreen.tapShop();
        searchForLocation(location);
        MobileDevice.getScreenshot(true);
    }

    @And("^I select a store$")
    public void iSelectAStore() throws Throwable {
        try {
            footerTabsScreen.tapShop();
        } catch (Exception e) {
        }
        select1stRetailerPartner();
    }

    @When("^I tap on product from the list$")
    public void iTapOnProductFromTheList() throws Throwable {
        select1stProduct();
    }

    @And("^I add product in cart$")
    public void iAddProductInCart() throws Throwable {
        productDetailsScreen.addToCart();
        Properties.setVariable("productName", productDetailsScreen.getProductName());
    }

    @Then("^I should see following products listed on partner screen$")
    public void iShouldSeeFollowingProductsListedOnPartnerScreen() throws Throwable {
        Assert.assertTrue(productImage.waitFor(15).isDisplayed(), "Product Image is not displayed");
        Assert.assertTrue(productName.waitFor(10).isDisplayed(), "Product Name is not displayed");
    }

    @Then("^I should see nearby stores$")
    public void iShouldSeeNearbyStores() throws Throwable {
        Assert.assertTrue(UIElement.byClass("XCUIElementTypeCell").waitFor(25).isDisplayed(), "Stores are not displayed in selected location");
    }
}
