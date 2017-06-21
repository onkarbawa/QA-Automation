package com.curbside.automation.customerApp.ios.applicationLaunch;

import com.curbside.automation.common.utilities.Utilities;
import com.curbside.automation.customerApp.common.CustomerBaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by bawa.onkar on 14/06/17.
 */
public class ApplicationLaunchPageIOS extends CustomerBaseTest {

    private AppiumDriver driver;
    private Utilities util ;

    By allow = By.name(ApplicationLaunchUIMap.ALLOW_BUUTON);
    By info = By.xpath(ApplicationLaunchUIMap.INFO);

    /**
     * Gets a AppiumDriver to initialize
     *
     * @param driver
     */
    public ApplicationLaunchPageIOS(AppiumDriver driver) {
        this.driver = driver;
        util = new Utilities(this.driver);
    }

    /**
     * Gets tap on button
     * @return
     */
    public WebElement getTapButton(){
        return driver.findElement(allow);
    }

//    public void doSwipe(String direction, int offset, int time, int noOfTimes){
//        for(int i = 0;i < noOfTimes;i++) {
//            util.swipe(direction, offset, time);
//        }
//    }




}
