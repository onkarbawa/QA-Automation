@signUp
Feature: Verify curbside signup flow

  @C114954
  Scenario: Verify on new account user should see name, email or phone number in my account
    Given I launch Curbside application
    And I land on store selection screen
    And I set the staging environment
    And I tap on 'Account' icon
    And I tap on 'Sign up' button on Account page
    And I tap on Sign up with email button
    And I enter 'email' in first text box
    And I go to next text field
    And I enter 'Phone Number' in third text box
    And I go to next text field
    And I enter 'password' in second text box
    When I tap on 'Create Account' button on the page
    Then I should see the Account details with Email id in it


