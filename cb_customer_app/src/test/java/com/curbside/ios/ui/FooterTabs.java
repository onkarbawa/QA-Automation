package com.curbside.ios.ui;

import cucumber.api.java.en.And;
import com.curbside.automation.uifactory.UIElement;

/**
 * @author kumar.anil
 * Footer tab that contains icons for Shop/ Map/ Cart and My Account
 */

public class FooterTabs extends AbstractScreen {
	
	static UIElement btnMyAccount= UIElement.byAccessibilityId("My Account");
	static UIElement btnShop= UIElement.byAccessibilityId("Shop");
	static UIElement btnMap= UIElement.byAccessibilityId("Map");
	static UIElement btnCart= UIElement.byAccessibilityId("Cart");

	@And("^I tap on '(.*)' icon in bottom menu$")
	public void iTapOnMyAccountIconInBottomMenu(String tabName) throws Throwable {
		UIElement.byAccessibilityId(tabName).tap();
	}
	
	public static void tapMyAccount() throws Throwable
	{
		btnMyAccount.tap();
	}
	
	public static void tapShop() throws Throwable
	{
		btnShop.tap();
	}
	
	public static void tapMap() throws Throwable
	{
		btnMap.tap();
	}
	
	@And("^I go to Cart screen$")
	public static void tapCart() throws Throwable
	{
		btnCart.tap();
	}
}
