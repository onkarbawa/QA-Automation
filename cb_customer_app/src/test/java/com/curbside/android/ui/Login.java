package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by kumar.nipun on 7/4/2017.
 */
public class Login {

  static UIElement signInWithEmailButton = new UIElement(By.id("com.curbside.nCurbside:id/button_sign_in_email"));
  static UIElement emailField = new UIElement(By.id("com.curbside.nCurbside:id/edit_email"));
  static UIElement passwordField = new UIElement(By.id("com.curbside.nCurbside:id/edit_password"));
  static UIElement signInButton = new UIElement(By.id("com.curbside.nCurbside:id/button_sign_in"));

  @And("^I enter \"([^\"]*)\" and \"([^\"]*)\" for login$")
  public void iEnterEmailAndPasswordForLogin(String email, String password) throws Throwable {
    emailField.waitFor(3);
    emailField.sendKeys(email);
    passwordField.sendKeys(password);
  }

  @And("^I tap on sign in with email button$")
  public void iTapOnSignInWithEmailButton() throws Throwable {
    signInWithEmailButton.waitFor(3);
    signInWithEmailButton.tap();
  }

  @When("^I tap on sign in button$")
  public void iTapOnSignInButtonOnSignInPage() throws Throwable {
    signInButton.tap();
  }
}
