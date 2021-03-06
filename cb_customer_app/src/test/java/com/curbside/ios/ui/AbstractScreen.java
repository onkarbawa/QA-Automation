package com.curbside.ios.ui;

import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;

/**
 * Created by bawa.onkar on 06/07/17.
 */
public class AbstractScreen {
	static AccountInfo accountInfoScreen;
	static Cart cartScreen;
	static CreditCard creditCardScreen;
	static FacebookLogin facebookLoginScreen;
	static FooterTabs footerTabsScreen;
	static Home homeScreen;
	static Login loginScreen;
	static LoyalityCard loyalityCardScreen;
	static Map mapScreen;
	static MyAccount myAccountScreen;
	static PaymentInfo paymentInfoScreen;
	static ProductDetails productDetailsScreen;
	static Search searchScreen;
	static Settings settingsScreen;
	static SignUp signUpScreen;
	static Welcome welcomeScreen;
	static StoreDetails storeDetailsScreen;
	static GetMyOrder getMyOrderScreen;
	static MyOrders myOrdersScreen;
	static CancelledOrder cancelledOrderScreen;
	
	static Steps commonSteps;

	static UIElement loadingIcon = UIElement.byAccessibilityId("In progress");

	static {
		accountInfoScreen = new AccountInfo();
		cartScreen = new Cart();
		creditCardScreen = new CreditCard();
		facebookLoginScreen = new FacebookLogin();
		footerTabsScreen = new FooterTabs();
		homeScreen = new Home();
		loginScreen = new Login();
		loyalityCardScreen = new LoyalityCard();
		mapScreen = new Map();
		myAccountScreen = new MyAccount();
		paymentInfoScreen = new PaymentInfo();
		productDetailsScreen = new ProductDetails();
		searchScreen = new Search();
		settingsScreen = new Settings();
		signUpScreen = new SignUp();
		welcomeScreen = new Welcome();
		storeDetailsScreen = new StoreDetails();
		getMyOrderScreen = new GetMyOrder();
		myOrdersScreen = new MyOrders();
		cancelledOrderScreen = new CancelledOrder();
		commonSteps= new Steps();
	}

	void waitForScreenToLoad() throws Throwable {
		loadingIcon.waitForNot(30);
	}
}
