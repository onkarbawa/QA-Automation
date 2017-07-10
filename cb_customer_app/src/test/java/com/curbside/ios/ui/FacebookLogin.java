package com.curbside.ios.ui;

import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by bawa.onkar on 7/3/17.
 */
public class FacebookLogin extends AbstractScreen {
	
	UIElement btnCurbsideSignInWithFacebook= UIElement.byXpath("//*[contains(@label,'Sign In with Facebook')]");
	
	UIElement btnLoginUsingEmail = UIElement.byAccessibilityId("Log In with Phone Number or Email Address");
	UIElement btnLoginWithFacebookApp = UIElement.byAccessibilityId("Log In with the Facebook App");

	UIElement txtInBrowserUsername = UIElement.byXpath("//XCUIElementTypeSecureTextField/preceding-sibling::XCUIElementTypeTextField");
	UIElement txtInBrowserPassword = UIElement.byClass("XCUIElementTypeSecureTextField");
	UIElement btnInBrowserLogin = UIElement.byName("Log In");
	UIElement btnInBrowserContinueAs = UIElement.byXpath("//*[contains(@label,'Continue as')]");

	UIElement facebookapp = new UIElement(By.xpath("//XCUIElementTypeStaticText[@name='Log In with the Facebook App']"));
	UIElement signWithFacebook = new UIElement(By.xpath("//XCUIElementTypeOther/XCUIElementTypeButton[4]']"));
	UIElement enterFacebookEmail = new UIElement(By.xpath("//XCUIElementTypeTextField[@name='username-field']"));
	UIElement enterPassword = new UIElement(By.xpath("//XCUIElementTypeSecureTextField[@name='password-field']"));
	UIElement loginButton = new UIElement(By.xpath("//XCUIElementTypeButton[@name='login-button']"));

	public FacebookLogin() {
		// TODO Auto-generated constructor stub
	}

	@And("^I login to facebook in browser with '(.*)' and '(.*)'$")
	public void i_login_via_browswr(String emailId, String password) throws Throwable {
		try {
			btnCurbsideSignInWithFacebook.tap();
		} catch (Exception e) {}
		
		btnLoginUsingEmail.waitFor(10).tap();
		txtInBrowserPassword.waitFor(10).sendKeys(emailId);
		txtInBrowserPassword.sendKeys(password);
		btnInBrowserLogin.tap();
		btnInBrowserContinueAs.waitFor(10).tap();
		btnInBrowserContinueAs.waitForNot(10);

		// Give 2 seconds for curbside to login
		Thread.sleep(2000);
		MobileDevice.getScreenshot(true);
	}

	@And("^I enter email and password$")
	public void iEnterEmailAndPassword() throws Throwable {
		enterFacebookEmail.sendKeys("jacktest94@gmail.com");
		enterPassword.sendKeys("tftus@123");
	}

	@And("^I enter '(.*)' and '(.*)' for facebook$")
	public void iEnterAndForFacebook(String emailText, String passwordText) throws Throwable {
		enterFacebookEmail.sendKeys(emailText);
		enterPassword.sendKeys(passwordText);
	}

	@And("^I tap on 'Log In with the Facebook App'$")
	public void iTapOnLogInWithTheFacebookApp() throws Throwable {
		facebookapp.waitFor(25);
		facebookapp.tap();
	}

	@And("^I tap on 'Sign In with Facebook'$")
	public void iTapOnSignInWithFacebook() throws Throwable {
		signWithFacebook.tap();
	}
}