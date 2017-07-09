package com.curbside.ios.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import org.openqa.selenium.By;

/**
 * Created by bawa.onkar on 7/3/17.
 */
public class Login extends AbstractScreen {

	UIElement email = new UIElement(By.className("XCUIElementTypeTextField"));
	UIElement password = new UIElement(By.className("XCUIElementTypeSecureTextField"));

	public Login() {
		// TODO Auto-generated constructor stub
	}

	@And("^I enter '(.*)' and '(.*)'$")
	public void signin(String emailText, String passwordText) throws Throwable {
		email.sendKeys(emailText);
		password.sendKeys(passwordText);
		Steps.tapButton("Sign In");
	}

	@And("^I signin in using signup information$")
	public void iAmSignedInUsingSignupInformation() throws Throwable {
		myAccountScreen.ensureSignedOut();
		try {
			footerTabsScreen.tapMyAccount();
			myAccountScreen.tapSignIn();
		} catch (Exception e) {
		}

		Steps.tapButton("Sign In with Email");
		signin(Properties.getVariable("signupEmail"), Properties.getVariable("signupPassword"));

		myAccountScreen.btnAccountInfo.waitFor(30);
	}
}