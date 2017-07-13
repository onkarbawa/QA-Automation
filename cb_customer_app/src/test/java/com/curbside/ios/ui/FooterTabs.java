package com.curbside.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.Then;

import java.io.File;

/**
 * @author kumar.anil Footer tab that contains icons for Shop/ Map/ Cart and My
 *         Account
 */

public class FooterTabs extends AbstractScreen {

	UIElement btnMyAccount = UIElement.byAccessibilityId("My Account");
	UIElement btnShop = UIElement.byAccessibilityId("Shop");
	UIElement btnMap = UIElement.byAccessibilityId("Map");
	UIElement btnCart = UIElement.byAccessibilityId("Cart");

	public FooterTabs() {
		// TODO Auto-generated constructor stub
	}

	@And("^I tap on '(.*)' icon in bottom menu$")
	public void iTapOnMyAccountIconInBottomMenu(String tabName) throws Throwable {
		UIElement.byAccessibilityId(tabName).tap();
	}

	public void tapMyAccount() throws Throwable {
		btnMyAccount.tap();
	}

	public void tapShop() throws Throwable {
		btnShop.tap();
	}

	public void tapMap() throws Throwable {
		btnMap.tap();
	}

	@And("^I go to Cart screen$")
	public void tapCart() throws Throwable {
		btnCart.tap();
	}
}
