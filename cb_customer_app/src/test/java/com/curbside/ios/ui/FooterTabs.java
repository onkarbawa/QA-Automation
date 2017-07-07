package com.curbside.ios.ui;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import com.curbside.automation.uifactory.UIElement;

/**
 * @author kumar.anil
 * Footer tab that contains icons for Shop/ Map/ Cart and My Account
 */

public class FooterTabs {

	@And("^I tap on '(.*)' icon in bottom menu$")
	public void iTapOnMyAccountIconInBottomMenu(String tabName) throws Throwable {
		UIElement.byAccessibilityId(tabName).tap();
	}
}
