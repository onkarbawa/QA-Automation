package com.curbside.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;

/**
 * Created by kumar.nipun on 7/11/2017.
 */
public class FacebookLogin extends AbstractScreen {

    UIElement emailField = UIElement.byClass("android.widget.EditText");
    UIElement passwordField = UIElement.byXpath("//android.widget.EditText[@password='true']");
    UIElement logInButton = UIElement.byAccessibilityId("Log In ");
    UIElement btnNext = UIElement.byAccessibilityId("Next ");
    UIElement continueButton = UIElement.byUISelector("new UiSelector().description(\"Continue\")");
    UIElement loadingIcon = UIElement.byUISelector("new UiSelector().text(\"Loading...\")");
    UIElement btnSignInWithFacebook = UIElement.byUISelector("new UiSelector().text(\"" + "Sign in with Facebook" + "\")");
    UIElement passwordFieldUI = UIElement.byUISelector("new UiSelector().description(\"Facebook Password\")");
    UIElement cancel = UIElement.byXpath("//android.widget.FrameLayout/android.widget.ImageView[1]");


    @And("^I enter '(.*)' and '(.*)' for facebook login$")
    public void iEnterAndForFacebookLogin(String email, String password) throws Throwable {
        try {
            Steps.tapButton_optional("Sign in with Facebook");
            fbLogin(email, password);
        } catch (Exception e) {
            Reporter.addStepLog("Trying to Login again");
            cancel.tapOptional();
            Steps.tapButton_optional("Sign in with Facebook");
            MobileDevice.getScreenshot(true);
            fbLogin(email, password);
        }
    }

    public void fbLogin(String email, String password) throws Throwable {
        loadingIcon.waitForNot(30);
        emailField.waitFor(30).sendKeys(email);

        if (btnNext.isDisplayed())
            btnNext.tap();
        try {
            passwordField.waitFor(5).sendKeys(password, true);
        } catch (Exception e) {
            passwordFieldUI.sendKeys(password, true);
        }
        MobileDevice.hideKeyboard();
        logInButton.tap();
        Thread.sleep(4000);
        continueButton.waitFor(20);
        for (int i = 0; i < 10; i++) {
            if (continueButton.isDisplayed())
                continueButton.tap();
        }
        Thread.sleep(3000);
        btnSignInWithFacebook.waitForNot(10);
        MobileDevice.getScreenshot(true);
    }

}
