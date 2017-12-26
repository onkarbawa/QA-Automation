package com.arriveconsole.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

/**
 * Created by bawa.onkar.
 */
public class SiteSelection {
    UIElement newSiteSelection = UIElement.byXpath("//XCUIElementTypeCell/XCUIElementTypeStaticText[1]");

    @And("^I select a different site from list$")
    public void iSelectADifferentSiteFromList() throws Throwable {
        int totalSites = newSiteSelection.getCount();
        if (totalSites > 1) {
            List<WebElement> list = newSiteSelection.getElements();
            for (WebElement element : list) {
                if (!Objects.equals(element.getText(), Properties.getVariable("selectedSite"))) {
                    element.click();
                    MobileDevice.getScreenshot(true);
                    break;
                }
            }
        }
    }
}
