package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

/**
 * Created by hitesh.grover on 27/06/17.
 */
public class SignupSignIn {


    BaseTest baseTest = new BaseTest();
    CustomerBaseTestCucumber customerBaseTestCucumber = new CustomerBaseTestCucumber();

    @And("^I tap on 'Sign up with email' button$")
    public void iClickOnSignupWithEmailButton() throws Exception {
        customerBaseTestCucumber.getSignInSignUpPage().getSignUpWithEmailButton().click();
    }

    @And("^I enter 'email' in first text box$")
    public void iEnterEmailInFirstTextBox() throws Exception {
        customerBaseTestCucumber.getSignInSignUpPage().getEmailTextField()
                .sendKeys(customerBaseTestCucumber.getUtilities().getRandomEmail());
    }

    @And("^I enter 'password' in second text box$")
    public void iEnterPasswordInSecondTextBox() throws Exception {
        Thread.sleep(1000);
        customerBaseTestCucumber.getSignInSignUpPage().getPasswordTextField().sendKeys("1234hdso6");
    }

    @And("^I enter 'Phone Number' in third text box$")
    public void iEnterPhoneNumberInThirdTextBox() throws Exception {
        Thread.sleep(1000);
        customerBaseTestCucumber.getSignInSignUpPage().getPhoneNumberTextField().sendKeys("95829096234");
        customerBaseTestCucumber.getUtilities().hitEnterAndroid();
    }

    @When("^I tap on 'Create Account' button on the page$")
    public void iClickOnCreateAccountButtonOnThePage() throws Exception {
        Thread.sleep(1000);
        customerBaseTestCucumber.getSignInSignUpPage().getCreateAccountButton().click();
    }
}
