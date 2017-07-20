package com.curbside.android.ui;

import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
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

  public void enterEmailPasswordAndPhoneNumForVerification(String email, String password, String phoneNumber ) throws Throwable {
      Properties.setVariable("signupEmail", email);
      Properties.setVariable("signupPassword", password);
      Properties.setVariable("signupPhoneNumber", phoneNumber);
    emailField.waitFor(3).sendKeys(email);
    passwordField.waitFor(5).sendKeys(password);
  }
  public void enterEmailPassword(String email, String password ) throws Throwable {
      Properties.setVariable("signupEmail", email);
      Properties.setVariable("signupPassword", password);
      emailField.waitFor(3).sendKeys(email);
      passwordField.waitFor(5).sendKeys(password);
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
    Steps.tapButton("Sign In");
    Steps.tapButton("Sign In with Email");
    this.enterEmailPasswordAndPhoneNumForVerification(Properties.getVariable("signupEmail"),
            Properties.getVariable("signupPassword"),Properties.getVariable("signupPhoneNumber"));
    this.iTapOnSignInButtonOnSignInPage();
    accountScreen.viewEmailId.waitFor(10);
  }

  @And("^I enter \"([^\"]*)\" and \"([^\"]*)\" for login$")
  public void iEnterAndForLogin(String email, String password) throws Throwable {
    Properties.setVariable("signInEmail", email);
    Properties.setVariable("signInPassword", password);
    emailField.waitFor(5);
    emailField.sendKeys(email, false);
    passwordField.sendKeys(password, false);
  }

 @And("^I am signed in with \"([^\"]*)\" and \"([^\"]*)\"$")
  public void iAmSignedInWithAnd(String email, String password) throws Throwable {
    Properties.setVariable("signInEmail", email);
    Properties.setVariable("signInPassword", password);
    footerTabsScreen.tapMyAccount();
    Steps.tapButton("Sign In");
    Steps.tapButton("Sign In with Email");
    this.enterEmailPassword(email, password);
    this.iTapOnSignInButtonOnSignInPage();
    accountScreen.viewEmailId.waitFor(10);
  }


  @And("^I sign in into application using username \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void iSignInIntoApplicationUsingUsernameAndPassword(String username, String password) throws Throwable {
    Steps.tapButton("Account");
    Steps.tapButton("Sign In");
    Steps.tapButton("Sign In with Email");
    this.iEnterAndForLogin(username, password);
    this.iTapOnSignInButtonOnSignInPage();
  }
}
