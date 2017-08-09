@snsVerification
Feature: Android- SMS verification flow

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment

  @Android @C114977
  Scenario: Verify on new account signup user should receive a message on phone
    Given I am not signed into application
    And I check there is no latest SMS from Curbisde
    And I tap on 'Account' button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see my given information under Account Info
    Then I should receive welcome SMS from Curbside app