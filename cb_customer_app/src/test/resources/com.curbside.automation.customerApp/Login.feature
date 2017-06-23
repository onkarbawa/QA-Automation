
"""Created by bawa.onkar on 6/21/2017."""

@login
Feature: Check information's and functionality's on Login in Customer app

  @C114955-1
  Scenario: Verify user is able to login if Facebook account is linked with curbside account
    Given I launch the Customer App
    And I click on Sign in with Facebook button
    And I click on log in with Facebook App button
    When I click on Open
    Then I click on Facebook to continue

  @C114955-2
  Scenario: Verify user is not linked Facebook account to curbside account then they should able to link both accounts
    Given I launch the Customer App
    And I click on Sign in with Facebook button
    And I click on log in with Facebook App button
    When I click on Open
    Then I add Facebook Account to continue

  @C114956
  Scenario: Verify user should be login in customer app
    Given I launch the Customer App
    And I click on Sign in with Facebook button
    And I click on log in with Facebook App button
    When I click on Open
    Then I add Facebook Account to continue