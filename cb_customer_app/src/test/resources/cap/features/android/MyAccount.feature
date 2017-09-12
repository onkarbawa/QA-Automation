@myAccount
Feature: Android- My Account

  @Android
  Scenario: Setting test environment for CAP
    Given I launch Cap application for the first time
    And I have selected test environment for CAP

  Scenario Outline: Setting test environment for CAP
    Given I enter "<account>", "<username>" and "<password>" for login
    When I tap on 'Login' button
    Then I should see Home screen of CAP
    When I Sign out from CAP
    Then I should see Login Screen of CAP
    Examples:
      | account  | username             | password |
      | curbside | qaautomation_initium | curbside |
