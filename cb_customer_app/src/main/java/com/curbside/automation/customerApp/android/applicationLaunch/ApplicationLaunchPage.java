package com.curbside.automation.customerApp.android.applicationLaunch;


import com.curbside.automation.common.utilities.Utilities;
import com.curbside.automation.customerApp.common.CustomerBaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.SwipeElementDirection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by hitesh.grover on 16/06/17.
 */
public class ApplicationLaunchPage {

    private AppiumDriver driver;
    private Utilities util ;

    By errorOkButton = By.id(ApplicationLaunchUIMap.ERROR_OK_BUTTON);
    By skipIntro = By.id(ApplicationLaunchUIMap.SKIP_INTRO);
    By allowButton = By.id(ApplicationLaunchUIMap.ALLOW_BUTTON);
    By getStarted = By.id(ApplicationLaunchUIMap.GET_STARTED);

    public ApplicationLaunchPage(AppiumDriver driver) {
        this.driver = driver;
        util = new Utilities(this.driver);
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

    public WebElement getStartedButton(){
        return driver.findElement(getStarted);
    }

    public void gotoHomepage(){
        try{
            System.out.println(util.isElementPresent(skipIntro));
            util.swipeHorizontal(0.7,0.03,0.5,1000);
            //Thread.sleep(3000);
            util.swipeHorizontal(0.9,0.01,0.5,1000);
            //Thread.sleep(3000);
            util.swipeHorizontal(0.9,0.01,0.5,1000);
            Thread.sleep(3000);
            getStartedButton().click();

//            getSkipIntro().click();
//            getErrorOkButton().click();
//            getAllowButton().click();
//            getAllowButton().click();
        }catch(Exception e){

        }
    }

    public void swipe(){

    }


}
