@signUp
Feature: Check informations and functionalitys on SignUp in Customer app

  @C114954
  Scenario: Verify on new account user should see name, email or phone number in my account
    Given I launch the iOS Curbside App
    And I accept Allow to send notifications
    And I click on 'Skip Intro' button
    And I tap on 'Ok with me' button on access landing screen
    And I accept 'Allow Access Location'
    And I tap on 'My Account' icon
    And I tap on create one now
    And I tap on 'Create an Account'
    And I enter 'email' in first box
    And I enter 'password' in second box
    And I enter 'Phone Number' in third box
    When I tap on 'Create Account' button
    Then I should see email in my account screen


  @C114958
  Scenario: Verify Credit card info is shown in 'Payment Info' in 'My Account'
    Given I launch the iOS Curbside App
    And I accept Allow to send notifications
    And I click on 'Skip Intro' button
    And I tap on 'Ok with me' button on access landing screen
    And I accept 'Allow Access Location'
    And I tap on 'My Account' icon
    And I tap on 'Create an Account'
    And I enter 'email' in first box
    And I enter 'password' in second box
    And I enter 'Phone Number' in third box
    And I tap on 'Create Account' button
    And I tap on Payment Info



