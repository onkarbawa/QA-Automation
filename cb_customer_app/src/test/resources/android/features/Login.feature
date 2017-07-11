@login
Feature: Verify the login functionality

  @C114957
  Scenario: Verify New user should login in curbside app
    Given I launch Curbside application for the first time
    And I am on Home Screen
    And I have selected test environment
    And I tap on 'Account' button
    And I tap on Sign up button on My Account page
    And I tap on sign up with email button on sign up page
    And I enter fresh details of EmailID, PhoneNumber and Password on Signup screen
    When I tap on Create Account button
    Then I should be logged in

  @C114956
  Scenario Outline: Verify user should login with email
    Given I launch Curbside application
    And I tap on 'Account' button
    And I tap on 'Sign In' button
    And I tap on 'Sign In with Email' button
    And I enter "<email>" and "<password>" for login
    When I tap on sign in button
    Then I should be logged in
  Examples:
    |       email         |      password     |
    |fusic.test1@gmail.com|fusic@123          |