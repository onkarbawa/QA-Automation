package com.curbside.automation.customerApp.android.pages.common;

import com.curbside.automation.common.pages.Page;
import com.curbside.automation.customerApp.android.pages.applicationLaunch.ApplicationLaunchUIMap;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by hitesh.grover on 22/06/17.
 */
public class CommonLocators extends Page {

    public final By shopTabButton = By.xpath(CommonLocatorsUIMap.SHOP_TAB);
    public final By mapTabButton = By.xpath(CommonLocatorsUIMap.MAP_TAB);
    public final By cartTabButton = By.xpath(CommonLocatorsUIMap.CART_TAB);
    public final By accountTabButton = By.xpath(CommonLocatorsUIMap.ACCOUNT_TAB);

    public CommonLocators(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Gets Shop Button element
     * @return
     */
    public WebElement getShopTabButton() {
        return driver.findElement(shopTabButton);
    }

    /**
     * Gets Map Button element
     * @return
     */
    public WebElement getMapTabButton() {
        return driver.findElement(mapTabButton);
    }

    /**
     * Gets Cart Button element
     * @return
     */
    public WebElement getCartTabButton() {
        return driver.findElement(cartTabButton);
    }

    /**
     * Gets Account Button element
     * @return
     */
    public WebElement getAccountTabButton() {
        return driver.findElement(accountTabButton);
    }

}
