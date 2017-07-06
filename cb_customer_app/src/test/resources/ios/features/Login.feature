@login
Feature: Verify application functionality on login screen

  @C114957
  Scenario: Verify New user should login in curbside app
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I have selected test environment
    And I tap on 'My Account' button
    And I tap on 'Sign In' button
    And I tap on 'Create An Account' button
    And I signup for a new account
    When I tap on 'Create Account' button
    Then I should see my signup information under My Account

  @C114956
  Scenario Outline: Verify Old user should login in curbside app
    Given I launch Curbside application
    And I accept notifications alert
    And I tap on 'Skip Intro' button
    And I have selected test environment
    And I tap on 'My Account' button
    And I tap on 'Sign In' button
    And I tap on 'Sign In with Email' button
    And I enter '<Email>' and '<Password>'
    When I tap on 'Sign In' button
    Then I saw email on MyAccount page
    Examples:
      |             Email                  |     Password    |
      |      fusic.test1@gmail.com         |     fusic@123   |


  @C114955
  Scenario Outline: Verify Facebook user should login in curbside app
    Given I launch Curbside application
    And I accept notifications alert
    And I tap on 'Skip Intro' button
    And I have selected test environment
    And I tap on 'My Account' button
    And I tap on 'Sign In' button
    And I tap on 'Sign In with Facebook' button
    And I tap on 'Log In with the Facebook App' button
    And I tap on 'Open' button
    And I enter '<Email>' and '<Password>' for facebook
    And I tap on 'login-button' button
    Examples:
      |             Email                  |     Password    |
      |      jacktest94@gmail.com          |     tft@123     |






