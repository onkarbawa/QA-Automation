package com.curbside.automation.steps.ios;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.ios.pages.myAccount.AccountInfoIOS;
import com.curbside.automation.customerApp.ios.pages.signInSignUp.SignInSignUpPageIOS;
import com.curbside.automation.customerApp.ios.pages.signUp.SignUpPageIOS;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 6/21/2017.
 */
public class SignupSteps {

  AccountInfoIOS accountInfoIOS = new AccountInfoIOS(BaseTest.driver);
  SignInSignUpPageIOS signInSignUpPageIOS = new SignInSignUpPageIOS(BaseTest.driver);
  SignUpPageIOS signUpPageIOS = new SignUpPageIOS(BaseTest.driver);

  String randomEmail = signUpPageIOS.getEmail();

  @And("^I click on create one now$")
  public void iClickOnCreateOneNow() {
    accountInfoIOS.createOneNow.click();
  }

  @And("^I click on 'Create an Account'$")
  public void iClickOnCreateAnAccount() {
      signInSignUpPageIOS.createAnAccount.click();
  }

  @And("^I enter 'email' in first box$")
  public void iEnterEmailInFirstBox() {
    signUpPageIOS.email.sendKeys(randomEmail);
  }

  @And("^I enter 'password' in second box$")
  public void iEnterPasswordInSecondBox() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    signUpPageIOS.password.sendKeys("usi@123");
  }

  @And("^I enter 'Phone Number' in third box$")
  public void iEnterPhoneNumberInThirdBox() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    signUpPageIOS.phoneNumber.sendKeys("12345678901");
  }

  @When("^I click on 'Create Account' button$")
  public void iClickOnCreateAccountButton() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    signUpPageIOS.createAccount.click();
  }

  @Then("^I should see email or phone number in my account$")
  public void iShouldSeeEmailOrPhoneNumberInMyAccount() {
    accountInfoIOS.isPhoneNoDisplayed();
    Assert.assertEquals(accountInfoIOS.email.getText(), randomEmail,
            "Entered email is not matched with Account Page");
  }
}
