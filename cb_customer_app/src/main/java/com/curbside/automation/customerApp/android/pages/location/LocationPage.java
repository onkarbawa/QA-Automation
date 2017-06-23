package com.curbside.automation.customerApp.android.pages.location;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by hitesh.grover on 23/06/17.
 */
public class LocationPage extends Page {


    public final By currentLocationTextField = By.id(LocationPageUIMap.CURRENT_LOCATION_TEXT_FIELD);

    public LocationPage(AppiumDriver driver) {
        super(driver);
    }


    /**
     * Gets Current Location Button element
     * @return
     */
    public WebElement getCurrentLocationTextField() {
        return driver.findElement(currentLocationTextField);
    }
}
