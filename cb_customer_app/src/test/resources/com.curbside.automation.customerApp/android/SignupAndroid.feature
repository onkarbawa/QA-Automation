@signUp
Feature: Verify curbside signup flow

  @C114954
  Scenario: Verify on new account user should see name, email or phone number in my account
    Given I launch the Android Customer App
    And I land on store selection page
    And I click on 'Account' icon
    And I click on 'Sign up' button on Account page
    And I click on 'Sign up with email' button
    And I enter 'email' in first text box
    And I enter 'password' in second text box
    And I enter 'Phone Number' in third text box
    When I click on 'Create Account' button on the page
    And I close the Curbside app
