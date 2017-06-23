package com.curbside.automation.customerApp.android.pages.shop;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by hitesh.grover on 22/06/17.
 */
public class HomePage extends Page{

    public final By currentLocationButton = By.id(HomePageUIMap.CURRENT_LOCATION_BUTTON);
    public final By searchButton = By.id(HomePageUIMap.SEARCH_BUTTON);
    public final By nearByStores = By.xpath(HomePageUIMap.NEARBY_STORES);
    public final By productSearchTextField = By.id(HomePageUIMap.PRODUCT_SEARCH_TEXT_FIELD);

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Gets Current Location Button element
     * @return
     */
    public WebElement getCurrentLocationButton() {
        return driver.findElement(currentLocationButton);
    }

    /**
     * Gets Search Button element
     * @return
     */
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }

    /**
     * Gets Nearby Stores Button element
     * @return
     */
    public WebElement getNearByStores() {
        return driver.findElement(nearByStores);
    }

    /**
     * Gets Product search text field element
     * @return
     */
    public WebElement getProductSearchTextField() {
        return driver.findElement(productSearchTextField);
    }

}
