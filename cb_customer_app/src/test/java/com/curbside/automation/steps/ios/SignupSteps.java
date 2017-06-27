package com.curbside.automation.steps.ios;

import com.curbside.automation.common.BaseTest;
import com.curbside.automation.customerApp.ios.pages.home.HomePageIOS;
import com.curbside.automation.customerApp.ios.pages.myAccount.MyAccountPageIOS;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by bawa.onkar on 6/21/2017.
 */
public class SignupSteps {

  MyAccountPageIOS myAccountPageIOS = new MyAccountPageIOS(BaseTest.driver);

  @And("^I click on 'Create an Account'$")
  public void iClickOnCreateAnAccount() {
    myAccountPageIOS.createOneNow.click();
  }

  @And("^I enter 'email' in first box$")
  public void iEnterEmailInFirstBox() {
    // Write code here that turns the phrase above into concrete actions
    
  }

  @And("^I enter 'password' in second box$")
  public void iEnterPasswordInSecondBox() {
    // Write code here that turns the phrase above into concrete actions
    
  }

  @And("^I enter 'Phone Number' in third box$")
  public void iEnterPhoneNumberInThirdBox() {
    // Write code here that turns the phrase above into concrete actions
    
  }

  @When("^I click on 'Create Account' button$")
  public void iClickOnCreateAccountButton() {
    // Write code here that turns the phrase above into concrete actions
    
  }

  @Then("^I should see name, email or phone number in my account$")
  public void iShouldSeeNameEmailOrPhoneNumberInMyAccount() {
    // Write code here that turns the phrase above into concrete actions
    
  }

  @Given("^I launched the application of Customer$")
  public void iLaunchedTheApplicationOfCustomer() {
    // Write code here that turns the phrase above into concrete actions
  }
}
