@signUp
Feature: Verify curbside signup flow

  #TODO
  @C114954
  Scenario: Verify on new account user should see name, email or phone number in my account
    Given I launch the Curbside App
    And I land on Nearby stores screen
    And I set the staging environment
    And I tap on 'Account' icon
    And I tap on 'Sign up' button on Account page
    And I tap on 'Sign up with email' button
    And I enter 'email' in first text box
    And I go to next text field
    And I enter 'password' in second text box
    And I go to next text field
    And I enter 'Phone Number' in third text box
    When I tap on 'Create Account' button on the screen
