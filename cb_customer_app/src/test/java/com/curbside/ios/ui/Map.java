package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * @author kumar.anil
 */

public class Map extends AbstractScreen {
    UIElement nearByStore = UIElement.byXpath("//XCUIElementTypeWindow[1]//XCUIElementTypeOther[XCUIElementTypeMap]//following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[1]");
    UIElement nearByStores = UIElement.byXpath("//XCUIElementTypeOther[contains(@name,'CVS')]");

    public Map() {
        // TODO Auto-generated constructor stub
    }

    @Then("^I should see stores on map for '(.*)'$")
    public void iShouldSeeStoresOnMapForPaloAlto(String location) throws Throwable {
        nearByStores.waitFor(25);
        if (!(nearByStores.getCount() > 0))
            Assert.fail("CVS stores are not displayed in the map");
//		Assert.assertTrue(nearByStore.isDisplayed(),"store is not displayed near to the selected location");
//		Assert.assertTrue(UIElement.byName("Palo Alto").waitFor(25).isDisplayed(), "Map is not displaying location " + location);
//		Assert.assertTrue(UIElement.byName("CVS, El Camino Real, Palo Alto").waitFor(25).isDisplayed(), "Map is not displaying store for " + location);
    }
}
