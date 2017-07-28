package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
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

    @And("^I select '(.*)' retailer and search for '(.*)'$")
    public void iSelectRetailerAndSearchFor(String storeName, String product) throws Throwable {
        footerTabsScreen.tapShop();
        UIElement.byXpath("//XCUIElementTypeOther[XCUIElementTypeStaticText[contains(@name,'Nearby Stores')]]/following-sibling::XCUIElementTypeCell[contains(@name,'" + storeName +"')]").waitFor(25).tap();
        searchBar.waitFor(10);
        searchBar.sendKeys(product,false);
        UIElement.byName("Search").tap();
    }

    @And("^I select (\\d+)no product from list$")
    public void iSelectNoProductFromList(int number) throws Throwable {
        UIElement.byXpath("//XCUIElementTypeCollectionView//XCUIElementTypeOther[XCUIElementTypeButton[contains(@name,'View All')]][1]/following-sibling::XCUIElementTypeCell[1]//XCUIElementTypeCollectionView//XCUIElementTypeCell[" + number + "]").waitFor(10).tap();
        Properties.setVariable("product"+Integer.toString(number),productDetailsScreen.getProductPrice());
    }
    @And("^I select '(.*)' retailer$")
    public void iSelectRetailer(String storeName) throws Throwable {
        footerTabsScreen.tapShop();
        UIElement.byXpath("//XCUIElementTypeOther[XCUIElementTypeStaticText[contains(@name,'Nearby Stores')]]/following-sibling::XCUIElementTypeCell[contains(@name,'" + storeName +"')]").waitFor(25).scrollTo(SwipeDirection.UP).tap();
    }

    @And("^I select '(.*)' product from list$")
    public void iSelectProductFromList(String product) throws Throwable {
        UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'"+product+"')]").waitFor(20).scrollTo(SwipeDirection.UP).tap();
    }
}
