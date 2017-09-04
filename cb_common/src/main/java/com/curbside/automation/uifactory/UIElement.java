package com.curbside.automation.uifactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kumar.anil
 *
 */

import org.apache.log4j.Logger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.curbside.automation.devicefactory.DeviceStore;

public class UIElement {

	private final Logger logger = Logger.getLogger(UIElement.class);

	By locator = null;

	public UIElement(By locator) {
		this.locator = locator;
	}

	public WebElement getElement() throws Throwable {
		try {
			return DriverFactory.getDriver().findElement(locator);
		} catch (Exception e) {
			MobileDevice.hideKeyboard();
			return DriverFactory.getDriver().findElement(locator);
		}
	}
	
	public List<WebElement> getElements() throws Throwable {
		return DriverFactory.getDriver().findElements(locator);
	}

	public void refershScreen() throws Throwable {
		DriverFactory.getDriver().navigate().refresh();
	}

	public void tap() throws Throwable {
		getElement().click();
	}
	
	public void tapOptional() throws Throwable {
		try {
			getElement().click();
		} catch (Exception e) {}
	}

	public String getText() throws Throwable {
		return getElement().getText();
	}

	public UIElement scrollTo() throws Throwable {
		MobileElement m = (MobileElement) getElement();

		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", m.getId());
		scrollObject.put("toVisible", "true");
		((AppiumDriver)DriverFactory.getDriver()).executeScript("mobile: scroll", scrollObject);
	
		/*
		// Vertical scroll
		int deviceHeight = MobileDevice.getHeight();
		long startTime = System.currentTimeMillis();

		if (m.getCenter().y > deviceHeight) {
			System.out.println("Swiping up to make element visible...");
			while (m.getCenter().getY() > deviceHeight && (System.currentTimeMillis() - startTime) < 60000) {
				MobileDevice.swipeUp();
				m = (MobileElement) getElement();
			}
		}*/

		return this;
	}
	public UIElement scrollToUp() throws Throwable {
		MobileElement m = (MobileElement) getElement();

		// Vertical scroll
		int deviceHeight = MobileDevice.getHeight();
		long startTime = System.currentTimeMillis();

		if (m.getCenter().y < deviceHeight) {
			System.out.println("Swiping down to make element visible...");
			while (m.getCenter().getY() < deviceHeight && (System.currentTimeMillis() - startTime) < 60000) {
				MobileDevice.swipeDown();
				m = (MobileElement) getElement();
			}
		}

		return this;
	}

	public UIElement scrollTo(SwipeDirection swipeDirection) throws Throwable {
		for (int i = 0; i < 10; i++) {
				if (this.isDisplayed())
					break;
				
				MobileDevice.swipe(swipeDirection);
			}
		
		return this;
	}
	
	public UIElement setText(String text) throws Throwable {
		return setText(text, true);
	}
	
	public UIElement sendKeys(String keySequence, boolean hideKeyboardAfterTyping) throws Throwable {
		getElement().sendKeys(keySequence);
		
		if (hideKeyboardAfterTyping)
			MobileDevice.hideKeyboard();
		
		return this;
	}
	
	public UIElement sendKeys(String keySequence) throws Throwable {
		return sendKeys(keySequence, true);
	}

	public UIElement setText(String text, boolean hideKeyboardAfterTyping) throws Throwable {
		MobileElement e = (MobileElement) getElement();
		
		this.clearText();
		this.sendKeys(text);
		
		if (hideKeyboardAfterTyping)
			MobileDevice.hideKeyboard();

		return this;
	}

	public WebElement clearText() throws Throwable {
		WebElement e= getElement();
		e.clear();
		return e;
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
			if (this.isDisplayed())
				break;
			MobileDevice.swipeUpSlowly();
		}
		return this;
	}

	public void touch() throws Throwable {
		new TouchAction((AppiumDriver) (DriverFactory.getDriver())).tap(getElement()).perform();
	}

	public void tapCenter() throws Throwable {
		MobileElement m = (MobileElement) getElement();
		Point p = m.getCenter();
		new TouchAction((AppiumDriver) (DriverFactory.getDriver())).tap(p.x, p.y).perform();
	}

	public int getCount() throws Throwable {
		return getElements().size();
	}

	public Dimension getSize() throws Throwable {
		return getElement().getSize();
	}

	public int getHeight() throws Throwable {
		return getSize().getHeight();
	}

	public int getWidth() throws Throwable {
		return getSize().getWidth();
	}
	public Point getLocation() throws Throwable {
		return getElement().getLocation();
	}
	public int getX() throws Throwable {
		return getLocation().getX();
	}
	public int getY() throws Throwable {
		return getLocation().getY();
	}

	public void longPress(int seconds) throws Throwable {
		new TouchAction((AppiumDriver) (DriverFactory.getDriver())).longPress(getElement(), Duration.ofSeconds(seconds)).release().perform();
	}
}