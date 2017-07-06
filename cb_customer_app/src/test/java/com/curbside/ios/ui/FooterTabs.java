package com.curbside.ios.ui;

import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import com.curbside.automation.uifactory.UIElement;

/**
 * @author kumar.anil
 * Footer tab that contains icons for Shop/ Map/ Cart and My Account
 */

public class FooterTabs {

	@And("^I am on '(.*)' Screen$")
	public void iAmOnScreen(String tabName) throws Throwable {
		new UIElement(By.name(tabName));
	}
}
