package com.curbside.ios.ui;

import com.curbside.automation.devicefactory.DeviceStore;
import com.curbside.automation.uifactory.AppleDevice;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 05/07/17.
 */
public class Settings {

    static UIElement settings =  new UIElement(By.name("Settings"));

    @Given("^'Location' preference is set as '(.*)' for '(.*)' app$")
    public void locationPreferenceIsSetAsForApp(String value, String appName) throws Throwable {
        MobileDevice.setLocationPreference(appName, value);
    }

    @Then("^I should see Location Services Disabled screen$")
    public void iShouldSeeLocationServicesDisabledScreen() throws Throwable {
       settings.isDisplayed();
    }

    @And("^I set '(.*)' as '(.*)'$")
    public void iSetAs(String appName, String newValue) throws Throwable {
        new UIElement(By.name("Location")).waitForElement(25);
        try {
            new UIElement(By.name("Location")).tap();
            new UIElement(By.name("newValue")).waitForElement(5);
            new UIElement(By.name(newValue)).tap();
        } catch (Exception e) {
        }
    }

}
