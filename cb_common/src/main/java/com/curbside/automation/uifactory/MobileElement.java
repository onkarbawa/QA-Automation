package com.curbside.automation.uifactory;

import java.net.MalformedURLException;

/**
 * @author kumar.anil
 *
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.functions.ExpectedCondition;

public class MobileElement {
	
	By locator;
	public MobileElement(By locator) {
        this.locator= locator;
    }
	
	private WebElement getElement() throws Exception 
	{
		return DriverFactory.getDriver().findElement(locator);
	}
	
	public void tap() throws Exception
	{
		getElement().click();
	}
}