package com.curbside.android.ui;

import com.curbside.MockLocations;
import com.curbside.automation.uifactory.MobileDevice;

import cucumber.api.java.en.And;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * @author hitesh.grover
 *
 */

public class Map {
	UIElement storeLocation = UIElement.byUISelector("new UiSelector().description(\"Google Map\").childSelector(new UiSelector().className(\"android.view.View\")).instance(0)");

	@And("^I am currently in '(.*)' city$")
	public void i_set_mock_location(String city) throws Throwable {
		switch (city) {
		case "Palo Alto, CA":
			MobileDevice.setGeoLocation(MockLocations.palo_alto[0], MockLocations.palo_alto[1], MockLocations.palo_alto[2]);
			break;
		default:
			new IllegalArgumentException("Not implemented for city: " + city);
			break;
		}
	}

  
  @Then("^I should see stores on map for '(.*)' location$")
  public void iShouldSeeStoresOnMapFor(String location) throws Throwable {
    Assert.assertTrue(storeLocation.waitFor(25).isDisplayed(), "Stores are not visible on map");
  }
}
