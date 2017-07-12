package com.curbside.android.ui;

import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import io.appium.java_client.AppiumDriver;

/**
 * Created by kumar.nipun on 7/11/2017.
 */
public class FacebookLogin extends AbstractScreen {

  UIElement emailField = UIElement.byXpath("//*[@text='Email address or phone number']");
  UIElement passwordField = UIElement.byXpath("//*[@text='Facebook password']");
  UIElement logInButton = UIElement.byXpath("//*[@content-desc='Log In']");
  UIElement continueButton = UIElement.byId("u_0_3");

  @And("^I enter \"([^\"]*)\" and \"([^\"]*)\" for facebook login$")
  public void iEnterAndForFacebookLogin(String email, String password) throws Throwable {
    emailField.waitFor(30);

    emailField.sendKeys(email);
    DriverFactory.hideKeyboard();
    passwordField.sendKeys(password);
    AndroidDevice.pressEnter();

    continueButton.waitFor(20);
    continueButton.tap();
  }

}
