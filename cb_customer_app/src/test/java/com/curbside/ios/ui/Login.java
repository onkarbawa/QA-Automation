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
	UIElement signIn = new UIElement(By.name("Sign In"));
	UIElement signInWithEmail = new UIElement(By.name("Sign In with Email"));

	public Login() {
		// TODO Auto-generated constructor stub
	}

	@And("^I enter '(.*)' and '(.*)'$")
	public void signin(String emailText, String passwordText) throws Throwable {
		email.waitFor(10).sendKeys(emailText);
		password.sendKeys(passwordText);
		try {
			Steps.tapButton("Sign In");
		}catch (Exception e){
		}
	}

	@And("^I signin in using signup information$")
	public void iAmSignedInUsingSignupInformation() throws Throwable {
		myAccountScreen.ensureSignedOut();
		if(!myAccountScreen.btnSignIn.isDisplayed())
		{
			footerTabsScreen.tapMyAccount();
			myAccountScreen.tapSignIn();
		}
		
		myAccountScreen.btnSignIn.tapOptional();
			
		
		Steps.tapButton("Sign In with Email");
		signin(Properties.getVariable("signupEmail"), Properties.getVariable("signupPassword"));

		myAccountScreen.btnAccountInfo.waitFor(30);
	}

	@And("^I am on Sign Up screen$")
	public void iAmOnSignUpScreen() throws Throwable {
		footerTabsScreen.tapMyAccount();
		signUpScreen.btnCreateOneNow.tap();
		signUpScreen.btnCreateNewAccount.tap();
	}

	@And("^I Sign-in with '(.*)' and '(.*)'$")
	public void iSignInWithAnd(String emailId, String password) throws Throwable {
		try {
			footerTabsScreen.btnMyAccount.tap();
		}catch (Exception e){}
		signIn.waitFor(2).tap();
		try {
			signIn.tap();
		}catch (Exception e){}
		signInWithEmail.waitFor(1).tap();
		signin(emailId,password);
	}
}