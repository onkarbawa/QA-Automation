package com.cap.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by bawa.onkar on 28/07/17.
 */
public class GetMyOrder extends AbstractScreen {
    UIElement deliveryBy = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Delivery by ￼')]");
    UIElement addNewAddressButton = UIElement.byXpath("//XCUIElementTypeStaticText[@name='New Address']");
    UIElement addressSearchField = UIElement.byXpath("//XCUIElementTypeSearchField[@name='Search']");
    UIElement savedAddress = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell");
    UIElement addressSuggestions = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeCell");

    @And("^I select the delivery address as:\"([^\"]*)\"$")
    public void iSelectTheDeliveryAddressAsStreetCityState(String address) throws Throwable {
        int totalAddress = savedAddress.waitFor(5).getCount();
        if (totalAddress >1){
            savedAddress.tap();
        }
        else {
            addNewAddressButton.tap();
            addressSearchField.waitFor(5).sendKeys(address, false);

            addressSuggestions.waitFor(5).tap();

        }
        deliveryBy.waitFor(15);
    }
}
