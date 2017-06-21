package com.curbside.automation.common.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utilities {

    WebElement element = null;

    private final Logger logger = Logger.getLogger(Utilities.class);
    private AppiumDriver driver;

    /**
     * Gets the instance of Utilities
     *
     * @param driver
     */
    public Utilities(AppiumDriver driver){
        this.driver = driver;
    }
/*
    *//**
     * Checks if element is present or not
     * @param locator
     * @return
     *//*
    *//**
     * To press Enter key for android native keyboard
     *//*
    public void hitEnter(){
        ((AndroidDriver<WebElement>) driver).pressKeyCode(66);
        //((AndroidDriver<WebElement>) driver).pressKeyCode(AndroidKeyCode.ENTER);

    }

    *//**
     * To press native back key for android
     *//*
    public void goBack(){
        ((AndroidDriver<WebElement>) driver).pressKeyCode(AndroidKeyCode.BACK);
    }

 */
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
    public void waitForElement(By locator, int timeout)
    {
        element =null;
        WebDriverWait waitObj = new WebDriverWait(driver,timeout);
        try {
            element = waitObj.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Click on element when its ready to click
     * @param locator
     * @param timeout
     */
    public void clickWhenReady(By locator, int timeout)
    {	element =null;
        WebDriverWait waitObj = new WebDriverWait(driver,timeout);

        try {
            element = waitObj.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Swipe screen
     *
     * @param direction
     * @param offset
     * @param time
     */
    public void swipe(String direction, int offset, int time) {
        int y = driver.manage().window().getSize().getHeight();
        int x = driver.manage().window().getSize().getWidth();
        TouchAction touchAction = new TouchAction(driver);
            if ("right".equalsIgnoreCase(direction)) {
                touchAction.press(x - offset, y / 2).moveTo(-(x - (2 * offset)), 0).release().perform();
            } else if ("left".equalsIgnoreCase(direction)) {
                touchAction.press(offset, y / 2).moveTo((x - (2 * offset)), 0).release().perform();
            } else if ("up".equalsIgnoreCase(direction)) {
                touchAction.press(x / 2, offset).moveTo(0, y - (2 * offset)).release().perform();
            } else if ("down".equalsIgnoreCase(direction)) {
                touchAction.press(x / 2, y - offset).moveTo(0, -(y - (2 * offset))).release().perform();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    /**
     * Swipe the screen horizontally
     * @param startPercentage
     * @param finalPercentage
     * @param anchorPercentage
     * @param duration
     * @throws Exception
     */
    public void swipeHorizontal( double startPercentage, double finalPercentage, double anchorPercentage, int duration) throws Exception {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * finalPercentage);
        new TouchAction(driver).press(startPoint, anchor).waitAction(duration).moveTo(endPoint, anchor).release().perform();
    }

    /**
     * Wait for element to load
     * @param time
     */
    public void implicitWait(int time){
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
    }

    public void clickHomeButton() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.setAutoDelay(300);
        // Use Apple script to get focus on the simulator
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("AppleScript");
        String script = "tell application \"Simulator\" \n activate \n end tell";
        try {
            engine.eval(script);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_H);
        // Have to release the key press
        robot.keyRelease(KeyEvent.VK_H);
        robot.keyRelease(KeyEvent.VK_H);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    public void closeSession() {
        driver.quit();
    }

//    public void h(){
//        ((IOSDriver) driver).runAppInBackground();
//    }

}
