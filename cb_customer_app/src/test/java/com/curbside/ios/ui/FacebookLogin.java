package com.curbside.ios.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.MobileDevice;
import com.curbside.automation.uifactory.Steps;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 7/3/17.
 */
public class FacebookLogin extends AbstractScreen {

    UIElement btnCurbsideSignInWithFacebook = UIElement.byPredicate("label CONTAINS 'Sign In with Facebook'");

    UIElement btnLoginUsingEmail = UIElement.byAccessibilityId("Log In with Phone Number or Email Address");
    UIElement btnLoginWithFacebookApp = UIElement.byAccessibilityId("Log In with the Facebook App");

    UIElement txtInBrowserUsername = UIElement.byClass("XCUIElementTypeTextField");
    UIElement txtInBrowserPassword = UIElement.byClass("XCUIElementTypeSecureTextField");
    //UIElement btnInBrowserLogin = UIElement.byName("Log In");
    UIElement btnInBrowserLogin = UIElement.byPredicate("type ='XCUIElementTypeButton' AND label == 'Log In'");
    UIElement btnInBrowserContinueAs = UIElement.byPredicate("label CONTAINS 'Continue'");

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
        Properties.setVariable("facebookEmail", emailId);
        Properties.setVariable("facebookPassword", password);
        Steps.tapButton("Sign In");
        btnCurbsideSignInWithFacebook.tap();
        btnInBrowserContinueAs.waitFor(25);

        MobileDevice.getScreenshot(true);

        if (!UIElement.byName("Done").isDisplayed()){
            commonSteps.launchApplication("Curbside");
            myAccountScreen.ensureSignedOut();
            Steps.tapButton("Sign In");
            btnCurbsideSignInWithFacebook.tap();
            btnInBrowserContinueAs.waitFor(25);
            Reporter.addStepLog("Relaunch Curbside");
        }

        if ((!btnInBrowserContinueAs.isDisplayed()) && (!txtInBrowserPassword.isDisplayed())) {
            try {
                Steps.tapButton("Done");
            } catch (Exception e) {
                Assert.fail("Not able to locate any element on the screen");
            }
            btnCurbsideSignInWithFacebook.waitFor(2).tap();
            btnInBrowserContinueAs.waitFor(25);
        }

        if (!btnInBrowserContinueAs.isDisplayed()) {
            //	btnLoginUsingEmail.tap();
            //	txtInBrowserUsername.clearText();
            txtInBrowserUsername.clearText();
            txtInBrowserUsername.sendKeys(emailId, false);
            if (!txtInBrowserPassword.isDisplayed() && txtInBrowserUsername.isDisplayed()) {
                btnInBrowserLogin.tap();
                txtInBrowserPassword.waitFor(12);
            }
            txtInBrowserPassword.sendKeys(password, false);
            MobileDevice.getScreenshot(true);
            btnInBrowserLogin.tap();
        }else {
            Reporter.addStepLog("Continue as displayed");
            MobileDevice.getScreenshot(true);
        }

        btnInBrowserContinueAs.waitFor(20).tap();
        btnInBrowserContinueAs.waitForNot(25);

        // Give 2 seconds for curbside to login
        btnCurbsideSignInWithFacebook.waitForNot(20);
    }
}