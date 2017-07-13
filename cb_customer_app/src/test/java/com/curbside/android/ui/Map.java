package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.Then;
import org.testng.Assert;

/**
 * @author hitesh.grover
 *
 */

public class Map {

  UIElement storeLocation = UIElement.byUISelector("new UiSelector().description(\"Google Map\").childSelector(new UiSelector().className(\"android.view.View\")).instance(0)");

  @Then("^I should see stores on map for '(.*)' location$")
  public void iShouldSeeStoresOnMapFor(String location) throws Throwable {
    Assert.assertTrue(storeLocation.waitFor(20).isDisplayed(), "Stores are not visible on map");
  }

}
