package com.curbside.android.ui;

import com.curbside.automation.uifactory.Steps;

/**
 * Created by kumar.nipun on 7/10/2017.
 */
public class AbstractScreen {
  static Home homeScreen;
  static Welcome welcomeScreen;
  static Login loginScreen;
  static SignUp signUpScreen;
  static Steps commonSteps;
  static FooterTabs footerTabsScreen;
  static MyAccount accountScreen;
  static StoreDetails storeDetailsScreen;
  static ProductDetails productDetailsScreen;


  static {
	homeScreen = new Home();
    welcomeScreen = new Welcome();
    loginScreen = new Login();
    signUpScreen = new SignUp();
    commonSteps = new Steps();
    footerTabsScreen = new FooterTabs();
    accountScreen = new MyAccount();
    storeDetailsScreen = new StoreDetails();
    productDetailsScreen = new ProductDetails();
  }
}
