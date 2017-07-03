package com.curbside.automation.steps.ios;

import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import com.curbside.automation.uifactory.DriverFactory;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * Created by bawa.onkar on 6/21/2017.
 */
public class SignupSteps {

  CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();

  String randomEmail = customerBaseTestCucumber.getSignUpPageIOS().getEmail();

  public SignupSteps() throws Throwable {
  }

  @And("^I tap on create one now$")
  public void iTapOnCreateOneNow() throws Throwable {
    System.out.println(DriverFactory.getDriver().getPageSource());
    customerBaseTestCucumber.getAccountInfoIOS().createOneNow.click();
  }

  @And("^I tap on 'Create an Account'$")
  public void iTapOnCreateAnAccount() throws Throwable {
    customerBaseTestCucumber.getSignInSignUpPageIOS().createAnAccount.click();
  }

  @And("^I enter 'email' in first box$")
  public void iEnterEmailInFirstBox() throws Throwable {
    customerBaseTestCucumber.getSignUpPageIOS().email.sendKeys(randomEmail);
  }

  @And("^I enter 'password' in second box$")
  public void iEnterPasswordInSecondBox() throws Throwable {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    customerBaseTestCucumber.getSignUpPageIOS().password.sendKeys("usi@123");
  }

  @And("^I enter 'Phone Number' in third box$")
  public void iEnterPhoneNumberInThirdBox() throws Throwable {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    customerBaseTestCucumber.getSignUpPageIOS().phoneNumber.sendKeys("12345678901");
  }

  @When("^I tap on 'Create Account' button$")
  public void iTapOnCreateAccountButton() throws Throwable {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    customerBaseTestCucumber.getSignUpPageIOS().createAccount.click();
  }

  @Then("^I should see email in my account screen$")
  public void iShouldSeeEmailInMyAccountScreen() throws Throwable {
    customerBaseTestCucumber.getAccountInfoIOS().isPhoneNoDisplayed();
    Assert.assertEquals(customerBaseTestCucumber.getAccountInfoIOS().email.getText(), randomEmail,
            "Entered email is not matched with Account Page");
  }
}
