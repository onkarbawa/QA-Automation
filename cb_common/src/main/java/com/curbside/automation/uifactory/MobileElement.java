package com.curbside.automation.uifactory;

import org.openqa.selenium.By;

public class MobileElement {
	
	By locator;
	public MobileElement(By locator) {
        this.locator= locator;
    }
}