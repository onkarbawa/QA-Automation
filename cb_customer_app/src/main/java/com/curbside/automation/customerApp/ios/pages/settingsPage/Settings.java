package com.curbside.automation.customerApp.ios.pages.settingsPage;

import com.curbside.automation.common.pages.Page;
import com.curbside.automation.common.utilities.SwipeOptions;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by bawa.onkar on 21/06/17.
 */
public class Settings extends Page {

    @FindBy(name = "Privacy")
    public WebElement privacyButton;

    @FindBy(name = "Location Services")
    public WebElement locationServicesButton;

    public Settings(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void getPrivacy(){
        privacyButton.click();
    }
    public void doScroll(){

                utilities.swipeOptions(SwipeOptions.Up);


    }
}
