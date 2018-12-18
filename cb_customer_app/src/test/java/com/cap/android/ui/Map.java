package com.cap.android.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.testng.Assert;

/**
 * Created by hitesh.grover on 03/10/17.
 */
public class Map extends AbstractScreenCap {

    UIElement storeLocation = UIElement.byUISelector("new UiSelector().description(\"Google Map\").childSelector(new UiSelector().className(\"android.view.View\")).instance(0)");


    @And("^I should see stores location on the map$")
    public void iShouldSeeStores() throws Throwable {
        MobileDevice.getScreenshot(true);
        Assert.assertTrue(storeLocation.waitFor(20).isDisplayed(), "Stores are not visible on map");
    }

    @And("^I tap on Map button on visibility")
    public void iTapOnMap() throws Throwable {
        Steps.waitForButton("Map");
        Steps.waitForButton("Map");
        MobileDevice.getScreenshot(true);
        Steps.tapButton("Map");
    }
}
