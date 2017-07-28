package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by bawa.onkar on 28/07/17.
 */
public class GetMyOrder extends AbstractScreen {
    UIElement deliveryWith = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'Delivery with ￼')]");
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
 //           homeScreen.btnSearchKeyboard.tap();

            addressSuggestions.waitFor(5).tap();
//            List<WebElement> list = addressSuggestions.getElements();
//            for (WebElement element : list) {
//                String streetName = element.findElement(By
//                        .xpath("//XCUIElementTypeCell//XCUIElementTypeStaticText[1]")).getText();
//                if (streetName.contains(street)) {
//                    String cityState = element.findElement(By
//                            .xpath("//XCUIElementTypeCell//XCUIElementTypeStaticText[2]")).getText();
//                    if (cityState.contains(city) && cityState.contains(state)) {
//                        element.click();
//                        break;
//                    }
//                }
//            }
        }
        deliveryWith.waitFor(15);
    }
}
