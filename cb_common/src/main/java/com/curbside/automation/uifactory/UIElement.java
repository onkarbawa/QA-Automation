package com.curbside.automation.uifactory;

/**
 * @author kumar.anil
 *
 */

import org.apache.log4j.Logger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIElement {

	private final Logger logger = Logger.getLogger(UIElement.class);

	By locator;

	public UIElement(By locator) {
		this.locator = locator;
	}

	public WebElement getElement() throws Throwable {
		return DriverFactory.getDriver().findElement(locator);
	}

	public void tap() throws Throwable {
		getElement().click();
	}

	public String getText() throws Throwable {
		return getElement().getText();
	}

	public UIElement scrollTo() throws Throwable {
		MobileElement m = (MobileElement) getElement();

		// Vertical scroll
		int deviceHeight = MobileDevice.getHeight();
		long startTime = System.currentTimeMillis();

		if (m.getCenter().y > deviceHeight) {
			System.out.println("Swiping up to make element visible...");
			while (m.getCenter().getY() > deviceHeight && (System.currentTimeMillis() - startTime) < 60000) {
				MobileDevice.swipeUp();
				m = (MobileElement) getElement();
			}
		}

		return this;
	}
	
	public UIElement scrollTo(SwipeDirection swipeDirection) throws Throwable {
		for (int i = 0; i < 10; i++) {
			if(this.isDisplayed()) break;
			MobileDevice.swipe(swipeDirection);
		}
		return this;
	}

	public void sendKeys(String text) throws Throwable {
		getElement().sendKeys(text);
	}

	public void clearText() throws Throwable {
		getElement().clear();
	}
	/**
	 * Wait for element to load
	 * 
	 * @param timeout
	 */
	public UIElement waitFor(int timeout) throws Throwable {
		WebDriverWait waitObj = new WebDriverWait(DriverFactory.getDriver(), timeout);
		try {
			waitObj.until(ExpectedConditions.visibilityOfElementLocated(this.locator));
		} catch (Exception e) {
			logger.debug("Unable to wait for visibility of element due to: " + e.getMessage());
		}
		
		return this;
	}

	public boolean isDisplayed() {
		try {
			return getElement().isDisplayed();
		} catch (Throwable ex) {
			logger.debug(ex.getMessage());
			return false;
		}
	}

	public static UIElement byXpath(String locator) {
		return new UIElement(By.xpath(locator));
	}
	
	public static UIElement byPredicate(String locator) {
		return new UIElement(MobileBy.iOSNsPredicateString(locator));
	}
	
	public static UIElement byCSS(String locator) {
		return new UIElement(By.cssSelector(locator));
	}
	
	public static UIElement byId(String locator) {
		return new UIElement(By.id(locator));
	}
	
	public static UIElement byUISelector(String locator) {
		return new UIElement(MobileBy.AndroidUIAutomator(locator));
	}
	
	public static UIElement byName(String locator) {
		return new UIElement(By.name(locator));
	}
	
	public static UIElement byAccessibilityId(String locator) {
		return new UIElement(MobileBy.AccessibilityId(locator));
	}
	
	public static UIElement byClass(String locator) {
		return new UIElement(MobileBy.className(locator));
	}

	public String getAttribute(String attrName) throws Throwable {
		return getElement().getAttribute(attrName);
	}

	public void waitForNot(int timeout) throws Throwable {
		WebDriverWait waitObj = new WebDriverWait(DriverFactory.getDriver(), timeout);
		try {
			waitObj.until(ExpectedConditions.invisibilityOfElementLocated(this.locator));
		} catch (Exception e) {
			logger.debug("Unable to wait for invisibility of element due to: " + e.getMessage());
		} 
	}

    public UIElement swipeUpSlow() throws Throwable {
        for (int i = 0; i < 20; i++) {
            if(this.isDisplayed()) break;
            MobileDevice.swipeUpSlowly();
        }
        return this;
    }

}