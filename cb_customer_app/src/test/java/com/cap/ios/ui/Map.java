package com.cap.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar
 */
public class Map {

    UIElement store = UIElement.byXpath("//XCUIElementTypeMap/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther");

    @Then("^I should see '(.*)' store on Map$")
    public void iShouldSeeStoreOnMap(String location) throws Throwable {
        Assert.assertEquals(store.waitFor(15).getText(),location,"Store is not displayed on the Map screen");
    }
}
