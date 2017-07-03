@login
Feature: Verify application functionality on login screen

  @C114956
  Scenario: Verify Old user should be login in curbside app
    Given I launch Curbside application
    And I accept notifications alert
    And I tap on 'Skip Intro' button
    And I tap on 'My Account' button
    And I tap on 'Sign In' button
    And I tap on 'Sign In with Email' button
    And I enter email and password 'Old User'
    When I tap on 'Sign In' button
    Then I saw email on MyAccount page 'Old User'

  @C114957
  Scenario: Verify New user should be login in curbside app
    Given I launch Curbside application
    And I accept notifications alert
    And I tap on 'Skip Intro' button
    And I tap on 'My Account' button
    And I tap on 'Sign In' button
    And I tap on 'Create An Account' button
    And I enter email and password 'New User'
    And I enter phone number
    When I tap on 'Create Account' button
    Then I saw email on MyAccount page 'New User'






