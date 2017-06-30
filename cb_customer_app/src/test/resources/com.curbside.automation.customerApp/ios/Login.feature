@login
Feature: Check information's and functionality's on Login in Customer app

  @C114956
  Scenario: Verify user should be login in curbside app
    Given I launch the iOS Curbside App
    And I accept Allow to send notifications
    And I click on 'Skip Intro' button
    And I tap on 'Ok with me' button on access landing screen
    And I accept 'Allow Access Location'
    And I tap on 'My Account' icon
    And I tap on Sign In button
    And I tap on Sign In with e-mail button
    And I enter email in first box
    And I enter password in second box
    When I tap on Sign In button
    Then I should see email in my account

  @C114957
  Scenario: Verify new user should be login in curbside app and Informaton shown in My Account
    Given I launch the iOS Curbside App
    And I accept Allow to send notifications
    And I click on 'Skip Intro' button
    And I tap on 'Ok with me' button on access landing screen
    And I accept 'Allow Access Location'
    And I tap on 'My Account' icon
    And I tap on Sign In button
    And I tap on 'Create an Account'
    And I enter 'email' in first box
    And I enter 'password' in second box
    And I enter 'Phone Number' in third box
    When I tap on 'Create Account' button
    Then I should see email in my account screen

  @C114955
  Scenario: Verify user should be login in curbside app
    Given I launch the iOS Curbside App
    And I accept Allow to send notifications
    And I click on 'Skip Intro' button
    And I tap on 'Ok with me' button on access landing screen
    And I accept 'Allow Access Location'
    And I tap on 'My Account' icon
    And I tap on Sign In button
    And I tap on Sign in with facebook
    And I tap on Login with facebook app
    And I enter facebook login email
    And I enter facebook password
    And I tap on login button