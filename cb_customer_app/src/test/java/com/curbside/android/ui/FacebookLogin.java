package com.curbside.android.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by kumar.nipun on 7/11/2017.
 */
public class FacebookLogin extends AbstractScreen {

  UIElement emailField = UIElement.byClass("android.widget.EditText");
  UIElement passwordField = UIElement.byXpath("//android.widget.EditText[@password='true']");
  UIElement logInButton = UIElement.byAccessibilityId("Log In ");
  UIElement continueButton = UIElement.byUISelector("new UiSelector().description(\"Continue \")");
  UIElement loadingIcon = UIElement.byUISelector("new UiSelector().text(\"Loading...\")");
  UIElement btnSignInWithFacebook= UIElement.byUISelector("new UiSelector().text(\"" + "Sign in with Facebook" + "\")");

  @And("^I enter \"([^\"]*)\" and \"([^\"]*)\" for facebook login$")
  public void iEnterAndForFacebookLogin(String email, String password) throws Throwable {
    
	/*
	try {
		MobileDevice.switchToWebView();
		MobileDevice.getSource(true);
	} catch (Exception e) {
		// TODO: handle exception
	}
    finally {
    	MobileDevice.switchToNativeView();
    }*/
	
	loadingIcon.waitForNot(30);
    emailField.waitFor(30).sendKeys(email);
    passwordField.sendKeys(password);
    logInButton.tap();
    continueButton.waitFor(20);
    for (int i = 0; i < 10; i++) {
    	if(continueButton.isDisplayed())
    		continueButton.tap();
	}
    
    Thread.sleep(3000);
    btnSignInWithFacebook.waitForNot(10);
    MobileDevice.getScreenshot(true);
  }

}
