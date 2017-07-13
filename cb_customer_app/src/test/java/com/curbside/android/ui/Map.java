package com.curbside.android.ui;

import com.curbside.MockLocations;
import com.curbside.automation.uifactory.MobileDevice;

import cucumber.api.java.en.And;

/**
 * @author hitesh.grover
 *
 */

public class Map {
	@And("^I am currently in '(.*)' city$")
	public void i_set_mock_location(String city) throws Throwable {
		switch (city) {
		case "Palo Alto, CA":
			MobileDevice.setGeoLocation(MockLocations.palo_alto[0], MockLocations.palo_alto[1], MockLocations.palo_alto[2]);
			break;
		default:
			break;
		}
	}
}
