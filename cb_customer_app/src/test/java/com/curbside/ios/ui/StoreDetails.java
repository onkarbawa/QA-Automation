package com.curbside.ios.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.SwipeDirection;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

import static com.curbside.ios.ui.AbstractScreen.footerTabsScreen;
import static com.curbside.ios.ui.AbstractScreen.productDetailsScreen;

/**
 * Created by bawa.onkar on 20/07/17.
 */
public class StoreDetails {

    UIElement searchBar = UIElement.byXpath("//XCUIElementTypeSearchField[contains(@name,'Search')]");
    UIElement mockPickingStore = UIElement.byName("Mock Picking no Training");

    @And("^I select '(.*)' retailer and search for '(.*)'$")
    public void iSelectRetailerAndSearchFor(String storeName, String product) throws Throwable {
        footerTabsScreen.btnShop.tap();
        UIElement.byXpath("//XCUIElementTypeCell[contains(@name,'" + storeName + "')]").waitFor(30).
                scrollTo(SwipeDirection.UP).tap();
        MobileDevice.getSource(true);
        if (storeName.contains("Mock")) {
            UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'Sheridan Ave')]").tap();
            if (mockPickingStore.waitFor(3).isDisplayed()) {
                mockPickingStore.tap();
                Reporter.addStepLog("Selecting Mock Picking Store");
            } else {
                Steps.tapButton("Cancel");
            }
        }
        searchBar.waitFor(10);
        searchBar.sendKeys(product, false);
        UIElement.byName("Search").tap();
        MobileDevice.getSource(true);
    }

    @And("^I select (\\d+)no product from list$")
    public void iSelectNoProductFromList(int number) throws Throwable {
        UIElement element = UIElement.byXpath("//XCUIElementTypeCollectionView//XCUIElementTypeOther[" +
                "XCUIElementTypeButton[contains(@name,'View All')]][1]/following-sibling::XCUIElementTypeCell[1]" +
                "//XCUIElementTypeCollectionView//XCUIElementTypeCell[" + number + "] | " +
                        "//XCUIElementTypeCell[XCUIElementTypeButton[contains(@name,'Departments')]]/following-sibling" +
                "::XCUIElementTypeCell[1]/XCUIElementTypeCollectionView/XCUIElementTypeCell[" + number + "]");
        element.waitFor(10).tap();
        productDetailsScreen.productLocationAndPrice.waitFor(3);
        Properties.setVariable("product"+Integer.toString(number),productDetailsScreen.getProductPrice());
        Properties.setVariable("productName"+Integer.toString(number),productDetailsScreen.productName.getText());
    }

    @And("^I select '(.*)' retailer$")
    public void iSelectRetailer(String storeName) throws Throwable {
        footerTabsScreen.tapShop();
        footerTabsScreen.tapShop();
        MobileDevice.getScreenshot(true);
        UIElement.byXpath("//XCUIElementTypeCell[contains(@name,'" + storeName + "')]").waitFor(25).scrollTo(SwipeDirection.UP).tap();
        MobileDevice.getScreenshot(true);
        if (storeName.contains("Mock")) {
            UIElement.byXpath("//XCUIElementTypeButton[contains(@name,'Sheridan Ave')]").tap();
            if (mockPickingStore.waitFor(3).isDisplayed()) {
                mockPickingStore.tap();
                Reporter.addStepLog("Selecting Mock Picking Store");
            } else {
                Steps.tapButton("Cancel");
            }
        }
    }

    @And("^I select '(.*)' product from list$")
    public void iSelectProductFromList(String product) throws Throwable {
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + product + "')]").waitFor(20).scrollTo(SwipeDirection.UP).tap();
    }

    @And("^I select (\\d+)no product from '(.*)' store$")
    public void iSelectNoProductFromCVSStore(int number, String store) throws Throwable {
       UIElement.byXpath("//XCUIElementTypeCell[XCUIElementTypeOther[XCUIElementTypeButton[@name='Refine']]]/following-sibling::XCUIElementTypeCell["+number+"]//XCUIElementTypeImage").waitFor(20).scrollTo().tap();
    }

    @And("^I select (\\d+) product '(.*)' from list$")
    public void iSelectProductRefrigeratedFoodFromList(int number, String product) throws Throwable {
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'" + product + "')]").waitFor(20).scrollTo(SwipeDirection.UP).tap();
        productDetailsScreen.productLocationAndPrice.waitFor(3);
        Properties.setVariable("product" + Integer.toString(number), productDetailsScreen.getProductPrice());
        Properties.setVariable("productName" + Integer.toString(number), productDetailsScreen.productName.getText());
    }
    @And("^I select (\\d+)no product and save product price named as'(.*)'$")
    public void iSelectNoProductFromStoreProductList(int number, String priceName) throws Throwable {
        UIElement element = UIElement.byXpath("//XCUIElementTypeCollectionView//XCUIElementTypeOther[" +
                "XCUIElementTypeButton[contains(@name,'View All')]][1]/following-sibling::XCUIElementTypeCell[1]" +
                "//XCUIElementTypeCollectionView//XCUIElementTypeCell[" + number + "] | " +
                "//XCUIElementTypeCell[XCUIElementTypeButton[contains(@name,'Departments')]]/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeCollectionView/XCUIElementTypeCell[" + number + "]");
        element.waitFor(10).tap();
        productDetailsScreen.productLocationAndPrice.waitFor(3);
        Properties.setVariable(priceName + number, productDetailsScreen.getProductPrice());
        System.out.println("name is :" + priceName + number);
        System.out.println("price is :" + productDetailsScreen.getProductPrice());
    }
}
