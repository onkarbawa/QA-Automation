package com.curbside.automation.uifactory;

/**
 * @author kumar.anil
 *
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.MobileElement;

public class UIElement {

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
		long startTime= System.currentTimeMillis();
		
		if (m.getCenter().y > deviceHeight) {
			System.out.println("Swiping up to make element visible...");
			while (m.getCenter().getY() > deviceHeight && (System.currentTimeMillis()- startTime) < 60000) {
				MobileDevice.swipeUp();
				m = (MobileElement) getElement();
			}
		}
		
		return this;
	}
}