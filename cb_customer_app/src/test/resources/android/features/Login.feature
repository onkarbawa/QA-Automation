@login
Feature: Verify the login functionality

  @C114956
  Scenario Outline: Verify user should login with email
    Given I launch Curbside application
    And I tap on My Account icon
    And I tap on sign in button on Account page
    And I tap on sign in with email button
    And I enter "<email>" and "<password>" for login
    When I tap on sign in button
    Then I should be logged in
  Examples:
    |       email         |      password     |
    |fusic.test1@gmail.com|fusic@123          |