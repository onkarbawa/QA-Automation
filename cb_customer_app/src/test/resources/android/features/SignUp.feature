@signUp
Feature: Verify curbside signup flow

  @C114954
  Scenario: Verify on new account user should see name, email or phone number in my account
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I have selected test environment
    And I tap on 'Account' button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see the Account details with Email id in it