package com.curbside.ios.ui;

import com.curbside.automation.uifactory.AppleDevice;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 05/07/17.
 */
public class Settings extends AbstractScreen {

    UIElement settings = new UIElement(By.name("Settings"));
    UIElement location = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Location']"));
    UIElement backgroundAppRefresh = UIElement.byXpath("//XCUIElementTypeSwitch[@name='" + "Background App Refresh" + "']");
    UIElement allowNotifications = UIElement.byXpath("//XCUIElementTypeSwitch[@name='" + "Allow Notifications" + "']");
    UIElement locationAlwaysDescription = UIElement.byXpath("//XCUIElementTypeTable//XCUIElementTypeOther[1][contains(@name,'Access to your location')]");

    public Settings() {
        // TODO Auto-generated constructor stub
    }

    @Given("^'Location' preference is set as '(.*)' for '(.*)' app$")
    public void locationPreferenceIsSetAsForApp(String value, String appName) throws Throwable {
        try {
            welcomeScreen.btnAllow.tapOptional();
        } catch (Exception e) {
        }
        MobileDevice.setLocationPreference(appName, value);
    }

    @Then("^I should see Location as '(.*)'$")
    public void iShouldSeeLocationAs(String currentLocation) throws Throwable {

        Assert.assertTrue(UIElement.byXpath("//XCUIElementTypeButton[@name='" + currentLocation + "']").isDisplayed(),
                "Mentioned location is not displayed");

        //settings.isDisplayed();
        MobileDevice.getScreenshot(true);
    }

    @And("^I set '(.*)' permission as '(.*)'$")
    public void iSetAs(String appName, String newValue) throws Throwable {
        MobileDevice.getSource(true);
        MobileDevice.getScreenshot(true);
        //location.waitFor(20);
        try {
            location.tap();
        } catch (Exception e) {
            MobileDevice.getSource(true);
            MobileDevice.getScreenshot(true);
        }
        new UIElement(By.name(newValue)).tap();
        MobileDevice.getScreenshot(true);
    }

    @When("^I turn '(.*)' '(.*)' for '(.*)'$")
    public void iTurnFor(String ONorOFF, String button, String appName) throws Throwable {
        UIElement toggleButton = UIElement.byXpath("//XCUIElementTypeSwitch[@name='" + button + "']");

        String currentButtonValue = toggleButton.getAttribute("value");
        System.out.println("Current toggle value is " + currentButtonValue);

        currentButtonValue = currentButtonValue.equals("true") ? "ON" : "OFF";

        if (!ONorOFF.equalsIgnoreCase(currentButtonValue))
            toggleButton.tap();

        MobileDevice.getScreenshot(true);
    }

    @And("^I verify that Location '(.*)' is set$")
    public void iVerifyThatLocationNeverIsSet(String permission) throws Throwable {
        Thread.sleep(3000);
        commonSteps.iTapOnBackButton();
        Thread.sleep(3000);
        Steps.tapButton("Location");
        switch (permission.toLowerCase()) {
            case "never":
                if (locationAlwaysDescription.isDisplayed()) {
                    Steps.tapButton("Never");
                }
                break;
            case "always":
                if (!locationAlwaysDescription.isDisplayed()) {
                    Steps.tapButton("Always");
                }
                break;
        }
        MobileDevice.getScreenshot(true);
    }

    @And("^I verify that Background App Refresh is '(.*)'$")
    public void iVerifyThatBackgroundAppRefreshIsOFF(String button) throws Throwable {

        String currentBackgroundRefreshValue = backgroundAppRefresh.getAttribute("value");
        System.out.println("Current background refresh is " + currentBackgroundRefreshValue);

        switch (button.toLowerCase()){
            case "off":
                if (currentBackgroundRefreshValue.equalsIgnoreCase("1"))
                    backgroundAppRefresh.tap();
                break;
            case "on":
                if (currentBackgroundRefreshValue.equalsIgnoreCase("0"))
                    backgroundAppRefresh.tap();
                break;
        }

        Steps.tapButton("Location");
        if (!locationAlwaysDescription.isDisplayed()) {
            Steps.tapButton("Always");
        }
    }
}
