package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * @author kumar.anil
 *
 */

public class Map extends AbstractScreen {
	UIElement nearByStore = UIElement.byXpath("//XCUIElementTypeWindow[1]//XCUIElementTypeOther[XCUIElementTypeMap]//following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[1]");
	public Map() {
		// TODO Auto-generated constructor stub
	}

	@Then("^I should see stores on map for 'Palo Alto'$")
	public void iShouldSeeStoresOnMapForPaloAlto() throws Throwable {
		Assert.assertTrue(nearByStore.isDisplayed(),"store is not displayed near to the selected location");
	}
}
