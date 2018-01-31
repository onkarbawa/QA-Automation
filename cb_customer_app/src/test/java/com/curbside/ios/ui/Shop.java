package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 14/07/17.
 */
public class Shop extends AbstractScreen{
    public Shop() {
        // TODO Auto-generated constructor stub
    }

    UIElement leadTime = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Delivery available')]");

    @Then("^I should see lead times to places as below$")
    public void iShouldSeeLeadTimesToPlacesAsBelow() throws Throwable {
        Assert.assertTrue(leadTime.waitFor(5).getText().contains("min") || leadTime.getText().contains("hr") ||
                leadTime.getText().contains("Delivery available"), "lead time is not displayed in retailer store");

    }

    @And("^I select '(.*)' from list$")
    public void iSelectFromList(String location) throws Throwable {
        try {
            footerTabsScreen.btnShop.waitFor(5).tap();
            homeScreen.lnkCurrentLocation.waitFor(5);
            if (homeScreen.lnkCurrentLocation.getText().equals(location))
                return;

            homeScreen.lnkCurrentLocation.tap();
            UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" +
                    location + "']").waitFor(8).tap();
        } catch (Exception e) {
            UIElement.byName("Done").tap();
            homeScreen.iAmOnLocationStoresScreen(location);
        }
    }
}
