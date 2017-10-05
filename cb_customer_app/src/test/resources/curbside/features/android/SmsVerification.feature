@smsVerification
Feature: Android- SMS verification flow

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment

  @Android @C114977
  Scenario: Verify on new account signup user should receive a SMS on phone
    Given I am not signed into application
    And I tap on 'Account' button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I check there is no latest SMS from Curbside
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see my given information under Account Info
    Then I should receive welcome SMS from Curbside app

  @Android @C114969
  Scenario: Verify SMS is in progress
    Given I add credit card information
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select 'CVS' store and search for 'toothpaste' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    And I check there is no latest SMS from Curbside
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    Then I should receive order SMS from Curbside app

