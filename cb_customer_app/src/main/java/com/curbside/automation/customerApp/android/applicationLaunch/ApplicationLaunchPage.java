package com.curbside.automation.customerApp.android.applicationLaunch;


import com.curbside.automation.customerApp.common.CustomerBaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by hitesh.grover on 16/06/17.
 */
public class ApplicationLaunchPage extends CustomerBaseTest{

    private AppiumDriver driver;

    By errorOkButton = By.id(ApplicationLaunchUIMap.ERROR_OK_BUTTON);
    By skipIntro = By.id(ApplicationLaunchUIMap.SKIP_INTRO);
    By allowButton = By.id(ApplicationLaunchUIMap.ALLOW_BUTTON);

    public ApplicationLaunchPage(AppiumDriver driver) {
        this.driver = driver;
    }


    public WebElement getErrorOkButton(){
        return driver.findElement(errorOkButton);
    }

    public WebElement getSkipIntro(){
        return driver.findElement(skipIntro);
    }

    public WebElement getAllowButton(){
        return driver.findElement(allowButton);
    }

    public void gotoHomepage(){
        try{
            getSkipIntro().click();
            getErrorOkButton().click();
            getAllowButton().click();
            getAllowButton().click();
        }catch(Exception e){

        }

    }
}
