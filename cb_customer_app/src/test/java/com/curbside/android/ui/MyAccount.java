package com.curbside.android.ui;

import com.cucumber.listener.Reporter;
import com.curbside.automation.common.configuration.Properties;
import com.curbside.automation.uifactory.UIElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * @author hitesh.grover
 * My Account- After clicking My Account tab on footer strip
 */

public class MyAccount extends AbstractScreen{

    static UIElement signUp= new UIElement(By.id("com.curbside.nCurbside:id/button_sign_up"));
    static UIElement userEmailField = new UIElement(By.id("com.curbside.nCurbside:id/text_email"));
    static UIElement paymentInfo= new UIElement(By.xpath("//android.widget.ListView/android.widget.RelativeLayout[@index='3']"));
    //static UIElement paymentInfoID= new UIElement(By.id("com.curbside.nCurbside:id/textview_my_account_listitem"));
    static UIElement signInButton = new UIElement(By.id("com.curbside.nCurbside:id/button_sign_in"));
    static UIElement userNameField = new UIElement(By.id("com.curbside.nCurbside:id/text_name"));
    static UIElement userPhoneNumberField = UIElement.byId("com.curbside.nCurbside:id/text_phone_number");

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

    @Then("^I should see my given information under Account Info$")
    public void iShouldSeeTheAccountDetailsSameAsProvidedOneS() throws Throwable {
        String phoneNumber = Properties.getVariable("signupPhoneNumber");
        String email = Properties.getVariable("signupEmail");

        String actEmail = userEmailField.getText();
        String actPhoneNumber = userPhoneNumberField.getText();

        Reporter.addStepLog(String.format("Email: actual- %s, expected- %s", actEmail, email));
        Reporter.addStepLog(String.format("Phone: actual- %s, expected- %s", actPhoneNumber, phoneNumber));

        phoneNumber = StringUtils.right(phoneNumber, 10);
        phoneNumber = String.format("1 (%s)%s%s", phoneNumber.substring(1, 4), phoneNumber.substring(4, 6), phoneNumber.substring(6, 10));

        System.out.print(actEmail +"----------------"+actPhoneNumber);
        Assert.assertEquals(actEmail, email);
        Assert.assertEquals(actPhoneNumber, phoneNumber);
        userEmailField.waitFor(10);
        Assert.assertTrue(userEmailField.isDisplayed(), "Android user is not able to sign-in yet");
    }

    @And("^I tap on Sign up button on My Account page$")
    public void iTapOnSignUpButtonOnMyAccountPage() throws Throwable {
        signUp.waitFor(5);
        signUp.tap();
    }

    @Given("^I am not signed into application$")
    public void ensureSignedOut() throws Throwable {
        try {
            footerTabsScreen.tapMyAccount();
            commonSteps.tapButton("Account Info");
            commonSteps.tapButton("Sign Out");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
