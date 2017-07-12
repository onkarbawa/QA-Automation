package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by kumar.nipun on 7/4/2017.
 */
public class Login extends AbstractScreen{

  static UIElement signInWithEmailButton = new UIElement(By.id("com.curbside.nCurbside:id/button_sign_in_email"));
  static UIElement emailField = new UIElement(By.id("com.curbside.nCurbside:id/edit_email"));
  static UIElement passwordField = new UIElement(By.id("com.curbside.nCurbside:id/edit_password"));
  UIElement signInButton = UIElement.byXpath("//*[@resource-id='com.curbside.nCurbside:id/button_sign_in' and @index='2']");

  @And("^I enter \"([^\"]*)\" and \"([^\"]*)\" for login$")
  public void iEnterEmailAndPasswordForLogin(String email, String password) throws Throwable {
    emailField.waitFor(3);
    emailField.sendKeys(email);
    DriverFactory.hideKeyboard();
    passwordField.waitFor(5);
    passwordField.sendKeys(password);
    DriverFactory.hideKeyboard();
  }

  @And("^I tap on sign in with email button$")
  public void iTapOnSignInWithEmailButton() throws Throwable {
    signInWithEmailButton.waitFor(3);
    signInWithEmailButton.tap();
  }

  @When("^I tap on sign in button$")
  public void iTapOnSignInButtonOnSignInPage() throws Throwable {
    signInButton.waitFor(5);
    signInButton.tap();
  }

  @And("^I signin in using signup information$")
  public void iAmSignedInUsingSignupInformation() throws Throwable {
    accountScreen.ensureSignedOut();
    try {
      footerTabsScreen.tapMyAccount();
      commonSteps.tapButton("Sign In");
    } catch (Exception e) {
    }


    commonSteps.tapButton("Sign In with Email");
    loginScreen.iEnterEmailAndPasswordForLogin("fusic.test1@gmail.com","fusic@123  ");
    loginScreen.iTapOnSignInButtonOnSignInPage();
    accountScreen.userEmailField.waitFor(30);
  }

}
