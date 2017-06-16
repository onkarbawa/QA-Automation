package com.curbside.automation.customerApp.android.applicationLaunch;


import com.curbside.automation.customerApp.common.CustomerBaseTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by hitesh.grover on 16/06/17.
 */
public class ApplicationLaunch extends CustomerBaseTest{

    private AppiumDriver driver;

    By errorOkButton = By.id(ApplicationLaunchUIMap.ERROR_OK_BUTTON);
    By skipIntro = By.id(ApplicationLaunchUIMap.SKIP_INTRO);
    public ApplicationLaunch(AppiumDriver driver) {
        this.driver = driver;
    }


    public WebElement getErrorOkButton(){
        System.out.print("in error getter ok button");
        return driver.findElement(errorOkButton);
    }
    public WebElement getSkipIntro(){
        System.out.print("in skip getter");
        return driver.findElement(skipIntro);
    }
}
