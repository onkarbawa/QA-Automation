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
public class SettingsIOS extends Page {

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Privacy']")
    public WebElement privacyButton;

    @FindBy(xpath = "//XCUIElementTypeCell[@name='Location']")
    public WebElement locationButton;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Curbside']")
    public WebElement curbsideApp;

    @FindBy(xpath = "//XCUIElementTypeCell[@label='Always']")
    public WebElement always;

    @FindBy(xpath = "//XCUIElementTypeCell[@label='Never']")
    public WebElement never;

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Always']")
    public WebElement alwaysEnabled;

    public SettingsIOS(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void getPrivacy()  {
        utilities.clickWhenReady(privacyButton,5000);
        System.out.print(privacyButton.getText());
    }
    public void getLocation(){
        utilities.clickWhenReady(locationButton,5000);
    }
    public void getCurbsideApp(){
        utilities.clickWhenReady(curbsideApp,5000);
    }
    public boolean isCheckMarkDisplayed(){
       boolean value = false;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(alwaysEnabled.isDisplayed()){
            value = true;
        }

        return value;
    }

    public void doScrollAndClickOnCurbsideApp(){
        while (true) {
                if(curbsideApp.isDisplayed()){
                   curbsideApp.click();
                   break;
                }
                else
                  {
                utilities.swipeOptions(SwipeOptions.Up);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException y) {
                    y.printStackTrace();
                }
            }
        }


    }

    public void tapOnLocationSelection(String element){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(element.equalsIgnoreCase("Never")){
            never.click();
        }
        else if(element.equalsIgnoreCase("Always"))
        {
            always.click();
        }

    }
}
