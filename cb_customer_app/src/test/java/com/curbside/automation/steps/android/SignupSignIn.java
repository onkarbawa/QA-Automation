package com.curbside.automation.steps.android;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.common.CustomerBaseTestCucumber;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

/**
 * Created by hitesh.grover on 27/06/17.
 */
public class SignupSignIn {


    BaseTest baseTest = new BaseTest();
    CustomerBaseTestCucumber customerBaseTestCucumber;

    @And("^I click on 'Sign up with email' button$")
    public void iClickOnSignupWithEmailButton(){
        customerBaseTestCucumber.getSignInSignUpPage().getSignUpWithEmailButton().click();
    }

    @And("^I enter 'email' in first text box$")
    public void iEnterEmailInFirstTextBox(){
        customerBaseTestCucumber.getSignInSignUpPage().getEmailTextField().sendKeys("sadas@ad.casd");
    }

    @And("^I enter 'password' in second text box$")
    public void iEnterPasswordInSecondTextBox(){
        customerBaseTestCucumber.getSignInSignUpPage().getPasswordTextField().sendKeys("123456789");
    }

    @And("^I enter 'Phone Number' in third text box$")
    public void iEnterPhoneNumberInThirdTextBox(){
        customerBaseTestCucumber.getSignInSignUpPage().getPhoneNumberTextField().sendKeys("958290962");
    }

    @When("^I click on 'Create Account' button on the page$")
    public void iClickOnCreateAccountButtonOnThePage(){
        customerBaseTestCucumber.getSignInSignUpPage().getCreateAccountButton().click();
    }
}
