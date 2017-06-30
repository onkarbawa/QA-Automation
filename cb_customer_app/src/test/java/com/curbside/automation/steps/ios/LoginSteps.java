package com.curbside.automation.steps.ios;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import com.curbside.automation.customerApp.ios.pages.home.HomePageIOS;
import com.curbside.automation.uifactory.DriverFactory;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 22/06/17.
 */
public class LoginSteps {
    CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();
    public String email = "fusic.test1@gmail.com";
    public String password = "fusic@123";

    @And("^I tap on 'My Account' icon$")
    public void iTapOnMyAccountIcon() {
        customerBaseTestCucumber.getHomePageIOS().myAccount.click();
    }

    @And("^I click on 'Sign in with Facebook' button$")
    public void iClickOnSignInWithFacebookButton()  {

    }

    @And("^I click on 'log in with Facebook App' button$")
    public void iClickOnLogInWithFacebookAppButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @When("^I click on 'Open'$")
    public void iClickOnOpen() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @Then("^I click on Facebook' to continue$")
    public void iClickOnFacebookToContinue() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @Then("^I add 'Facebook Account' to continue$")
    public void iAddFacebookAccountToContinue() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @And("^I tap on Sign In button$")
    public void iTapOnSignInButton() {
        customerBaseTestCucumber.getAccountInfoIOS().signIn.click();
    }

    @And("^I tap on Sign In with e-mail button$")
    public void iTapOnSignInWithEMailButton()  {
        customerBaseTestCucumber.getSignInSignUpPageIOS().signWithMail.click();
    }

    @And("^I enter email in first box$")
    public void iEnterEmailInFirstBox()  {
        customerBaseTestCucumber.getLoginPageIOS().email.sendKeys(email);
    }

    @And("^I enter password in second box$")
    public void iEnterPasswordInSecondBox()  {
        customerBaseTestCucumber.getLoginPageIOS().password.sendKeys(password);
    }

    @Then("^I should see email in my account$")
    public void iShouldSeeEmailInMyAccount() {
        customerBaseTestCucumber.getAccountInfoIOS().isPhoneNoDisplayed();
        Assert.assertEquals(customerBaseTestCucumber.getAccountInfoIOS().email.getText(), email,
                "Entered email is not matched with Account Page");
    }

    @And("^I tap on Payment Info$")
    public void iTapOnPaymentInfo()  {
        customerBaseTestCucumber.getAccountInfoIOS().isPhoneNoDisplayed();
        customerBaseTestCucumber.getPaymentInfo().paymentInfo.click();
        System.out.println(DriverFactory.getDriver().getPageSource());
    }

    @And("^I tap on Sign in with facebook$")
    public void iTapOnSignInWithFacebook()  {
        customerBaseTestCucumber.getSignInSignUpPageIOS().signInWithFacebook.click();
    }

    @And("^I tap on Login with facebook app$")
    public void iTapOnLoginWithFacebookApp() throws InterruptedException {
        customerBaseTestCucumber.getFacebookLoginIOS().isFacebookAppDisplayed();
        customerBaseTestCucumber.getFacebookLoginIOS().loginWithFacebookApp.click();
        Thread.sleep(500);
        customerBaseTestCucumber.getFacebookLoginIOS().tapOpen.click();
    }

    @And("^I enter facebook login email$")
    public void iEnterFacebookLoginEmail()  {
        customerBaseTestCucumber.getFacebookLoginIOS().enterFacebookEmail.sendKeys("jacktest94@gmail.com");
    }

    @And("^I enter facebook password$")
    public void iEnterFacebookPassword()  {
        customerBaseTestCucumber.getFacebookLoginIOS().enterPassword.sendKeys("tftus@123");
    }

    @And("^I tap on login button$")
    public void iTapOnLoginButton()  {
        customerBaseTestCucumber.getFacebookLoginIOS().loginButton.click();
    }
}
