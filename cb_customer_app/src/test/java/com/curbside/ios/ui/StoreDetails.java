package com.curbside.ios.ui;

import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

/**
 * Created by bawa.onkar on 20/07/17.
 */
public class StoreDetails {

    UIElement searchBar = UIElement.byXpath("//XCUIElementTypeSearchField[@name='Search All Stores']");

    @And("^I select '(.*)' retailer and search for '(.*)'$")
    public void iSelectRetailerAndSearchFor(String storeName, String product) throws Throwable {
        UIElement.byXpath("//XCUIElementTypeCollectionView//XCUIElementTypeCell[contains(@name,'" + storeName +"')]").waitFor(15).tap();
        searchBar.waitFor(10);
        searchBar.sendKeys(product,false);
        UIElement.byName("Search").tap();
    }

    @And("^I select (\\d+)no product from list$")
    public void iSelectNoProductFromList(int number) throws Throwable {
        UIElement.byXpath("//XCUIElementTypeCollectionView//XCUIElementTypeCollectionView//XCUIElementTypeCell[" + number + "]").waitFor(10).tap();
    }
}
