package com.curbside.android.ui;

import com.curbside.automation.uifactory.UIElement;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * @author hitesh.grover
 * My Account- After clicking My Account tab on footer strip
 */

public class MyAccount extends AbstractScreen{

    static UIElement signUp= new UIElement(By.id("com.curbside.nCurbside:id/button_sign_up"));
    static UIElement emailField = new UIElement(By.id("com.curbside.nCurbside:id/text_email"));
    static UIElement paymentInfo= new UIElement(By.xpath("//android.widget.ListView/android.widget.RelativeLayout[@index='3']"));
    //static UIElement paymentInfoID= new UIElement(By.id("com.curbside.nCurbside:id/textview_my_account_listitem"));
    static UIElement signInButton = new UIElement(By.id("com.curbside.nCurbside:id/button_sign_in"));
    static UIElement nameText = new UIElement(By.id("com.curbside.nCurbside:id/text_name"));

    @And("^I tap on PaymentInfo button on Account page$")
    public void iTapOnPaymentInfoButtonOnAccountPage() throws Throwable {
        try{
            paymentInfo.tap();
            Thread.sleep(1000);
            paymentInfo.tap();
        }catch (Exception e){}

    }

    @And("^I tap on sign in button on Account page$")
    public void iTapOnSignInButtonOnAccountPage() throws Throwable {
        signInButton.waitFor(5);
        signInButton.tap();
    }

    @Then("^I should be logged in$")
    public void iShouldBeLoggedIn() throws Throwable {
        nameText.waitFor(5);
        Assert.assertTrue(nameText.isDisplayed(), "User is not logged in");
    }

    @Then("^I should see the Account details with Email id in it$")
    public void iShouldSeeTheAccountDetailsSameAsProvidedOneS() throws Throwable {
        Thread.sleep(1000);
        Assert.assertTrue(emailField.isDisplayed(),"Android : User is not able to Sign up");
    }

    @And("^I tap on Sign up button on My Account page$")
    public void iTapOnSignUpButtonOnMyAccountPage() throws Throwable {
        signUp.waitFor(5);
        signUp.tap();
    }
}
