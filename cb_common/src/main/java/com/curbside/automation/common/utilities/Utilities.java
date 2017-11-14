package com.curbside.automation.common.utilities;

import com.curbside.automation.common.configuration.Properties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by hitesh.grover on 6/19/2017.
 */

public class Utilities {

    WebElement element = null;

    private final Logger logger = Logger.getLogger(Utilities.class);
    private AppiumDriver driver;

    /**
     * Gets the instance of Utilities
     * @param driver
     */
    public Utilities(AppiumDriver driver){
        this.driver = driver;
    }

    /**
     * To press Enter key for android native keyboard
     */
    public void hitEnterAndroid(){
        ((AndroidDriver<WebElement>) driver).pressKeyCode(66);
    }

    /**
     * To press native back key for android
     */
    public void goBackAndroid(){
        ((AndroidDriver<WebElement>) driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    /**
     * To find element using locator
     * @param locator
     * @return
     */
    public WebElement getElement(By locator)
    {	element = null;
        try{
            element = driver.findElement(locator);
        }
        catch(NoSuchElementException e){
        }
        return element;
    }

    /**
     * To find list of elements using locator
     * @param locator
     * @return
     */
    public List<WebElement> getElementList(By locator)
    {
        List<WebElement> elementList = new ArrayList<WebElement>();
        elementList = driver.findElements(locator);
        if (elementList.isEmpty()) {
        } else {
        }
        return elementList;
    }

    /**
     * Enter text into textfield
     * @param locator
     * @param data
     */
    public void sendKeys(By locator, String data)
    {	//log.info("in sendKeys function");
        try{
            element=getElement(locator);
            element.sendKeys(data);
//            log.info("Sent data on element with locator: " + locator +
//                    " Data: " + data);
        }
        catch(NoSuchElementException e)
        {
//            log.info("Cannot send data on element with locator: " + locator +
//                    " Data: " + data);
        }

    }

    /**
     * Checks if element is present or not
     * @param locator
     * @return
     */
    public  boolean isElementPresent(By locator){
        List<WebElement> elementList = getElementList(locator);
        int size = elementList.size();
        if (size > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Wait for element to load
     * @param locator
     * @param timeout
     */
    @Deprecated public void waitForElement(WebElement locator, int timeout)
    {
        /*
        element =null;
        WebDriverWait waitObj = new WebDriverWait(driver,timeout);
        try {
            element = waitObj.until(ExpectedConditions.visibilityOf(locator));
        } catch (Exception e)
        {
            e.printStackTrace();
        }*/
    }

    /**
     * Click on element when its ready to click
     * @param locator
     * @param timeout
     */
    @Deprecated public void clickWhenReady(WebElement locator, int timeout)
    {	/*
        element =null;
        WebDriverWait waitObj = new WebDriverWait(driver,timeout);

        try {
            element = waitObj.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e)
        {
            e.printStackTrace();
        }*/

    }

    /**
     * Swipe screen using directions
     *@param
     */
    public void swipeOptions(SwipeOptions option){
        Dimension size = driver.manage().window().getSize();
        String platform = Reporter.getCurrentTestResult().getTestContext().getAttribute("platform").toString();
        int height = size.getHeight();
        int width = size.getWidth();
        int anchor;
        int startPoint;
        int endPoint;
        TouchAction touchAction = new TouchAction(driver);
        switch (option){
            case Right:
                anchor = (int) (height * 0.5);
                startPoint = (int) (width * 0.01);
                endPoint = (int) (width * 0.9);
                if (platform.equalsIgnoreCase("iOS")) {
                    touchAction.press(startPoint, anchor).waitAction(1000).moveTo(endPoint, 0).release().perform();
                 }
                 else if (platform.equalsIgnoreCase("Android")){
                    touchAction.press(startPoint, anchor).waitAction(1000).moveTo(endPoint, anchor).release().perform();
                 }
                 break;
            case Left:
                anchor = (int) (height * 0.5);
                startPoint = (int) (width * 0.8);
                endPoint = (int) (width * 0.01);
                if (platform.equalsIgnoreCase("iOS")) {
                    touchAction.press(startPoint, anchor).waitAction(1000).moveTo((startPoint - (2 * startPoint)), 0).release().perform();
                }
                else if (platform.equalsIgnoreCase("Android")){
                    touchAction.press(startPoint, anchor).waitAction(1000).moveTo(endPoint, anchor).release().perform();
                }
                break;
            case Up:
                anchor = (int) (width * 0.5);
                startPoint = (int) (height * 0.45);
                endPoint = (int) (height * 0.01);
                if (platform.equalsIgnoreCase("iOS")) {
                    touchAction.press(anchor, startPoint).waitAction(1000).moveTo(0, startPoint - (2 * startPoint)).release().perform();
                }else if (platform.equalsIgnoreCase("Android")){
                    touchAction.press(anchor, startPoint).waitAction(1000).moveTo(0, endPoint).release().perform();
                }
                break;
            case Down:
                anchor = (int) (width * 0.5);
                startPoint = (int) (height * 0.01);
                endPoint = (int) (height * 0.8);
                touchAction.press(anchor, startPoint).waitAction(1000).moveTo(0, endPoint).release().perform();
                break;
              default:
                  try {
                      Thread.sleep(2000);
                  } catch (InterruptedException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                  }
        }
    }

    public void setTouchAction(WebElement element){
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(element);
    }

    public String getRandomEmail(){
        return "test"+ System.currentTimeMillis() +"@example.com";
    }
}
