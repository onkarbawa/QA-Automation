package com.arriveconsole.android.ui;

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
public class SiteSelection extends AbstractScreen {

    UIElement siteSelection = UIElement.byXpath("//android.widget.LinearLayout/android.widget.TextView[1]");

    @And("^I select a different site from list$")
    public void iSelectADifferentSiteFromList() throws Throwable {
        int totalSites = siteSelection.getCount();
        MobileDevice.getScreenshot(true);

        final int[] selectedElement = {0};
        if (totalSites > 1) {
            List<WebElement> siteList = siteSelection.getElements();
            siteList.stream().forEach((WebElement element) -> {
                if (Objects.equals(element.getText(), Properties.getVariable("selectedSite"))) {
                    selectedElement[0] = siteList.indexOf(element) + 1;
                    return;
                }
            });
            int selectElement = selectedElement[0];
            if (selectedElement[0] == totalSites) {
                UIElement.byXpath("//android.support.v7.widget.RecyclerView[@resource-id='com.curbside.arriveconsole:" +
                        "id/rvSites']/android.widget.LinearLayout[1]").tap();
            } else {
                UIElement.byXpath("//android.support.v7.widget.RecyclerView[@resource-id='com.curbside.arriveconsole:" +
                        "id/rvSites']/android.widget.LinearLayout[" + (selectElement + 1) + "]").tap();
            }
        } else {
            //To Do Later
        }
    }
}

