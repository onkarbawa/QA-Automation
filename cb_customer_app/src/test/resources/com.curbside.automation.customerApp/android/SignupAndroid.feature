@signUp
Feature: Check information's and functionality's on SignUp in Customer app of Android

  @C114954
  Scenario: Verify on new account user should see name, email or phone number in my account
    Given I launch the Android Customer App
    And I click on 'Account' icon
    And I click on 'Create an Account' on Account page
    And I enter 'email' in first text box
    And I enter 'password' in second text box
    And I enter 'Phone Number' in third text box
    When I click on 'Create Account' button on the page
    Then I should see name, email or phone number in my account page

  @C114958
  Scenario: Verify Credit card info is shown in 'Payment Info' in 'Account' page
    Given I launched the application of Customer
    And I click on 'Account' icon
    And I click on 'Create an Account' on Account page
    And I enter 'email' in first text box
    And I enter 'password' in second text box
    And I enter 'Phone Number' in third text box
    When I click on 'Create Account' button