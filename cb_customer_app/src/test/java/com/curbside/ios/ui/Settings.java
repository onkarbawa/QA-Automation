package com.curbside.ios.ui;

import com.curbside.automation.uifactory.AppleDevice;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 05/07/17.
 */
public class Settings {

    static UIElement alwaysEnabled =  new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Always']"));
    static UIElement settings =  new UIElement(By.name("Settings"));



//    @And("^'Location' preference should be set as 'Always' for 'Curbside' app$")
//    public void locationPreferenceShouldBeSetAsAlwaysForCurbsideApp() throws Throwable {
//        setLocationPreference();
//        Assert.assertTrue(alwaysEnabled.isDisplayed(),"Checkmark of Always is not enabled");
//    }

    public static void setLocationPreference() throws Throwable {
            AppleDevice.launchSettings();

            new UIElement(By.xpath("//XCUIElementTypeCell[@name='Curbside']")).scrollTo().tap();
            try {
                new UIElement(By.name("Location")).tap();
            } catch (Exception e) {
            }
    }

    @Given("^'Location' preference is set as '(.*)' for '(.*)' app$")
    public void locationPreferenceIsSetAsForApp(String value, String appName) throws Throwable {
        MobileDevice.setLocationPreference(appName, value);
    }

    @Then("^I should see Location Services Disabled screen$")
    public void iShouldSeeLocationServicesDisabledScreen() throws Throwable {
       settings.isDisplayed();
    }

    public static void setBackgroundAppRefresh() throws Throwable {
        AppleDevice.launchSettings();

        new UIElement(By.xpath("//XCUIElementTypeCell[@name='Curbside']")).scrollTo().tap();
        try {
            new UIElement(By.xpath("//XCUIElementTypeSwitch[@name='Background App Refresh']")).tap();
        } catch (Exception e) {
        }
    }
}
