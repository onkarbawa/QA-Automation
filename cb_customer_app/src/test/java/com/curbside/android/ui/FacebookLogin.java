package com.curbside.android.ui;

import com.curbside.automation.uifactory.AndroidDevice;
import com.curbside.automation.uifactory.DriverFactory;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by kumar.nipun on 7/11/2017.
 */
public class FacebookLogin extends AbstractScreen {

  UIElement emailField = UIElement.byXpath("//*[@text='Email address or phone number']");
  UIElement passwordField = UIElement.byXpath("//*[@text='Facebook password']");
  UIElement logInButton = UIElement.byAccessibilityId("Log In");

  @And("^I enter \"([^\"]*)\" and \"([^\"]*)\" for facebook login$")
  public void iEnterAndForFacebookLogin(String email, String password) throws Throwable {
    emailField.waitFor(20);

    DriverFactory.getDriver().getWindowHandles().forEach(handle -> System.out.println(handle));

    emailField.sendKeys(email);
    passwordField.sendKeys(password);
    DriverFactory.hideKeyboard();
    logInButton.tap();
  }

}
