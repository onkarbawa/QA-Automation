@mailVerification
Feature: iOS- Mail verification flow

  Scenario: Setting test environment
    Given I launch Curbside application for the first time
    And I have selected test environment
    And I am not signed into application

  @iOS @C114977
  Scenario: Verify Mail subject - welcome to curbside
    Given I tap on 'My Account' icon in bottom menu
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    When I should see my signup information under Account Info
    Then I should receive welcome Email from Curbside app

