package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * Created by kumar.nipun on 7/4/2017.
 */
public class Login extends AbstractScreen{

  UIElement signInWithEmailButton = UIElement.byId("com.curbside.nCurbside:id/button_sign_in_email");
  static UIElement emailField = new UIElement(By.id("com.curbside.nCurbside:id/edit_email"));
  static UIElement passwordField = new UIElement(By.id("com.curbside.nCurbside:id/edit_password"));
  UIElement signInButton = UIElement.byXpath("//*[@resource-id='com.curbside.nCurbside:id/button_sign_in' and @index='2']");

  @And("^I enter \"([^\"]*)\" and \"([^\"]*)\" for login and \"([^\"]*)\" for verification$")
  public void iEnterEmailAndPasswordForLogin(String email, String password, String phoneNumber) throws Throwable {
      Properties.setVariable("signupEmail", email);
      Properties.setVariable("signupPassword", password);
      Properties.setVariable("signupPhoneNumber", phoneNumber);
    emailField.waitFor(3).sendKeys(email);
    DriverFactory.hideKeyboard();
    passwordField.waitFor(5).sendKeys(password);
    DriverFactory.hideKeyboard();
  }

  @And("^I tap on sign in with email button$")
  public void iTapOnSignInWithEmailButton() throws Throwable {
    signInWithEmailButton.waitFor(3).tap();
  }

  @When("^I tap on sign in button$")
  public void iTapOnSignInButtonOnSignInPage() throws Throwable {
    signInButton.waitFor(5).tap();
  }

  @And("^I signin in using signup information$")
  public void iAmSignedInUsingSignupInformation() throws Throwable {
    accountScreen.ensureSignedOut();
    Steps.tapButton("Sign In");
    Steps.tapButton("Sign In with Email");
    this.iEnterEmailAndPasswordForLogin(Properties.getVariable("signupEmail"),
            Properties.getVariable("signupPassword"),Properties.getVariable("signupPhoneNumber"));
    this.iTapOnSignInButtonOnSignInPage();
    accountScreen.userEmailField.waitFor(30);
  }

  @And("^I enter \"([^\"]*)\" and \"([^\"]*)\" for login$")
  public void iEnterAndForLogin(String email, String password) throws Throwable {
    Properties.setVariable("signInEmail", email);
    Properties.setVariable("signInPassword", password);
    emailField.waitFor(3).sendKeys(email);
    DriverFactory.hideKeyboard();
    passwordField.waitFor(5).sendKeys(password);
    DriverFactory.hideKeyboard();
  }
}
