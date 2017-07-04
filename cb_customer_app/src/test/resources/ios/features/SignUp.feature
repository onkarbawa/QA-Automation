@appLaunch
Feature: Verify application functionality on SignUp screen

  @C114954
  Scenario: Verify on SignUp user should see name, email or phone number in My Account screen
  Given I launch Curbside application
  And I accept notifications alert
  And I tap on 'Skip Intro' button
  And I tap on 'My Account' button
  And I tap on 'Create one now' button
  And I tap on 'Create An Account' button
  And I enter credentials for signUp
  And I tap on 'Create Account' button
  Then I saw new email on MyAccount page

  @C114958
  Scenario: Verify Credit card info is shown in 'Payment Info Screen' in My Account screen
    Given I launch Curbside application
    And I accept notifications alert
    And I tap on 'Skip Intro' button
    And I tap on 'My Account' button
    And I tap on 'Create one now' button
    And I tap on 'Create An Account' button
    And I enter credentials for signUp
    And I tap on 'Create Account' button