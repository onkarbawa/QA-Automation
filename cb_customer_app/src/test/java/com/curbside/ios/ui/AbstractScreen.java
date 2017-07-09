package com.curbside.ios.ui;

import com.curbside.automation.uifactory.UIElement;

/**
 * Created by bawa.onkar on 06/07/17.
 */
public class AbstractScreen {
	AccountInfo accountInfoScreen = new AccountInfo();
	Cart cartScreen = new Cart();
	CreditCard creditCardScreen = new CreditCard();
	FacebookLogin facebookLoginScreen = new FacebookLogin();
	FooterTabs footerTabsScreen = new FooterTabs();
	Home homeScreen = new Home();
	Login loginScreen = new Login();
	LoyalityCard loyalityCardScreen = new LoyalityCard();
	Map mapScreen = new Map();
	MyAccount myAccountScreen = new MyAccount();
	PaymentInfo paymentInfoScreen = new PaymentInfo();
	ProductDetails productDetailsScreen = new ProductDetails();
	Search searchScreen = new Search();
	Settings settingsScreen = new Settings();
	SignUp signUpScreen = new SignUp();
	Welcome welcomeScreen = new Welcome();

	UIElement loadingIcon = UIElement.byAccessibilityId("In progress");

	public AbstractScreen() {
	}

	void waitForScreenToLoad() throws Throwable {
		loadingIcon.waitForNot(30);
	}
}
