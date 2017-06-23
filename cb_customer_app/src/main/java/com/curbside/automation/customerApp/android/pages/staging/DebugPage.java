package com.curbside.automation.customerApp.android.pages.staging;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by hitesh.grover on 22/06/17.
 */
public class DebugPage extends Page {

    public final By apiHostButton = By.id(DebugPageUIMap.API_HOST_BUTTON);
    public final By apiHostDialogTextField = By.id(DebugPageUIMap.API_HOST_DIALOG_TEXT_FIELD);
    public final By apiHostDialogOkButton = By.id(DebugPageUIMap.API_HOST_DIALOG_OK_BUTTON);
    public final By backButton = By.id(DebugPageUIMap.BACK_BUTTON);

    public DebugPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Gets ApiHost button from Debug page
     * @return
     */
    public WebElement getApiHostButton() {
        return driver.findElement(apiHostButton);
    }

    /**
     * Gets ApiHost Dialog text field
     * @return
     */
    public WebElement getApiHostDialogTextField() {
        return driver.findElement(apiHostDialogTextField);
    }

    /**
     * Gets ApiHost Dialog Ok button
     * @return
     */
    public WebElement getApiHostDialogOkButton() {
        return driver.findElement(apiHostDialogOkButton);
    }

    /**
     * Gets back button from staging page/Debug page
     * @return
     */
    public WebElement getBackButton() {
        return driver.findElement(backButton);
    }

}
