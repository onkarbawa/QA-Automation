package com.arriveconsole.ios.ui;

import com.curbside.automation.common.configuration.Properties;
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
        final int[] selectedElement = {0};
        if (totalSites > 1) {
            List<WebElement> siteList = newSiteSelection.getElements();
            siteList.stream().forEach((WebElement element) -> {
                if (Objects.equals(element.getText(), Properties.getVariable("selectedSite"))) {
                    selectedElement[0] = siteList.indexOf(element) + 1;
                    return;
                }
            });
            int selectElement = selectedElement[0];
            if (selectedElement[0] == totalSites) {
                UIElement.byXpath("//XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]").tap();
            } else {
                UIElement.byXpath("//XCUIElementTypeCell[" + (selectElement + 1) + "]/XCUIElementTypeStaticText[1]").tap();
            }
        } else {
            //To Do Later
        }
    }
}
