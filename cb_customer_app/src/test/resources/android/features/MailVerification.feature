@mailVerification
Feature: Android- Mail verification flow

  Scenario: Setting test environment
    Given I launch Curbside application
    And I have selected test environment

  @Android @C114978
  Scenario: Verify on new account signup user should receive a E-Mail on EmailID
    Given I am not signed into application
    And I tap on 'Account' button
    And I tap on 'Sign Up' button
    And I tap on 'Sign Up with Email' button
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should see my given information under Account Info
    Then I should receive 'Welcome' Email from Curbside app


  @Android @C114979
  Scenario: Verify user receives a E-Mail when order is in progress
    Given I add credit card information
    And I am on 'Palo Alto' location 'Stores' Screen
    And I select 'CVS' store and search for 'toothpaste' product
    And I select 1 product from list
    And I add 1 quantity of the product
    And I tap on 'Cart' button
    When I tap on Place order button
    Then I should see the successful placed order notification on the screen
    Then I should receive 'In-Progress' Email from Curbside app