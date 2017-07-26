package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 14/07/17.
 */
public class Shop {
    public Shop(){
        // TODO Auto-generated constructor stub
    }
    UIElement leadTime = UIElement.byXpath("//XCUIElementTypeStaticText[contains(@name,'hr')]");

    @Then("^I should see lead times to places as below$")
    public void iShouldSeeLeadTimesToPlacesAsBelow() throws Throwable {
     //   System.out.println(leadTime.getText());
        Assert.assertTrue(leadTime.getText().contains("hr"),"lead time is not displayed in retailer store");
    }
}
