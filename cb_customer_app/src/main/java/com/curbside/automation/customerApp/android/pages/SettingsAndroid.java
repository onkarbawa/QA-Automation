package com.curbside.automation.customerApp.android.pages;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by hitesh.grover on 30/06/17.
 */
public class SettingsAndroid extends Page {

    @FindBy(id = "com.android.settings:id/search")
    public WebElement search;

    @FindBy(id = "android:id/search_src_text")
    public WebElement searchTextField;

    @FindBy(xpath = "//android.widget.ListView/android.widget.LinearLayout/android.widget.RelativeLayout/" +
            "android.widget.TextView[@text=‘App permissions’]")
    public WebElement appPermissonButton;


    public SettingsAndroid(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public WebElement getSearchButton() {
        return search;
    }

    public WebElement getSearchTextField() {
        return searchTextField;
    }

    public WebElement getAppPermissions() {
        return appPermissonButton;
    }
}
